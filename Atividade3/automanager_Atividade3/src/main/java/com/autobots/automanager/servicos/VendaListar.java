package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosVenda;
import com.autobots.automanager.entitades.Venda;
import com.autobots.automanager.repositorios.RepositorioVenda;

@Service
public class VendaListar {
	
	@Autowired
	private RepositorioVenda repositorio_venda;

	
	public List<DadosVenda> listarTudo(){
		List<Venda> vendas = repositorio_venda.findAll();
		List<DadosVenda> listagem = this.converterLista(vendas);
		return listagem;
	}
	
	public List<DadosVenda> converterLista(List<Venda> vendas){
		List<DadosVenda> listagem = new ArrayList<DadosVenda>();
		vendas.forEach((venda)->{
			if(venda.getVeiculo() != null) {
				listagem.add(
						new DadosVenda(
								venda.getId(),
								venda.getCliente().getId(),
								venda.getFuncionario().getId(),
								venda.getVeiculo().getId(),
								venda.getCadastro(),
								venda.getMercadorias(),
								venda.getServicos()
								)
						);
			}
			else {
				listagem.add(
						new DadosVenda(
								venda.getId(),
								venda.getCliente().getId(),
								venda.getFuncionario().getId(),
								null,
								venda.getCadastro(),
								venda.getMercadorias(),
								venda.getServicos()
								)
						);
			}
		});
		return listagem;
	}
}
