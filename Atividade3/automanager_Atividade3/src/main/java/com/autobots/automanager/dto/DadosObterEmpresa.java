package com.autobots.automanager.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.Link;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.entitades.Telefone;

public record DadosObterEmpresa(
		Long id,
		String razaoSocial,
		String nomeFantasia,
		Set<Telefone> telefones,
		Endereco endereco,
		Date cadastro,
		Set<Mercadoria> mercadorias,
		Set<Servico> servicos,
		List<Link> linksRelacionados
		) {
	
}
