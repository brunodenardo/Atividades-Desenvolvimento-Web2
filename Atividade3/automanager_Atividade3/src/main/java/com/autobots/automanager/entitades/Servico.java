package com.autobots.automanager.entitades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.autobots.automanager.dto.DadosServico;

import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode
@Data
@Entity
public class Servico {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private double valor;
	
	@Column
	private String descricao;
	

	
	public Servico() {}
	
	public Servico(DadosServico dadosServico) {
		this.nome = dadosServico.nome();
		this.valor = dadosServico.valor();
		this.descricao = dadosServico.descricao();
	}
}