package com.autobots.automanager.entitades;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.autobots.automanager.dto.DadosVeiculo;
import com.autobots.automanager.enumeracoes.TipoVeiculo;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "proprietario", "vendas" })
@Entity
public class Veiculo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private TipoVeiculo tipo;
	
	@Column(nullable = false)
	private String modelo;
	
	@Column(nullable = false)
	private String placa;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Usuario proprietario;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Set<Venda> vendas = new HashSet<>();
	
	public Veiculo() {}
	
	public Veiculo(DadosVeiculo dadosVeiculo, Usuario usuario) {
		this.modelo = dadosVeiculo.modelo();
		this.placa = dadosVeiculo.placa();
		this.tipo = dadosVeiculo.tipo();
		this.proprietario = usuario;
	}
}