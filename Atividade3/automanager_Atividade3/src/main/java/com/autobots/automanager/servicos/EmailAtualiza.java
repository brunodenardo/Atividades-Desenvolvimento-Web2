package com.autobots.automanager.servicos;

import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Usuario;

@Service
public class EmailAtualiza {
	
	
	@Transactional
	public void atualizaEmail(Usuario usuario, Set<Email> emailsAtualizacao) {
		Set<Email> emailAdicionados = new HashSet<Email>();
		Set<Email> emailMantidos = new HashSet<Email>();
		for(Email emailAtualizado : emailsAtualizacao) {
			if(emailAtualizado.getId() == null) {
				emailAdicionados.add(emailAtualizado);
				emailMantidos.add(emailAtualizado);
			}
			else {
				usuario.getEmails().forEach((emailAtual)->{
					System.out.println();
					if(emailAtual.getId() == emailAtualizado.getId()) {
						if(emailAtualizado.getEndereco() != null) {
							emailAtual.setEndereco(emailAtualizado.getEndereco());
							
						}
						emailMantidos.add(emailAtualizado);
					}
				});

			}
		}
		usuario.getEmails().addAll(emailAdicionados);
		usuario.getEmails().retainAll(emailMantidos);
	}
}
