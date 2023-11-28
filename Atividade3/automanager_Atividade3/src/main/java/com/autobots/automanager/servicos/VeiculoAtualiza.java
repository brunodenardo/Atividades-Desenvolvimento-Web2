package com.autobots.automanager.servicos;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.repositorios.RepositorioUsuario;

@Service
public class VeiculoAtualiza {
	
	@Autowired
	private RepositorioUsuario repositorio_usuario;

	public Boolean atualizarListaVeiculo(Long usuario_id, Set<Veiculo> veiculosAtualizar) {
		Optional<Usuario> optionalUsuario = repositorio_usuario.findById(usuario_id);
		if(optionalUsuario.isPresent()) {
			Usuario usuario = optionalUsuario.get();
			Set<Veiculo> veiculosMantidos = new HashSet<Veiculo>();
			Set<Veiculo> veiculosNovos = new HashSet<Veiculo>();
			for(Veiculo veiculo : veiculosAtualizar) {
				if(veiculo.getId() == null) {
					veiculosMantidos.add(veiculo);
					veiculosNovos.add(veiculo);
				}
				else {
					usuario.getVeiculos().forEach((veiculoAntigo)->{
						if(veiculoAntigo.getId() == veiculo.getId()) {
							veiculosMantidos.add(veiculoAntigo);
						}
					});
				}
			}
			usuario.getVeiculos().addAll(veiculosNovos);
			usuario.getVeiculos().retainAll(veiculosMantidos);
			return true;
		}
		return false;
	}
}
