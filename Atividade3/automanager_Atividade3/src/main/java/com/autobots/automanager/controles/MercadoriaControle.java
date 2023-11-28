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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DadosMercadoria;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.servicos.MercadoriaDeletar;

@RestController
@RequestMapping("/mercadoria")
public class MercadoriaControle {

	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
	@Autowired
	private RepositorioMercadoria repositorio_mercadoria;
	
	@Autowired
	private MercadoriaDeletar mercadoriaDelecao;
	
	
	@GetMapping("/empresa/{empresa_id}")
	public ResponseEntity<Set<Mercadoria>> listarMercadoriaEmpresa(@PathVariable Long empresa_id){
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			return ResponseEntity.ok(empresa.getMercadorias());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/fornecedor/{fornecedor_id}")
	public ResponseEntity<Set<Mercadoria>> listarMercadoriaFornecedor(@PathVariable Long fornecedor_id){
		Optional<Usuario> optionalUsuario = repositorio_usuario.findById(fornecedor_id);
		if(optionalUsuario.isPresent()) {
			Usuario fornecedor = optionalUsuario.get();
			return ResponseEntity.ok(fornecedor.getMercadorias());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/empresa/{empresa_id}")
	@Transactional
	public ResponseEntity cadastrarMercadoriaEmpresa(
			@PathVariable Long empresa_id,
			@RequestBody DadosMercadoria dadosMercadoria
			) {
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			Mercadoria mercadorio = new Mercadoria(dadosMercadoria);
			empresa.getMercadorias().add(mercadorio);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().body("Empresa não encontrada");
	}
	
	
	@PostMapping("/fornecedor/{fornecedor_id}")
	@Transactional
	public ResponseEntity cadastrarMercadoriaFornecedor(
			@PathVariable Long fornecedor_id,
			@RequestBody DadosMercadoria dadosMercadoria
			) {
		Optional<Usuario> optionalFornecedor = repositorio_usuario.findById(fornecedor_id);
		if(optionalFornecedor.isPresent()) {
			Usuario fornecedor = optionalFornecedor.get();
			if(fornecedor.getPerfis().contains(PerfilUsuario.FORNECEDOR)) {
				Mercadoria mercadorio = new Mercadoria(dadosMercadoria);
				fornecedor.getMercadorias().add(mercadorio);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().body("Usuário não é um fornecedor");	
		}
		return ResponseEntity.badRequest().body("Fornecedor não encontrada");
	}
	
	@DeleteMapping("/empresa/{empresa_id}/{mercadoria_id}")
	@Transactional
	public ResponseEntity deletarRelacaoMercadoriaEmpresa(
			@PathVariable Long empresa_id,
			@PathVariable Long mercadoria_id) {
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			Boolean deletou = mercadoriaDelecao.deletar(empresa, mercadoria_id);
			if(deletou) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().body("Mercadoria não encontrada");
		}
		return ResponseEntity.badRequest().body("Empresa não encontrada");
	}
	
	@DeleteMapping("/fornecedor/{fornecedor_id}/{mercadoria_id}")
	@Transactional
	public ResponseEntity deletarRelacaoMercadoriaFornecedor(
			@PathVariable Long fornecedor_id,
			@PathVariable Long mercadoria_id) {
		Optional<Usuario> optionalUsuario = repositorio_usuario.findById(fornecedor_id);
		if(optionalUsuario.isPresent()) {
			Usuario fornecedor = optionalUsuario.get();
			Boolean deletou = mercadoriaDelecao.deletar(fornecedor, mercadoria_id);
			if(deletou) {
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().body("Mercadoria não encontrada");
		}
		return ResponseEntity.badRequest().body("Empresa não encontrada");
	}
	
	
	//NÃO HÁ OUTROS TIPOS DE PUT PARA QUE O HISTÓRICO E AS VALIDADES NÃO SEJA PREJUDICADO
}
