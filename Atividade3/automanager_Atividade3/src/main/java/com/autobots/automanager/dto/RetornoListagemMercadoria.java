package com.autobots.automanager.dto;

import java.util.List;
import java.util.Set;

import org.springframework.hateoas.Link;

import com.autobots.automanager.entitades.Mercadoria;

public record RetornoListagemMercadoria(
		Set<Mercadoria> listaMercadoria,
		List<Link> linksRelacionados
		) {

}
