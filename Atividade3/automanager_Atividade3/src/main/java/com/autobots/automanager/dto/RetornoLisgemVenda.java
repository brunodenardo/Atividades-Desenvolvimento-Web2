package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoLisgemVenda(
		List<DadosVenda> listaVendas,
		List<Link> linksRelacionados
		) {

}
