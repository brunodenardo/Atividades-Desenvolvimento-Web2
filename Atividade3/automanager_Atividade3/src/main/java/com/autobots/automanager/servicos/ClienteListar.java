package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosListagemClientesEmpresa;
import com.autobots.automanager.dto.DadosVeiculo;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@Service
public class ClienteListar {

	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	public List<DadosListagemClientesEmpresa> listarClientes(Long empresa_id){
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		List<DadosListagemClientesEmpresa> listaDados = new ArrayList<DadosListagemClientesEmpresa>();
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			empresa.getUsuarios().forEach((usuario)->{
				if(usuario.getPerfis().contains(PerfilUsuario.CLIENTE)) {
					listaDados.add(
							new DadosListagemClientesEmpresa(
									usuario.getId(),
									usuario.getNome(),
									usuario.getNomeSocial(),
									usuario.getPerfis(),
									usuario.getTelefones(),
									usuario.getEmails(),
									this.listaVeiculos(usuario)
									));
				}
			});
			return listaDados;
		}
		return null;
	}
	
	public Set<DadosVeiculo> listaVeiculos(Usuario usuario) {
		Set<DadosVeiculo> listaVeiculos = new HashSet<DadosVeiculo>();
		usuario.getVeiculos().forEach((veiculo)->{
			listaVeiculos.add(
					new DadosVeiculo(
							veiculo.getId(),
							veiculo.getTipo(),
							veiculo.getModelo(),
							veiculo.getPlaca())
					);
		});
		return listaVeiculos;
	}
	
}
