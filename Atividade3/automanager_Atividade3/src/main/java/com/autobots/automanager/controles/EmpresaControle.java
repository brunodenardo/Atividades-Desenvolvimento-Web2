package com.autobots.automanager.controles;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Links;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DadosAtrelaUsuario;
import com.autobots.automanager.dto.DadosAtualizacaoEmpresa;
import com.autobots.automanager.dto.DadosListagemClientesEmpresa;
import com.autobots.automanager.dto.DadosListagemEmpresa;
import com.autobots.automanager.dto.DadosListagemFornecedoresEmpresa;
import com.autobots.automanager.dto.DadosListagemFuncionariosEmpresa;
import com.autobots.automanager.dto.DadosObterEmpresa;
import com.autobots.automanager.dto.RetornoListagemClientesEmpresa;
import com.autobots.automanager.dto.RetornoListagemEmpresa;
import com.autobots.automanager.dto.RetornoListagemFornecedoresEmpresa;
import com.autobots.automanager.dto.RetornoListagemFuncionariosEmpresa;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.servicos.ClienteListar;
import com.autobots.automanager.servicos.EmpresaAtualiza;
import com.autobots.automanager.servicos.EmpresaGeraLink;
import com.autobots.automanager.servicos.EmpresaListar;
import com.autobots.automanager.servicos.FornecedoresListar;
import com.autobots.automanager.servicos.FuncionariosListar;

@RestController
@RequestMapping("/empresa")
public class EmpresaControle {

	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
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
	
	@Autowired
	private EmpresaGeraLink geraLink;
	
	@GetMapping
	public ResponseEntity<RetornoListagemEmpresa> listarTodasEmpresas(){
		List<DadosListagemEmpresa> listaEmpresa = empresaListagem.listarTodos();
		RetornoListagemEmpresa retorno = new RetornoListagemEmpresa(listaEmpresa, geraLink.gerar());
		return ResponseEntity.ok(retorno);
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
	public ResponseEntity<RetornoListagemFuncionariosEmpresa> listarFuncionariosEmpresa(
			@PathVariable Long empresa_id){
		List<DadosListagemFuncionariosEmpresa> funcionarios = funcionarioListagem.listarFuncionarios(empresa_id); 
		if(funcionarios != null) {
			RetornoListagemFuncionariosEmpresa retorno = new RetornoListagemFuncionariosEmpresa(funcionarios, geraLink.gerar());
			return ResponseEntity.ok(retorno);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{empresa_id}/fornecedores")
	public ResponseEntity<RetornoListagemFornecedoresEmpresa> listarFornecedoresEmpresa(
			@PathVariable Long empresa_id){
		List<DadosListagemFornecedoresEmpresa> funcionarios = fornecedorListagem.listarFornecedores(empresa_id); 
		if(funcionarios != null) {
			RetornoListagemFornecedoresEmpresa retorno = new RetornoListagemFornecedoresEmpresa(funcionarios, geraLink.gerar());
			return ResponseEntity.ok(retorno);
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/{empresa_id}/clientes")
	public ResponseEntity<RetornoListagemClientesEmpresa> listarClientesEmpresa(
			@PathVariable Long empresa_id){
		List<DadosListagemClientesEmpresa> clientes = clienteListagem.listarClientes(empresa_id); 
		if(clientes != null) {
			RetornoListagemClientesEmpresa retorno = new RetornoListagemClientesEmpresa(clientes, geraLink.gerar());
			return ResponseEntity.ok(retorno);
		}
		return ResponseEntity.badRequest().build();
	}
	
	
	
	@PostMapping
	public ResponseEntity<List<Link>> cadastrarEmpresa(@RequestBody Empresa empresa) {
		empresa.setCadastro(new Date());
		repositorio_empresa.save(empresa);
		return ResponseEntity.ok(geraLink.gerar());
	}
	
	@DeleteMapping("/{empresa_id}")
	public ResponseEntity<List<Link>> deletarEmpresa(@PathVariable long empresa_id) {
		try {
			repositorio_empresa.deleteById(empresa_id);
		} catch (Exception e) {
			return ResponseEntity.badRequest().body(geraLink.gerar());
		}
		
		return ResponseEntity.ok(geraLink.gerar());
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<List<Link>> atualizarEmpresa(@RequestBody DadosAtualizacaoEmpresa dadosAtualizacao) {
		Boolean existe = empresaAtualiza.atualizar(dadosAtualizacao);
		if(existe) {
			return ResponseEntity.ok(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
	@PutMapping("/atrela/usuario")
	@Transactional
	public ResponseEntity<List<Link>> atrelarUsuarioEmpresa(@RequestBody DadosAtrelaUsuario dadosAtrelaUsuario){
		Optional<Usuario> usuarioOptional = repositorio_usuario.findById(dadosAtrelaUsuario.id_usuario());
		Optional<Empresa> empresaOptional = repositorio_empresa.findById(dadosAtrelaUsuario.id_empresa());
		if(usuarioOptional.isPresent() && empresaOptional.isPresent()) {
			empresaOptional.get().getUsuarios().add(usuarioOptional.get());
			return ResponseEntity.ok(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
}
