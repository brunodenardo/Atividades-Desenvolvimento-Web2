package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoVenda(
		DadosVenda venda,
		List<Link> linksRelacionados
		) {

}
