package com.autobots.automanager.servicos;

import java.util.Optional;
import java.util.Set;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosAtualizacaoEmpresa;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Telefone;
import com.autobots.automanager.repositorios.RepositorioEmpresa;

@Service
public class EmpresaAtualiza {
	
	@Autowired
	private RepositorioEmpresa repositorio_empresa;
	
	@Autowired
	private TelefoneAtualiza telefoneAtualiza;
	
	@Autowired
	private EnderecoAtualiza enderecoAtualiza;
	
	@Transactional
	public Boolean atualizar(DadosAtualizacaoEmpresa dadosAtualizacao) {
		Optional<Empresa> optionalEmpresa = repositorio_empresa.findById(dadosAtualizacao.id());
		if(optionalEmpresa.isPresent()) {
			Empresa empresa = optionalEmpresa.get();
			if(dadosAtualizacao.endereco() != null) {
				enderecoAtualiza.atualizar(empresa, dadosAtualizacao.endereco());
			}
			if(dadosAtualizacao.nomeFantasia().isBlank()) {
				empresa.setNomeFantasia(dadosAtualizacao.nomeFantasia());
			}
			if(!dadosAtualizacao.razaoSocial().isBlank()) {
				empresa.setRazaoSocial(dadosAtualizacao.razaoSocial());
			}
			if(!dadosAtualizacao.telefones().isEmpty()) {
				telefoneAtualiza.atualizarListaTelefones(empresa, dadosAtualizacao.telefones());
			}
			return true;
		}
		return false;
	}
}
