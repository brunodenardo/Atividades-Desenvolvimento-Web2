package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosListagemFornecedoresEmpresa;
import com.autobots.automanager.dto.DadosMercadoria;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.enumeracoes.PerfilUsuario;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@Service
public class FornecedoresListar {

	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	public List<DadosListagemFornecedoresEmpresa> listarFornecedores(Long empresa_id){
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(empresa_id);
		List<DadosListagemFornecedoresEmpresa> listaDados = new ArrayList<DadosListagemFornecedoresEmpresa>();
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			empresa.getUsuarios().forEach((usuario)->{
				if(usuario.getPerfis().contains(PerfilUsuario.FORNECEDOR)) {
					listaDados.add(
							new DadosListagemFornecedoresEmpresa(
									usuario.getId(),
									usuario.getNome(),
									usuario.getNomeSocial(),
									usuario.getPerfis(),
									usuario.getTelefones(),
									usuario.getEmails(),
									this.listarMercadoriasFornecedores(usuario)
									));
				}
			});
			return listaDados;
		}
		return null;
	}
	
	public Set<DadosMercadoria> listarMercadoriasFornecedores(Usuario usuario){
		Set<DadosMercadoria> mercadorias = new HashSet<DadosMercadoria>();
		usuario.getMercadorias().forEach((mercadoria)->{
			mercadorias.add(
					new DadosMercadoria(
							mercadoria.getValidade(),
							mercadoria.getFabricao(),
							mercadoria.getCadastro(),
							mercadoria.getNome(),
							mercadoria.getQuantidade(),
							mercadoria.getValor(),
							mercadoria.getDescricao()
							));
		});
		return mercadorias;
	}
}
