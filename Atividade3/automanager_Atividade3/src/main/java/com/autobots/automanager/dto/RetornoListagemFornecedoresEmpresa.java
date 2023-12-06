package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoListagemFornecedoresEmpresa(
		List<DadosListagemFornecedoresEmpresa> listaFornecedores,
		List<Link> linksRelacionados
		) {

}
