package com.autobots.automanager.servicos;


import java.util.HashSet;

import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Veiculo;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.RepositorioMercadoria;
import com.autobots.automanager.repositorios.RepositorioServico;
import com.autobots.automanager.repositorios.RepositorioVenda;

@Service
public class VendaCadastrar {
	
	
	@Autowired
	private RepositorioMercadoria repositorio_mercadoria;
	
	@Autowired
	private RepositorioServico repositorio_servico;
	
	@Autowired
	private RepositorioVenda repositorio_venda;

	public void cadastrar(Set<Long> listaIdsMercadoria,
			Set<Long> listaIdsServico, Usuario funcionario, Usuario cliente, Veiculo veiculo) {
		Set<Mercadoria> mercadoria = this.gerarListaMercadoria(listaIdsMercadoria);
		Set<Servico> servico = this.gerarListaServico(listaIdsServico);
		Venda venda = new Venda(mercadoria, servico, cliente, funcionario, veiculo);
		System.out.println("TESTE: " + venda.getMercadorias());
		repositorio_venda.save(venda);
	}
	
	private Set<Mercadoria> gerarListaMercadoria(Set<Long> listaIdsMercadoria){
		Set<Mercadoria> lista = new HashSet<Mercadoria>();
		listaIdsMercadoria.forEach((id)->{
			Optional<Mercadoria> mercadoriaOptional = repositorio_mercadoria.findById(id);
			if(mercadoriaOptional.isPresent()) {
				lista.add(mercadoriaOptional.get());
			}
		});
		return lista;
	}
	
	private Set<Servico> gerarListaServico(Set<Long> listaIdsServico){
		Set<Servico> lista = new HashSet<Servico>();
		listaIdsServico.forEach((id)->{
			Optional<Servico> servicoOptional = repositorio_servico.findById(id);
			if(servicoOptional.isPresent()) {
				lista.add(servicoOptional.get());
			}
		});
		return lista;
	}
}
