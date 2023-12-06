package com.autobots.automanager.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.autobots.automanager.entitades.Usuario;
import com.autobots.automanager.entitades.Venda;

public interface RepositorioVenda extends JpaRepository<Venda, Long>{

	public List<Venda> findByCliente(Usuario usuario);
	
	public List<Venda> findByFuncionario(Usuario usuario);
}
