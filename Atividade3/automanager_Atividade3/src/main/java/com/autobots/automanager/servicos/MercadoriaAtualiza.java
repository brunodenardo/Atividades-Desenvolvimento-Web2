package com.autobots.automanager.servicos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@Service
public class MercadoriaAtualiza {
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;

	@Transactional
	public Boolean atualizarListaMercadoria(Long usuario_id, Set<Mercadoria> mercadoriaAtualizar) {
		Optional<Usuario> optionalUsuario = repositorio_usuario.findById(usuario_id);
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Set<Mercadoria> mercadoriaMantidos = new HashSet<Mercadoria>();
			Set<Mercadoria> mercadoriaNovos = new HashSet<Mercadoria>();
			for(Mercadoria mercadoria : mercadoriaAtualizar) {
				if(mercadoria.getId() == null) {
					mercadoriaMantidos.add(mercadoria);
					mercadoriaNovos.add(mercadoria);
				}
				else {
					usuario.getMercadorias().forEach((mercadoriaAntigo)->{
						if(mercadoriaAntigo.getId() == mercadoria.getId()) {
							mercadoriaMantidos.add(mercadoriaAntigo);
						}
					});
				}
			}
			usuario.getMercadorias().addAll(mercadoriaNovos);
			usuario.getMercadorias().retainAll(mercadoriaMantidos);
			return true;
		}
		return false;
	}
}
