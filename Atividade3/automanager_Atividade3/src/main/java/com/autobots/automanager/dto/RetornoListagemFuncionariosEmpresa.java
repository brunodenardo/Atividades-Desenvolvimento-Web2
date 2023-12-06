package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoListagemFuncionariosEmpresa(
		List<DadosListagemFuncionariosEmpresa> listagemFuncionario,
		List<Link> linksRelacionados
		) {

}
