package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.transaction.Transactional;


import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosTelefone;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.entitades.Usuario;


@Service
public class TelefoneAtualiza {
	
	
	@Transactional
	public void  atualizarListaTelefones(Empresa empresa, List<DadosTelefone> telefonesAtualizacao) {
		Set<Telefone> listaNaoDelecao = new HashSet<Telefone>();
		Set<Telefone> listaAdicao = new HashSet<Telefone>();
		telefonesAtualizacao.forEach((telefoneAtualizacao)->{
			if(telefoneAtualizacao.id() == null) {
				Telefone novoTelefone = new Telefone(telefoneAtualizacao);
				listaNaoDelecao.add(novoTelefone);
				listaAdicao.add(novoTelefone);
			}
			else {
				empresa.getTelefones().forEach((telefoneAtual)->{
					if(telefoneAtual.getId() == telefoneAtualizacao.id()) {
						listaNaoDelecao.add(this.atualizar(telefoneAtual, telefoneAtualizacao));
					}
				});
			}
		});
		this.deletar(empresa, listaNaoDelecao);
		empresa.getTelefones().addAll(listaAdicao);
	}
	
	@Transactional
	public void  atualizarListaTelefones(Usuario usuario, Set<Telefone> telefonesAtualizacao) {
		Set<Telefone> listaNaoDelecao = new HashSet<Telefone>();
		Set<Telefone> listaAdicao = new HashSet<Telefone>();
		telefonesAtualizacao.forEach((telefoneAtualizacao)->{
			if(telefoneAtualizacao.getId() == null) {
				listaNaoDelecao.add(telefoneAtualizacao);
				listaAdicao.add(telefoneAtualizacao);
			}
			else {
				usuario.getTelefones().forEach((telefoneAtual)->{
					if(telefoneAtual.getId() == telefoneAtualizacao.getId()) {
						listaNaoDelecao.add(this.atualizar(telefoneAtual, telefoneAtualizacao));
					}
				});
			}
		});
		this.deletar(usuario, listaNaoDelecao);
		usuario.getTelefones().addAll(listaAdicao);
	}
	
	@Transactional
	public Telefone atualizar(Telefone atual, DadosTelefone atualizacao) {
		if(atualizacao.ddd() != null) {
			atual.setDdd(atualizacao.ddd());
		}
		if(atualizacao.numero() != null) {
			atual.setNumero(atualizacao.numero());
		}
		return atual;
	}
	
	@Transactional
	public Telefone atualizar(Telefone atual, Telefone atualizacao) {
		if(atualizacao.getDdd() != null) {
			atual.setDdd(atualizacao.getDdd());
		}
		if(atualizacao.getNumero() != null) {
			atual.setNumero(atualizacao.getNumero());
		}
		return atual;
	}
	
	@Transactional
	public void deletar(Empresa empresa, Set<Telefone> atualizados) {
		List<Telefone> listaDeletaveis = new ArrayList<Telefone>();
		empresa.getTelefones().forEach((telefone)->{
			if(!atualizados.contains(telefone)) {
				listaDeletaveis.add(telefone);
			}
		});
		empresa.getTelefones().removeAll(listaDeletaveis);
	}
	
	@Transactional
	public void deletar(Usuario usuario, Set<Telefone> atualizados) {
		List<Telefone> listaDeletaveis = new ArrayList<Telefone>();
		usuario.getTelefones().forEach((telefone)->{
			if(!atualizados.contains(telefone)) {
				listaDeletaveis.add(telefone);
			}
		});
		usuario.getTelefones().removeAll(listaDeletaveis);
	}

	
}
