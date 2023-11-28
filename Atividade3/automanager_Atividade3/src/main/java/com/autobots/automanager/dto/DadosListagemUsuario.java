package com.autobots.automanager.dto;

import java.util.Set;

import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.enumeracoes.PerfilUsuario;

public record DadosListagemUsuario(
		Long id,
		String nome,
		String nomeSocial,
		Set<PerfilUsuario> perfis,
		Set<Telefone> telefones,
		Set<Email> emails
		) {

}
