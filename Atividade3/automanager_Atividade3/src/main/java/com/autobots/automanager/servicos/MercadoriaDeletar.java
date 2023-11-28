package com.autobots.automanager.servicos;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Mercadoria;
import com.autobots.automanager.entitades.Servico;
import com.autobots.automanager.entitades.Usuario;

@Service
public class MercadoriaDeletar {
	
	public Boolean deletar(Empresa empresa, Long mercadoria_id) {
		for(Mercadoria mercadoria : empresa.getMercadorias()) {
			if(mercadoria.getId() == mercadoria_id) {
				empresa.getMercadorias().remove(mercadoria);
				return true;
			}
		}
		return false;
	}
	
	public Boolean deletar(Usuario fornecedor, Long mercadoria_id) {
		for(Mercadoria mercadoria : fornecedor.getMercadorias()) {
			if(mercadoria.getId() == mercadoria_id) {
				fornecedor.getMercadorias().remove(mercadoria);
				return true;
			}
		}
		return false;
	}
}
