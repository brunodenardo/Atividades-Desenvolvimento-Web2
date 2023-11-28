package com.autobots.automanager.servicos;

import org.springframework.stereotype.Service;

import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Servico;

@Service
public class ServicoDeletar {
	
	public Boolean deletar(Empresa empresa, Long servico_id) {
		for(Servico servico : empresa.getServicos()) {
			if(servico.getId() == servico_id) {
				empresa.getServicos().remove(servico);
				return true;
			}
		}
		return false;
	}
}
