package com.autobots.automanager.servicos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosAtualizacaoUsuario;
import com.autobots.automanager.entitades.Email;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@Service
public class UsuarioAtualizar {
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
	@Autowired
	private EmailAtualiza emailAtualiza;
	
	@Autowired
	private EnderecoAtualiza enderecoAtualiza;
	
	@Autowired
	private TelefoneAtualiza telefoneAtualiza;

	@Transactional
	public Boolean atualizarUsuario(DadosAtualizacaoUsuario dadosAtualizacao) {
		Optional<Usuario> optionalUsuario = repositorio_usuario.findById(dadosAtualizacao.id());
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			if(dadosAtualizacao.nome() != null) {
				usuario.setNome(dadosAtualizacao.nome());
			}
			if(dadosAtualizacao.nomeSocial() != null) {
				usuario.setNomeSocial(dadosAtualizacao.nomeSocial());
			}
			if(dadosAtualizacao.endereco() != null) {
				enderecoAtualiza.atualizar(usuario, dadosAtualizacao.endereco());
			}
			emailAtualiza.atualizaEmail(usuario, dadosAtualizacao.emails());
			telefoneAtualiza.atualizarListaTelefones(usuario, dadosAtualizacao.telefones());			
			
			return true;
		}
		return false;
	}
	
	
	
}
