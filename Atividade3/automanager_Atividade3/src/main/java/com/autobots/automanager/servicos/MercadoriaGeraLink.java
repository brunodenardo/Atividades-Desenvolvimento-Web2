package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.MercadoriaControle;


@Service
public record MercadoriaGeraLink() {

	public List<Link> gerar(){
		List<Link> links = new ArrayList<Link>();
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.listarMercadoriaEmpresa(Long.valueOf(0)))
				.withRel("Listar Mercadorias de uma Empresa"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.listarMercadoriaFornecedor(Long.valueOf(0)))
				.withRel("Listar Mercadorias de um Fornecedor"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.cadastrarMercadoriaEmpresa(Long.valueOf(0), null))
				.withRel("Cadastrar Mercadoria de uma Empresa"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.cadastrarMercadoriaFornecedor(Long.valueOf(0), null))
				.withRel("Cadastrar Mercadoria de um Fornecedor"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.deletarRelacaoMercadoriaEmpresa(Long.valueOf(0), Long.valueOf(0)))
				.withRel("Deletar Mercadoria de uma Empresa"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(MercadoriaControle.class)
						.deletarRelacaoMercadoriaFornecedor(Long.valueOf(0), Long.valueOf(0)))
				.withRel("Deletar Mercadoria de um Fornecedor"));
		
		return links;
	}
	
}
