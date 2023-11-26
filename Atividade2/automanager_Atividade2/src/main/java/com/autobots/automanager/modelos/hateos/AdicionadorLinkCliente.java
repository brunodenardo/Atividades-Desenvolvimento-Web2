package com.autobots.automanager.modelos.hateos;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;


import com.autobots.automanager.entidades.Cliente;
import com.autobots.automanager.modelos.hateos.geradorLink.GeraLinkCliente;


@Component
public class AdicionadorLinkCliente implements AdicionadorLink<Cliente> {

	
	@Autowired
	private GeraLinkCliente geraLink;
	
	@Override
	public void adicionarLink(List<Cliente> lista) {
		for (Cliente cliente : lista) {
			long id = cliente.getId();
			Link link = geraLink.cadastrarCliente(id, cliente);
			cliente.add(link);
			link = geraLink.atualizaTelefone(cliente);
			cliente.add(link);
			link = geraLink.deletaCliente(cliente);
			cliente.add(link);
			link = geraLink.pegarCliente(id);
			cliente.add(link);
			link = geraLink.listarTodosClientes();
			
		}
	}

	@Override
	public void adicionarLink(Cliente cliente) {
		long id = cliente.getId();
		Link link = geraLink.cadastrarCliente(id, cliente);
		cliente.add(link);
		link = geraLink.atualizaTelefone(cliente);
		cliente.add(link);
		link = geraLink.deletaCliente(cliente);
		cliente.add(link);
		link = geraLink.listarTodosClientes();
		cliente.add(link);
		link = geraLink.pegarCliente(id);
	}


}