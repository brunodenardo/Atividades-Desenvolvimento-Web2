package com.autobots.automanager.modelos.hateos.geradorLink;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;

import com.autobots.automanager.controles.ClienteControle;
import com.autobots.automanager.entidades.Cliente;


@Component
public class GeraLinkCliente {
	
	
	public Link pegarCliente(long cliente_id) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ClienteControle.class)
						.obterCliente(cliente_id))
				.withRel("Pega_Cliente_por_Id");
		return link;
	}
	
	
	public Link listarTodosClientes() {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ClienteControle.class)
						.obterClientes())
				.withRel("Listar_todos_clientes");
		return link;
				
	}
	
	public Link cadastrarCliente(long cliente_id, Cliente cliente) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ClienteControle.class)
						.cadastrarCliente(cliente))
				.withRel("Cadastrar");
		return link;
	}
	
	public Link atualizaTelefone(Cliente clienteAtualizacao) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ClienteControle.class)
						.atualizarCliente(clienteAtualizacao))
				.withRel("Atualizar");
		return link;
	}
	
	public Link deletaCliente(Cliente cliente) {
		Link link = WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ClienteControle.class)
						.excluirCliente(cliente))
				.withRel("Deletar");
		return link;
	}
}
