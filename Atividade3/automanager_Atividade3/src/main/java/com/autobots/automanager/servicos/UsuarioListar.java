package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosListagemUsuario;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@Service
public class UsuarioListar {

	@Autowired
	private RepositorioUsuario repositorio_usuario;
	
	public List<DadosListagemUsuario> listarTodos(){
		List<Usuario> usuarios = repositorio_usuario.findAll();
		List<DadosListagemUsuario> listaUsuarios = new ArrayList<DadosListagemUsuario>();
		usuarios.forEach((usuario)->{
			listaUsuarios.add(
					new DadosListagemUsuario(
							usuario.getId(),
							usuario.getNome(),
							usuario.getNomeSocial(),
							usuario.getPerfis(),
							usuario.getTelefones(),
							usuario.getEmails())
					);
		});
		return listaUsuarios;
	}
}
