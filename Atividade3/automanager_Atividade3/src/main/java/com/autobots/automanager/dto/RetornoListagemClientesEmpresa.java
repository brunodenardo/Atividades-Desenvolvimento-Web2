package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoListagemClientesEmpresa(
		List<DadosListagemClientesEmpresa> listagemCliente,
		List<Link> linkesRelacionados
		) {

}
