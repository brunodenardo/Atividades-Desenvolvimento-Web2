package com.autobots.automanager.dto;

import java.util.List;

import org.springframework.hateoas.Link;

public record RetornoListagemUsuario(
		List<DadosListagemUsuario> listaUsuario,
		List<Link> linnksRelacionados
		) {

}
