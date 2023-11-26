package com.autobots.automanager.modelo;

import java.util.ArrayList;
import java.util.List;

import com.autobots.automanager.entidades.Telefone;

public class TelefoneAtualizador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Telefone telefone, Telefone atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getDdd())) {
				telefone.setDdd(atualizacao.getDdd());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				telefone.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Telefone> telefones, List<Telefone> atualizacoes) {
		List<Telefone> telefonesPersistidos = new ArrayList<Telefone>();
		List<Telefone> telefonesAdicionados = new ArrayList<Telefone>();
		
		for (Telefone atualizacao : atualizacoes) {
			if(!telefones.isEmpty()) {
				for (Telefone telefone : telefones) {
					if(atualizacoes.contains(telefone) && !telefonesPersistidos.contains(telefone)) {
						telefonesPersistidos.add(telefone);
					}
					if (atualizacao.getId() != null) {
						if (atualizacao.getId() == telefone.getId()) {
							atualizar(telefone, atualizacao);
							telefonesPersistidos.add(telefone);
						}
					}
					else {
						if(!telefonesAdicionados.contains(atualizacao)) {
							telefonesAdicionados.add(atualizacao);
							telefonesPersistidos.add(atualizacao);
						}
					}
				}
			}
			else {
				telefonesAdicionados.add(atualizacao);
				telefonesPersistidos.add(atualizacao);
			}
		}
		telefones.addAll(telefonesAdicionados);
		telefones.retainAll(telefonesPersistidos);
	}
}