package com.autobots.automanager.controles;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.autobots.automanager.modelo.TelefoneAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.TelefoneRepositorio;

@RestController
@RequestMapping("/telefone")
public class TelefoneControle {

	@Autowired
	private ClienteRepositorio cliente_repositorio;
	
	@Autowired
	private TelefoneRepositorio telefone_repositorio;
	
	@GetMapping("/{telefone_id}")
	public Telefone pegarDocumentos(@PathVariable Long telefone_id) {
		Telefone telefone = telefone_repositorio.findById(telefone_id).get();
		return telefone;
	}
	
	@GetMapping("/porCliente/{cliente_id}")
	public List<Telefone> pegarDocumentosCliente(@PathVariable Long cliente_id) {
		Cliente cliente = cliente_repositorio.findById(cliente_id).get();
		List<Telefone> telefones = cliente.getTelefones();
		return telefones;
	}
	
	@GetMapping
	public List<Telefone> listarTodosDocumentos(){
		return telefone_repositorio.findAll();
	}
	
	@Transactional
	@PostMapping("/cliente/{cliente_id}")
	public void cadastrarDocumento(@PathVariable Long cliente_id, @RequestBody Telefone telefone) {
		Cliente cliente = cliente_repositorio.findById(cliente_id).get();
		List<Telefone> listaTelefones = cliente.getTelefones();
		listaTelefones.add(telefone);
		cliente.setTelefones(listaTelefones);
	}
	
	@Transactional
	@PutMapping
	public void atualizaDocumento(@RequestBody Telefone telefoneAtualizacao) {
		Telefone telefoneAtual = telefone_repositorio.findById(telefoneAtualizacao.getId()).get();
		TelefoneAtualizador atualizador = new TelefoneAtualizador();
		atualizador.atualizar(telefoneAtual, telefoneAtualizacao);
	}
	
	@Transactional
	@DeleteMapping("/{telefone_id}/cliente/{cliente_id}")
	public void deletaDocumetno(@PathVariable Long telefone_id, @PathVariable Long cliente_id) {
		Telefone telefone = telefone_repositorio.findById(telefone_id).get();
		Cliente cliente = cliente_repositorio.findById(cliente_id).get();
		List<Telefone> listaTelefone = cliente.getTelefones();
		listaTelefone.remove(telefone);
		cliente.setTelefones(listaTelefone);
	}
	
}
