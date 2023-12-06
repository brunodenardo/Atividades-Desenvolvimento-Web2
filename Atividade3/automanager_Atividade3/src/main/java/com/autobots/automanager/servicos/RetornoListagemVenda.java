package com.autobots.automanager.servicos;

import java.util.List;

import org.springframework.hateoas.Link;

import com.autobots.automanager.dto.DadosVenda;

public record RetornoListagemVenda(
		List<DadosVenda> listaVenda,
		List<Link> linksRelacionados
		) {

}
