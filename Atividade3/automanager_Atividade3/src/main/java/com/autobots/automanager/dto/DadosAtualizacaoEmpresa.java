package com.autobots.automanager.dto;


import java.util.List;


import com.autobots.automanager.entitades.Endereco;


public record DadosAtualizacaoEmpresa(
		Long id,
		String razaoSocial,
		String nomeFantasia,
		List<DadosTelefone> telefones,
 		DadosEndereco endereco
		) {

}
