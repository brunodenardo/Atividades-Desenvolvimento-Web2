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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.dto.DadosListagemVeiculos;
import com.autobots.automanager.dto.DadosVeiculo;
import com.autobots.automanager.dto.RetornoListagemVeiculo;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.repositorios.RepositorioUsuario;
import com.autobots.automanager.repositorios.RepositorioVeiculo;
import com.autobots.automanager.servicos.VeiculoGeraLink;
import com.autobots.automanager.servicos.VeiculoListar;

@RestController
@RequestMapping("/veiculo")
public class VeiculoControle {
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
	@Autowired
	private RepositorioVeiculo repositorio_veiculo;
	
	@Autowired
	private VeiculoListar veiculoConsulta;
	
	@Autowired
	private VeiculoGeraLink geraLink;

	@GetMapping("/{id_usuario}")
	public ResponseEntity<RetornoListagemVeiculo> listaVeiculos(@PathVariable Long id_usuario){
		Optional<Usuario> usuarioOptional = repositorio_usuario.findById(id_usuario);
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			return ResponseEntity.ok(
					new RetornoListagemVeiculo(veiculoConsulta.listarVeiculo(usuario), geraLink.gerar()));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@PostMapping("/{id_usuario}")
	@Transactional
	public ResponseEntity<List<Link>> cadastraVeiculos(
			@PathVariable Long id_usuario,
			@RequestBody DadosVeiculo veiculo) {
		Optional<Usuario> usuarioOptional = repositorio_usuario.findById(id_usuario);
		if(usuarioOptional.isPresent()) {
			Usuario usuario = usuarioOptional.get();
			Veiculo novoVeiculo = new Veiculo(veiculo, usuario);
			usuario.getVeiculos().add(novoVeiculo);
			return ResponseEntity.ok().body(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
	}
	
	@DeleteMapping("/{id_veiculo}")
	@Transactional
	public ResponseEntity<List<Link>> deletarVeiculo(@PathVariable Long id_veiculo) {
		Optional<Veiculo> veiculoOptional = repositorio_veiculo.findById(id_veiculo);
		if(veiculoOptional.isPresent()) {
			Veiculo veiculo = veiculoOptional.get();
			veiculo.getProprietario().getVeiculos().remove(veiculo);
			return ResponseEntity.ok().body(geraLink.gerar());
		}
		return ResponseEntity.badRequest().body(geraLink.gerar());
		
	}
	
}
