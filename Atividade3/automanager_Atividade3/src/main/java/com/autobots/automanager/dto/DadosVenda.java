package com.autobots.automanager.dto;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.Link;

import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Servico;

public record DadosVenda(
		Long id_venda,
		Long id_cliente,
		Long id_funcionario,
		Long id_veiculo,
		Date cadastro,
		Set<Mercadoria> mercadoria,
		Set<Servico> servico
		) {

}
