package com.autobots.automanager.dto;

import com.autobots.automanager.enumeracoes.TipoVeiculo;

public record DadosVeiculo(
		Long id,
		TipoVeiculo tipo,
		String modelo,
		String placa
		) {

}
