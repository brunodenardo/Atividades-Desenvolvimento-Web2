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
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelo.DocumentoAtualizador;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
	
	@Autowired
	private DocumentoRepositorio documento_repositorio;
	
	@Autowired
	private ClienteRepositorio cliente_repositorio;

	@GetMapping("/{documento_id}")
	public Documento pegarDocumentos(@PathVariable Long documento_id) {
		Documento documento = documento_repositorio.findById(documento_id).get();
		return documento;
	}
	
	@GetMapping("/porCliente/{cliente_id}")
	public List<Documento> pegarDocumentosCliente(@PathVariable Long cliente_id) {
		Cliente cliente = cliente_repositorio.findById(cliente_id).get();
		List<Documento> documentos = cliente.getDocumentos();
		return documentos;
	}
	
	@GetMapping
	public List<Documento> listarTodosDocumentos(){
		return documento_repositorio.findAll();
	}
	
	@Transactional
	@PostMapping("/cliente/{cliente_id}")
	public void cadastrarDocumento(@PathVariable Long cliente_id, @RequestBody Documento documento) {
		Cliente cliente = cliente_repositorio.findById(cliente_id).get();
		List<Documento> listaDocumentos = cliente.getDocumentos();
		listaDocumentos.add(documento);
		cliente.setDocumentos(listaDocumentos);
	}
	
	@Transactional
	@PutMapping
	public void atualizaDocumento(@RequestBody Documento documentoAtualizacao) {
		Documento documentoAtual = documento_repositorio.findById(documentoAtualizacao.getId()).get();
		DocumentoAtualizador atualizador = new DocumentoAtualizador();
		atualizador.atualizar(documentoAtual, documentoAtualizacao);
	}
	
	@Transactional
	@DeleteMapping("/{documento_id}/cliente/{cliente_id}")
	public void deletaDocumetno(@PathVariable Long documento_id, @PathVariable Long cliente_id) {
		Documento documento = documento_repositorio.findById(documento_id).get();
		Cliente cliente = cliente_repositorio.findById(cliente_id).get();
		List<Documento> listaDocumento = cliente.getDocumentos();
		listaDocumento.remove(documento);
		cliente.setDocumentos(listaDocumento);
	}
}
