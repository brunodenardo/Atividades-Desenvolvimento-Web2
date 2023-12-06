package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosVeiculo;
import com.autobots.automanager.entitades.Usuario;

@Service
public class VeiculoListar {
	
	public List<DadosVeiculo> listarVeiculo(Usuario usuario){
		List<DadosVeiculo> lista = new ArrayList<DadosVeiculo>();
		usuario.getVeiculos().forEach((veiculo)->{
			lista.add(new DadosVeiculo(
					veiculo.getId(),
					veiculo.getTipo(),
					veiculo.getModelo(),
					veiculo.getPlaca()));
		});
		return lista;
	}
}
