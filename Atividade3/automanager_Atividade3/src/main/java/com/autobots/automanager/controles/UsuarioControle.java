package com.autobots.automanager.controles;

import java.util.List;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DadosAtualizacaoUsuario;
import com.autobots.automanager.dto.DadosCadastroUsuario;
import com.autobots.automanager.dto.DadosListagemUsuario;
import com.autobots.automanager.dto.RetornoListagemUsuario;
import com.autobots.automanager.dto.RetornoObterUsuario;
import com.autobots.automanager.entitades.CredencialUsuarioSenha;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.servicos.MercadoriaAtualiza;
import com.autobots.automanager.servicos.UsuarioAtualizar;
import com.autobots.automanager.servicos.UsuarioGeraLink;
import com.autobots.automanager.servicos.UsuarioListar;
import com.autobots.automanager.servicos.VeiculoAtualiza;

@RestController
@RequestMapping("/usuario")
public class UsuarioControle {

	@Autowired
	private UsuarioListar usuarioListagem;
	
	@Autowired
	private UsuarioAtualizar usuarioAtualizacao;
	
	@Autowired
	private VeiculoAtualiza veiculoAtualiza;
	
	@Autowired
	private MercadoriaAtualiza mercadoriaAtualiza;
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
	@Autowired
	private UsuarioGeraLink geraLink;
	
	@GetMapping
	public ResponseEntity<RetornoListagemUsuario> listarTodosUsuarios(){
		List<DadosListagemUsuario> usuarios = usuarioListagem.listarTodos();
		return ResponseEntity.ok(new RetornoListagemUsuario(usuarios, geraLink.gerar()));
	}
	
	@GetMapping("/{id_usuario}")
	public ResponseEntity<RetornoObterUsuario> obterUsuario(@PathVariable Long id_usuario){
		try {
			DadosListagemUsuario usuario = usuarioListagem.obterUsuario(id_usuario);
			return ResponseEntity.ok(new RetornoObterUsuario(usuario, geraLink.gerar()));
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();
		}
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<List<Link>> cadastrarUsuario(@RequestBody DadosCadastroUsuario dadosUsuario) {
		Usuario usuario = new Usuario(dadosUsuario);
		repositorio_usuario.save(usuario);
		return ResponseEntity.ok().body(geraLink.gerar());
	}
	
	@PutMapping
	@Transactional
	public ResponseEntity<List<Link>> atualizarUsuario(@RequestBody DadosAtualizacaoUsuario dadosAtualizacao) {
		Boolean atualizou = usuarioAtualizacao.atualizarUsuario(dadosAtualizacao);
		if(atualizou) {
			return ResponseEntity.ok().body(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
	@PutMapping("/{usuario_id}/veiculos")
	@Transactional
	public ResponseEntity<List<Link>> alterarListaVeiculos(
			@PathVariable Long usuario_id,
			@RequestBody Set<Veiculo> veiculosAtualizar
			) {
		Boolean atualizou = veiculoAtualiza.atualizarListaVeiculo(usuario_id, veiculosAtualizar);
		if(atualizou) {
			return ResponseEntity.ok().body(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
	@PutMapping("/{usuario_id}/mercadorias")
	@Transactional
	public ResponseEntity<List<Link>> alterarListaMercadoria(
			@PathVariable Long usuario_id,
			@RequestBody Set<Mercadoria> mercadoriaAtualizacao
			) {
		Boolean atualizou = mercadoriaAtualiza.atualizarListaMercadoria(usuario_id, mercadoriaAtualizacao);
		if(atualizou) {
			return ResponseEntity.ok().body(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
	@DeleteMapping("/{usuario_id}")
	@Transactional
	public ResponseEntity<List<Link>> deletarUsuario(
			@PathVariable Long usuario_id
			) {
		Optional<Usuario> optionalUsuario = repositorio_usuario.findById(usuario_id);
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			usuario.getCredenciais().forEach((credencial)->{
				if(credencial instanceof CredencialUsuarioSenha) {
					credencial.setInativo(true);
				}
			});
			return ResponseEntity.ok().body(geraLink.gerar());
			
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
}
