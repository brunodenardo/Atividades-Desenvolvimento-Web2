package com.autobots.automanager.dto;

import java.util.Date;
import java.util.Set;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.entitades.Telefone;

public record DadosListagemEmpresa(
		Long id,
		String razaoSocial,
		String nomeFantasia,
		Set<Telefone> telefones,
 		Endereco endereco,
 		Date cadastro
		) {

}
