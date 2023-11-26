package com.autobots.automanager.modelos.hateos.geradorLink;



import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.TelefoneControle;
import com.autobots.automanager.entidades.Telefone;

@Component
public class GeraLinkTelefone {
	
	public Link pegarTelefone(long telefone_id) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.pegarTelefone(telefone_id))
				.withRel("Pega_Telefone_por_Id");
		return link;
	}
	
	public Link pegarTelefonesCliente(long cliente_id) {
			Link link = WebMvcLinkBuilder
					.linkTo(WebMvcLinkBuilder
							.methodOn(TelefoneControle.class)
							.pegarTelefonesCliente(cliente_id))
					.withRel("Pega_Telefone_Desse_Cliente_por_Id");
			return link;
	}
	
	public Link listarTodosTelefones() {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.listarTodosTelefones())
				.withRel("Pega_Telefone_por_Id");
		return link;
				
	}
	
	public Link cadastrarTelefone(long cliente_id, Telefone telefone) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.cadastrarTelefone(cliente_id, telefone))
				.withRel("Cadastrar");
		return link;
	}
	
	public Link atualizaTelefone(Telefone telefoneAtualizacao) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.atualizaTelefone(telefoneAtualizacao))
				.withRel("Atualizar");
		return link;
	}
	
	public Link deletaTelefone(long telefone_id, long cliente_id) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(TelefoneControle.class)
						.deletaTelefone(telefone_id, cliente_id))
				.withRel("Deletar");
		return link;
	}
}
