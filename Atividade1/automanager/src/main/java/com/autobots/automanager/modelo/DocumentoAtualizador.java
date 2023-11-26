package com.autobots.automanager.modelo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import com.autobots.automanager.entidades.Documento;

public class DocumentoAtualizador {
	private StringVerificadorNulo verificador = new StringVerificadorNulo();

	public void atualizar(Documento documento, Documento atualizacao) {
		if (atualizacao != null) {
			if (!verificador.verificar(atualizacao.getTipo())) {
				documento.setTipo(atualizacao.getTipo());
			}
			if (!verificador.verificar(atualizacao.getNumero())) {
				documento.setNumero(atualizacao.getNumero());
			}
		}
	}

	public void atualizar(List<Documento> documentos, List<Documento> atualizacoes) {
		Collection<Documento> documentosPersistidos = new ArrayList<Documento>();
		Collection<Documento> documentosAdicionados = new ArrayList<Documento>();
		for (Documento atualizacao : atualizacoes) {
			if(!documentos.isEmpty()) {
				for (Documento documento : documentos) {
					if(atualizacoes.contains(documento) && !documentosPersistidos.contains(documento)) {
						documentosPersistidos.add(documento);
					}
					if (atualizacao.getId() != null) {
						if (atualizacao.getId() == documento.getId()) {
							atualizar(documento, atualizacao);
							documentosPersistidos.add(documento);
						}
					}
					else {
						if(!documentosAdicionados.contains(atualizacao)) {
							documentosAdicionados.add(atualizacao);
							documentosPersistidos.add(atualizacao);
						}
					}
				}
			}
			else {
				documentosAdicionados.add(atualizacao);
				documentosPersistidos.add(atualizacao);
			}
		}
		documentos.addAll(documentosAdicionados);
		documentos.retainAll(documentosPersistidos);
	}
}
