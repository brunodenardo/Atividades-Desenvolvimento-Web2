package com.autobots.automanager.dto;

import java.util.Set;

import com.autobots.automanager.entitades.Documento;
import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.enumeracoes.PerfilUsuario;

public record DadosAtualizacaoUsuario(
		Long id,
		String nome,
		String nomeSocial,
		Set<Telefone> telefones,
		Endereco endereco,
		Set<Email> emails
		) {

}
