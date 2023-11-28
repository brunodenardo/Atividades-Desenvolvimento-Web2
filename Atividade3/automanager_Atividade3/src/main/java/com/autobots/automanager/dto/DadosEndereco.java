package com.autobots.automanager.dto;

public record DadosEndereco(
		String estado,
		String cidade,
		String bairro,
		String rua,
		String numero,
		String codigoPostal,
		String informacoesAdicionais
		) {

}
