package com.autobots.automanager.entitades;

import javax.persistence.Column;
import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
@Entity
public class CredencialUsuarioSenha extends Credencial {
	@Column(nullable = false, unique = true)
	private String login;
	@Column(nullable = false)
	private String senha;
	
	public CredencialUsuarioSenha() {}
	
	public CredencialUsuarioSenha(String login, String senha) {
		this.login = login;
		this.senha = senha;
		
	}
}