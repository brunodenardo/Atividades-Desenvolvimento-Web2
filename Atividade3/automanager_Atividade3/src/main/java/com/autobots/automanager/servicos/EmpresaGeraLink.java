package com.autobots.automanager.servicos;

import java.util.ArrayList;
import java.util.List;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Service;

import com.autobots.automanager.controles.EmpresaControle;


@Service
public class EmpresaGeraLink {

	public List<Link> gerar(){
		List<Link> lista = new ArrayList<Link>();
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.atualizarEmpresa(null))
				.withRel("Atualizar Empresa"));
		
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.deletarEmpresa(Long.valueOf(0)))
				.withRel("Deletar Empresa"));
		
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.cadastrarEmpresa(null))
				.withRel("Cadastrar Empresa"));
		
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.listarClientesEmpresa(Long.valueOf(0)))
				.withRel("Listar Clientes da Empresa"));
		
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.listarFornecedoresEmpresa(Long.valueOf(0)))
				.withRel("Listar Fornecedores da Empresa"));
		
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.listarFuncionariosEmpresa(Long.valueOf(0)))
				.withRel("Listar Funcionarios da Empresa"));
		
		lista.add(WebMvcLinkBuilder
				.linkTo(WebMvcLinkBuilder
						.methodOn(EmpresaControle.class)
						.atrelarUsuarioEmpresa(null))
				.withRel("Atrelar Usuário à Empresa"));
		
		return lista;
		
	}
}
