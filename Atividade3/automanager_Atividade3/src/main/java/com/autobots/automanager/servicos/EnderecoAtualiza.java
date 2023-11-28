package com.autobots.automanager.servicos;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.autobots.automanager.dto.DadosEndereco;
import com.autobots.automanager.entitades.Empresa;
import com.autobots.automanager.entitades.Endereco;
import com.autobots.automanager.entitades.Usuario;


@Service
public class EnderecoAtualiza {
	
	public void atualizar(Empresa empresa, DadosEndereco enderecoAtualizado) {
		if(enderecoAtualizado.bairro() != null) {
			empresa.getEndereco().setBairro(enderecoAtualizado.bairro());
		}
		if(enderecoAtualizado.cidade() != null) {
			empresa.getEndereco().setCidade(enderecoAtualizado.cidade());
		}
		if(enderecoAtualizado.codigoPostal() != null) {
			empresa.getEndereco().setCodigoPostal(enderecoAtualizado.codigoPostal());
		}
		if(enderecoAtualizado.estado() != null) {
			empresa.getEndereco().setEstado(enderecoAtualizado.estado());
		}
		if(enderecoAtualizado.informacoesAdicionais() != null) {
			empresa.getEndereco().setInformacoesAdicionais(enderecoAtualizado.informacoesAdicionais());
		}
		if(enderecoAtualizado.numero() != null) {
			empresa.getEndereco().setNumero(enderecoAtualizado.numero());
		}
		if(enderecoAtualizado.rua() != null) {
			empresa.getEndereco().setRua(enderecoAtualizado.rua());
		}
	}
	
	public void atualizar(Usuario usuario, Endereco enderecoAtualizado) {
		if(enderecoAtualizado.getBairro() != null) {
			usuario.getEndereco().setBairro(enderecoAtualizado.getBairro());
		}
		if(enderecoAtualizado.getCidade() != null) {
			usuario.getEndereco().setCidade(enderecoAtualizado.getCidade());
		}
		if(enderecoAtualizado.getCodigoPostal() != null) {
			usuario.getEndereco().setCodigoPostal(enderecoAtualizado.getCodigoPostal());
		}
		if(enderecoAtualizado.getEstado() != null) {
			usuario.getEndereco().setEstado(enderecoAtualizado.getEstado());
		}
		if(enderecoAtualizado.getInformacoesAdicionais() != null) {
			usuario.getEndereco().setInformacoesAdicionais(enderecoAtualizado.getInformacoesAdicionais());
		}
		if(enderecoAtualizado.getNumero() != null) {
			usuario.getEndereco().setNumero(enderecoAtualizado.getNumero());
		}
		if(enderecoAtualizado.getRua() != null) {
			usuario.getEndereco().setRua(enderecoAtualizado.getRua());
		}
	}
}
