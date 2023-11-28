package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosListagemFuncionariosEmpresa;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@Service
public class FuncionariosListar {

	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	public List<DadosListagemFuncionariosEmpresa> listarFuncionarios(Long empresa_id){
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		List<DadosListagemFuncionariosEmpresa> listaDados = new ArrayList<DadosListagemFuncionariosEmpresa>();
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			empresa.getUsuarios().forEach((usuario)->{
				if(usuario.getPerfis().contains(PerfilUsuario.FUNCIONARIO)) {
					listaDados.add(
							new DadosListagemFuncionariosEmpresa(
									usuario.getId(),
									usuario.getNome(),
									usuario.getNomeSocial(),
									usuario.getPerfis(),
									usuario.getTelefones(),
									usuario.getEmails()));
				}
			});
			return listaDados;
		}
		return null;
	}
}
