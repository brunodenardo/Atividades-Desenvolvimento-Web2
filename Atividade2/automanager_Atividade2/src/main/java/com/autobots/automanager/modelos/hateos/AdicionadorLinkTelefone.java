package com.autobots.automanager.modelos.hateos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;


import com.autobots.automanager.entidades.Telefone;
import com.autobots.automanager.modelos.hateos.geradorLink.GeraLinkTelefone;

@Component
public class AdicionadorLinkTelefone implements AdicionadorLink<Telefone> {

	@Autowired
	private GeraLinkTelefone geraLink;
	
	@Override
	public void adicionarLink(List<Telefone> lista) {
		for (Telefone telefone : lista) {
			long id = telefone.getId();
			Link link = geraLink.pegarTelefone(id);
			telefone.add(link);
			link = geraLink.atualizaTelefone(telefone);
			telefone.add(link);
			link = geraLink.listarTodosTelefones();
			telefone.add(link);
		}
	}

	@Override
	public void adicionarLink(Telefone objeto) {
		Link link = geraLink.listarTodosTelefones();
		objeto.add(link);
		link = geraLink.atualizaTelefone(objeto);
		objeto.add(link);
		link = geraLink.pegarTelefone(objeto.getId());
		objeto.add(link);
	}
	

	public void adicionarLink(long cliente_id, List<Telefone> telefones) {
		for (Telefone telefone : telefones) {
			Link link = geraLink.cadastrarTelefone(cliente_id, telefone);
			telefone.add(link);
			link = geraLink.atualizaTelefone(telefone);
			telefone.add(link);
			link = geraLink.deletaTelefone(cliente_id, telefone.getId());
			telefone.add(link);
			link = geraLink.pegarTelefone(cliente_id);
			telefone.add(link);
			link = geraLink.pegarTelefonesCliente(cliente_id);
			telefone.add(link);
			link = geraLink.listarTodosTelefones();
			telefone.add(link);
		}
	}
	
	public void adicionarLink(long cliente_id, Telefone telefone) {
		Link link = geraLink.cadastrarTelefone(cliente_id, telefone);
		telefone.add(link);
		link = geraLink.atualizaTelefone(telefone);
		telefone.add(link);
		link = geraLink.deletaTelefone(cliente_id, telefone.getId());
		telefone.add(link);
		link = geraLink.pegarTelefone(cliente_id);
		telefone.add(link);
		link = geraLink.pegarTelefonesCliente(cliente_id);
		telefone.add(link);
		link = geraLink.listarTodosTelefones();
		telefone.add(link);
	}
	
}