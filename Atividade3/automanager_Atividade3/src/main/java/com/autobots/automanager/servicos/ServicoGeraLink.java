package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.ServicoControle;

@Service
public class ServicoGeraLink {

	public List<Link> gerar(){
		List<Link> links = new ArrayList<Link>();
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServicoControle.class)
						.listarServicos(Long.valueOf(0)))
				.withRel("Listar Serviços providos pela Empresa"));

		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServicoControle.class)
						.cadastrarServico(Long.valueOf(0), null))
				.withRel("Cadastrar Serviço de uma empresa Empresa"));
		

		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(ServicoControle.class)
						.deletarRelacaoServicoEmpresa(Long.valueOf(0), Long.valueOf(0)))
				.withRel("Deletar um Serviço providos pela Empresa"));
		
		return links;
	}
	
}
