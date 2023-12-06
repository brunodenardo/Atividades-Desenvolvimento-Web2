package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.VeiculoControle;

@Service
public class VeiculoGeraLink {

	public List<Link> gerar(){
		List<Link> links = new ArrayList<Link>();
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.cadastraVeiculos(Long.valueOf(0), null))
				.withRel("Cadastro Veiculo"));
				
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.deletarVeiculo(Long.valueOf(0)))
				.withRel("Deleta Veiculo"));
		
		links.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(VeiculoControle.class)
						.listaVeiculos(Long.valueOf(Long.valueOf(0))))
				.withRel("Lista veiculo de um cliente"));
		
		return links;
	}
}
