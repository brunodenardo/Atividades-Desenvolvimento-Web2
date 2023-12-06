package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoListagemVeiculo(
		List<DadosVeiculo> listaVeiculos,
		List<Link> linksRelacionados
		) {

}
