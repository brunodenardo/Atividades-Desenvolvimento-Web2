package com.autobots.automanager.dto;

import java.util.Set;

public record DadosCadastroVenda(
		Long id_venda,
		Long id_cliente,
		Long id_funcionario,
		Long id_veiculo,
		Set<Long> mercadoria,
		Set<Long> servico
		) {
	
}
