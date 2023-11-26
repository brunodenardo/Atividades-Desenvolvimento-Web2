package com.autobots.automanager.modelos.hateos.geradorLink;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.DocumentoControle;

import com.autobots.automanager.entidades.Documento;

@Component
public class GeraLinkDocumento {

	public Link pegarDocumento(long documento_id) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.pegarDocumento(documento_id))
				.withRel("Pega_Documento_por_Id");
		return link;
	}
	
	public Link pegarDocumentosCliente(long cliente_id) {
			Link link = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(DocumentoControle.class)
							.pegarDocumentosCliente(cliente_id))
					.withRel("Pega_Documento_Desse_Cliente_por_Id");
			return link;
	}
	
	public Link listarTodosDocumentos() {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.listarTodosDocumentos())
				.withRel("Pega_Documento_por_Id");
		return link;
				
	}
	
	public Link cadastrarDocumento(long cliente_id, Documento documento) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.cadastrarDocumento(cliente_id, documento))
				.withRel("Cadastrar");
		return link;
	}
	
	public Link atualizaDocumento(Documento documentoAtualizacao) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.atualizaDocumento(documentoAtualizacao))
				.withRel("Atualizar");
		return link;
	}
	
	public Link deletaDocumento(long documento_id, long cliente_id) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(DocumentoControle.class)
						.deletaDocumetno(documento_id, cliente_id))
				.withRel("Deletar");
		return link;
	}
}
