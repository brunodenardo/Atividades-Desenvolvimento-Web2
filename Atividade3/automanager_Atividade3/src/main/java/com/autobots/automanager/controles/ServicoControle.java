package com.autobots.automanager.controles;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DadosServico;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.servicos.ServicoDeletar;

@RestController
@RequestMapping("empresa/{empresa_id}/servico")
public class ServicoControle{
	
	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	@Autowired
	private ServicoDeletar servicoDelecao;
	
	@GetMapping
	public ResponseEntity<Set<Servico>> listarServicos(@PathVariable Long empresa_id){
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			return ResponseEntity.ok(empresa.getServicos());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity cadastrarServico(@PathVariable Long empresa_id, @RequestBody DadosServico dadosServico) {
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			Servico novoServico = new Servico(dadosServico);
			empresa.getServicos().add(novoServico);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
	@DeleteMapping("/{servico_id}")
	@Transactional
	public ResponseEntity deletarRelacaoServicoEmpresa(@PathVariable Long empresa_id, @PathVariable Long servico_id) {
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			Boolean deletou = servicoDelecao.deletar(empresa, servico_id);
			if(deletou) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().body("Id de serviço não encontrado");
		}
		return ResponseEntity.badRequest().body("Id de empresa não encontrado");
	}
	
	//NÃO HÁ PUT DE PRODUTO PARA QUE O HISTÓRICO NÃO SEJA PREJUDICADO
}