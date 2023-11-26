package com.autobots.automanager.controles;

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
import com.autobots.automanager.entidades.Documento;
import com.autobots.automanager.modelos.DocumentoAtualizador;
import com.autobots.automanager.modelos.hateos.AdicionadorLinkCliente;
import com.autobots.automanager.modelos.hateos.AdicionadorLinkDocumento;
import com.autobots.automanager.repositorios.ClienteRepositorio;
import com.autobots.automanager.repositorios.DocumentoRepositorio;

@RestController
@RequestMapping("/documento")
public class DocumentoControle {
	
	@Autowired
	private DocumentoRepositorio documento_repositorio;
	
	@Autowired
	private ClienteRepositorio cliente_repositorio;
	
	@Autowired
	private AdicionadorLinkDocumento adicionadorLink;

	@GetMapping("/{documento_id}")
	public ResponseEntity<?> pegarDocumento(@PathVariable Long documento_id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Documento> optionalDocumento = documento_repositorio.findById(documento_id);
		if(optionalDocumento.isPresent()) {
			status = HttpStatus.OK;
			Documento documento = optionalDocumento.get();
			adicionadorLink.adicionarLink(documento);
			return ResponseEntity.status(status).body(documento);
		}
		return ResponseEntity.status(status).body("Documento não encontrado");
	}
	
	@GetMapping("/porCliente/{cliente_id}")
	public ResponseEntity<?> pegarDocumentosCliente(@PathVariable Long cliente_id) {
		HttpStatus status = HttpStatus.BAD_REQUEST;
		Optional<Cliente> optionalCliente = cliente_repositorio.findById(cliente_id);
		if(optionalCliente.isPresent()) {
			status = HttpStatus.OK;
			Cliente cliente = optionalCliente.get();
			List<Documento> documentos = cliente.getDocumentos();
			adicionadorLink.adicionarLink(documentos);
			return ResponseEntity.status(status).body(documentos);
		}
		return ResponseEntity.status(status).body("Cliente não encontrado");
	}
	
	@GetMapping
	public ResponseEntity<List<Documento>> listarTodosDocumentos(){
		List<Documento> documentos = documento_repositorio.findAll();
		adicionadorLink.adicionarLink(documentos);
		return ResponseEntity.ok(documentos);
	}
	
	@Transactional
	@PostMapping("/cliente/{cliente_id}")
	public ResponseEntity<?> cadastrarDocumento(@PathVariable Long cliente_id, @RequestBody Documento documento) {
		Optional<Cliente> optionalCliente = cliente_repositorio.findById(cliente_id);
		if(optionalCliente.isPresent()) {
			Cliente cliente = optionalCliente.get();
			List<Documento> listaDocumentos = cliente.getDocumentos();
			listaDocumentos.add(documento);
			cliente.setDocumentos(listaDocumentos);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().body("Cliente não encontrado");
		
	}
	
	@Transactional
	@PutMapping
	public ResponseEntity<?> atualizaDocumento(@RequestBody Documento documentoAtualizacao) {
		Optional<Documento> optionalDocumentoAtual = documento_repositorio.findById(documentoAtualizacao.getId());
		if(optionalDocumentoAtual.isPresent()) {
			Documento documentoAtual = optionalDocumentoAtual.get();
			DocumentoAtualizador atualizador = new DocumentoAtualizador();
			atualizador.atualizar(documentoAtual, documentoAtualizacao);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.badRequest().body("Documento não encontrado");
		
	}
	
	@Transactional
	@DeleteMapping("/{documento_id}/cliente/{cliente_id}")
	public ResponseEntity<?> deletaDocumetno(@PathVariable Long documento_id, @PathVariable Long cliente_id) {
		Optional<Documento> optionalDocumento = documento_repositorio.findById(documento_id);
		Optional<Cliente> optionalCliente = cliente_repositorio.findById(cliente_id);
		if(optionalCliente.isPresent()) {
			if(optionalDocumento.isPresent()) {
				Cliente cliente = optionalCliente.get();
				Documento documento = optionalDocumento.get();
				List<Documento> listaDocumento = cliente.getDocumentos();
				listaDocumento.remove(documento);
				cliente.setDocumentos(listaDocumento);
				return ResponseEntity.ok().build();
			}
			return ResponseEntity.badRequest().body("Documento não encontrado");
		}
		return ResponseEntity.badRequest().body("Cliente não encontrado");
	}
}
