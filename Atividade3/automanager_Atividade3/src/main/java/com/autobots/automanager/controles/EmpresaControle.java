package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;

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

import com.autobots.automanager.dto.DadosAtualizacaoEmpresa;
import com.autobots.automanager.dto.DadosListagemClientesEmpresa;
import com.autobots.automanager.dto.DadosListagemEmpresa;
import com.autobots.automanager.dto.DadosListagemFornecedoresEmpresa;
import com.autobots.automanager.dto.DadosListagemFuncionariosEmpresa;
import com.autobots.automanager.dto.DadosObterEmpresa;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.servicos.ClienteListar;
import com.autobots.automanager.servicos.EmpresaAtualiza;
import com.autobots.automanager.servicos.EmpresaListar;
import com.autobots.automanager.servicos.FornecedoresListar;
import com.autobots.automanager.servicos.FuncionariosListar;

@RestController
@RequestMapping("/empresa")
public class EmpresaControle {

	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	@Autowired
	private EmpresaListar empresaListagem;
	
	@Autowired
	private ClienteListar clienteListagem;
	
	@Autowired
	private FuncionariosListar funcionarioListagem;
	
	@Autowired
	private FornecedoresListar fornecedorListagem;
	
	@Autowired
	private EmpresaAtualiza empresaAtualiza;
	
	@GetMapping
	public ResponseEntity<List<DadosListagemEmpresa>> listarTodasEmpresas(){
		List<DadosListagemEmpresa> listaEmpresa = empresaListagem.listarTodos();
		return ResponseEntity.ok(listaEmpresa);
	}
	
	@GetMapping("/{empresa_id}")
	public ResponseEntity<DadosObterEmpresa> pegarEmpresa(@PathVariable long empresa_id){
		DadosObterEmpresa empresa = empresaListagem.obterEmpresa(empresa_id);
		if(empresa != null) {
			return ResponseEntity.ok(empresa);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{empresa_id}/funcionarios")
	public ResponseEntity<List<DadosListagemFuncionariosEmpresa>> listarFuncionariosEmpresa(
			@PathVariable Long empresa_id){
		List<DadosListagemFuncionariosEmpresa> funcionarios = funcionarioListagem.listarFuncionarios(empresa_id); 
		if(funcionarios != null) {
			return ResponseEntity.ok(funcionarios);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{empresa_id}/fornecedores")
	public ResponseEntity<List<DadosListagemFornecedoresEmpresa>> listarFornecedoresEmpresa(
			@PathVariable Long empresa_id){
		List<DadosListagemFornecedoresEmpresa> funcionarios = fornecedorListagem.listarFornecedores(empresa_id); 
		if(funcionarios != null) {
			return ResponseEntity.ok(funcionarios);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{empresa_id}/clientes")
	public ResponseEntity<List<DadosListagemClientesEmpresa>> listarClientesEmpresa(
			@PathVariable Long empresa_id){
		List<DadosListagemClientesEmpresa> clientes = clienteListagem.listarClientes(empresa_id); 
		if(clientes != null) {
			return ResponseEntity.ok(clientes);
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	
	@PostMapping
	public ResponseEntity cadastrarEmpresa(@RequestBody Empresa empresa) {
		repositorio_empresa.save(empresa);
		return ResponseEntity.ok().build();
	}
	
	@DeleteMapping("/{empresa_id}")
	public ResponseEntity deletarEmpresa(@PathVariable long empresa_id) {
		repositorio_empresa.deleteById(empresa_id);
		return ResponseEntity.ok().build();
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity atualizarEmpresa(@RequestBody DadosAtualizacaoEmpresa dadosAtualizacao) {
		Boolean existe = empresaAtualiza.atualizar(dadosAtualizacao);
		if(existe) {
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().build();
	}
	
}
