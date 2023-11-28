package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosListagemEmpresa;
import com.autobots.automanager.dto.DadosListagemFuncionariosEmpresa;
import com.autobots.automanager.dto.DadosObterEmpresa;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@Service
public class EmpresaListar {
	
	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	public List<DadosListagemEmpresa> listarTodos() {
		List<Empresa> empresas = repositorio_empresa.findAll();
		List<DadosListagemEmpresa> listaEmpresas = new ArrayList<DadosListagemEmpresa>();
		empresas.forEach((empresa)->{
			listaEmpresas.add(
					new DadosListagemEmpresa(
							empresa.getId(),
							empresa.getRazaoSocial(),
							empresa.getNomeFantasia(),
							empresa.getTelefones(),
							empresa.getEndereco(),
							empresa.getCadastro()));
		});
		return listaEmpresas;
	}
	
	public DadosObterEmpresa obterEmpresa(Long empresa_id) {
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			DadosObterEmpresa dadosEmpresa =
					new DadosObterEmpresa(
							empresa.getId(),
							empresa.getRazaoSocial(),
							empresa.getNomeFantasia(),
							empresa.getTelefones(),
							empresa.getEndereco(),
							empresa.getCadastro(),
							empresa.getMercadorias(),
							empresa.getServicos());
			return dadosEmpresa;
		}
		return null;
	}
	
	
}
