package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoListagemEmpresa(
		List<DadosListagemEmpresa> listagemEmpresas,
		List<Link> linksRelacionados
		) {

}
