package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.VendasControle;

@Service
public class VendaGeraLink {

	public List<Link> gerar(){
		List<Link> links = new ArrayList<Link>();
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendasControle.class)
						.cadastrarVenda(null))
				.withRel("Cadastro Venda"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendasControle.class)
						.listarTodasVendas())
				.withRel("Listar Todas Vendas"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendasControle.class)
						.listarVenda(Long.valueOf(0)))
				.withRel("Listar uma Venda"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendasControle.class)
						.listarVendasClientes(Long.valueOf(0)))
				.withRel("Listar Compras do Cliente"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VendasControle.class)
						.listarVendasFuncionario(Long.valueOf(0)))
				.withRel("Listar Vendas do Funcionário"));
		
		return links;
	}
	
}
