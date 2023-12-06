package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.UsuarioControle;

@Service
public class UsuarioGeraLink {

	public List<Link> gerar(){
		List<Link> links = new ArrayList<Link>();
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.listarTodosUsuarios())
				.withRel("Listar todos os usuários"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.obterUsuario(Long.valueOf(0)))
				.withRel("Obter usuário"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.cadastrarUsuario(null))
				.withRel("Cadastro Usuários"));
	
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.alterarListaVeiculos(Long.valueOf(0), null))
				.withRel("Alterar lista de Veiculos do Usuário"));
	
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.atualizarUsuario(null))
				.withRel("Atualizar Usuário"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.alterarListaMercadoria(Long.valueOf(0), null))
				.withRel("Alterar Lista de Mercadorias do Usuário"));
	
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(UsuarioControle.class)
						.deletarUsuario(Long.valueOf(0)))
				.withRel("Deletar Usuários"));
		
		return links;
	}
	
}
