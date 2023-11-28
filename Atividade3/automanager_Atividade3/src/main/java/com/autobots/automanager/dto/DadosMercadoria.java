package com.autobots.automanager.dto;

import java.util.Date;

public record DadosMercadoria(
		Date validade,
		Date fabricao,
		Date cadastro,
		String nome,
		long quantidade,
		double valor,
		String descricao
		) {

}
