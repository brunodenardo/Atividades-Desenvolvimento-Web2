package com.autobots.automanager.controles;

import java.net.http.HttpRequest;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.TelefoneAtualizador;
import com.autobots.automanager.modelos.hateos.AdicionadorLinkTelefone;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

	@Autowired
	private ClienteRepositorio cliente_repositorio;
	
	@Autowired
	private TelefoneRepositorio telefone_repositorio;
	
	@Autowired
	private AdicionadorLinkTelefone adicionadorLink;
	
	@GetMapping("/{telefone_id}")
	public ResponseEntity<?> pegarTelefone(@PathVariable Long telefone_id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Telefone> optionalTelefone = telefone_repositorio.findById(telefone_id);
		if(optionalTelefone.isPresent()) {
			status = HttpStatus.OK;
			Telefone telefone = optionalTelefone.get();
			adicionadorLink.adicionarLink(telefone);
			return ResponseEntity.status(status).body(telefone);
		}
		return ResponseEntity.status(status).body("Telefone não encontrado");
	}
	
	@GetMapping("/porCliente/{cliente_id}")
	public ResponseEntity<List<Telefone>> pegarTelefonesCliente(@PathVariable Long cliente_id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Cliente> optionalCliente = cliente_repositorio.findById(cliente_id);
		if(optionalCliente.isPresent()) {
			status = HttpStatus.OK;
			Cliente cliente = optionalCliente.get();
			List<Telefone> telefones = cliente.getTelefones();
			adicionadorLink.adicionarLink(cliente_id, telefones);
			return new ResponseEntity<List<Telefone>>(telefones, status);
		}
		return ResponseEntity.status(status).build();
		
	}
	
	@GetMapping
	public ResponseEntity<List<Telefone>> listarTodosTelefones(){
		List<Telefone> telefones = telefone_repositorio.findAll();
		adicionadorLink.adicionarLink(telefones);
		return ResponseEntity.ok(telefones);
	}
	
	@Transactional
	@PostMapping("/cliente/{cliente_id}")
	public ResponseEntity<?> cadastrarTelefone(@PathVariable Long cliente_id, @RequestBody Telefone telefone) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Cliente> optionalCliente = cliente_repositorio.findById(cliente_id);
		if(optionalCliente.isPresent()) {
			status = HttpStatus.CREATED;
			Cliente cliente = optionalCliente.get();
			List<Telefone> listaTelefones = cliente.getTelefones();
			listaTelefones.add(telefone);
			cliente.setTelefones(listaTelefones);
			adicionadorLink.adicionarLink(telefone);
			return ResponseEntity.status(status).body(telefone);
		}
		return ResponseEntity.status(status).body("Cliente não encontrado");
		
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<?> atualizaTelefone(@RequestBody Telefone telefoneAtualizacao) {
		Optional<Telefone> optionalTelefoneAtual = telefone_repositorio.findById(telefoneAtualizacao.getId());
		HttpStatus status = HttpStatus.BAD_REQUEST;
		if(optionalTelefoneAtual.isPresent()) {
			status = HttpStatus.OK;
			Telefone telefoneAtual = optionalTelefoneAtual.get();
			TelefoneAtualizador atualizador = new TelefoneAtualizador();
			atualizador.atualizar(telefoneAtual, telefoneAtualizacao);
			adicionadorLink.adicionarLink(telefoneAtual);
			return ResponseEntity.status(status).body(telefoneAtual);
		}
		return ResponseEntity.status(status).body("Id do telefone errado");
	}
	
	@Transactional
	@DeleteMapping("/{telefone_id}/cliente/{cliente_id}")
	public ResponseEntity<?> deletaTelefone(@PathVariable Long telefone_id, @PathVariable Long cliente_id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Telefone> optionalTelefone = telefone_repositorio.findById(telefone_id);
		Optional<Cliente> optionalCliente = cliente_repositorio.findById(cliente_id);
		if(optionalCliente.isPresent()) {
			if(optionalTelefone.isPresent()) {
				status = HttpStatus.OK;
				Telefone telefone = optionalTelefone.get();
				Cliente cliente = optionalCliente.get();
				List<Telefone> listaTelefone = cliente.getTelefones();
				listaTelefone.remove(telefone);
				cliente.setTelefones(listaTelefone);
				adicionadorLink.adicionarLink(cliente_id, telefone);
				return ResponseEntity.status(status).body("Deleção feita com sucesso");
			}
			return ResponseEntity.status(status).body("Telefone não encontrado");
		}
		return ResponseEntity.status(status).body("Cliente não encontrado");
	}
	
}
