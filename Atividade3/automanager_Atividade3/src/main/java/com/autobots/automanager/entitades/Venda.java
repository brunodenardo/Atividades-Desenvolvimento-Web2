package com.autobots.automanager.entitades;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.autobots.automanager.dto.DadosVeiculo;
import com.autobots.automanager.dto.DadosVenda;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(exclude = { "cliente", "funcionario", "veiculo" })
@Entity
public class Venda {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date cadastro;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Usuario cliente;
	
	@ManyToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Usuario funcionario;
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
        name = "venda_mercadoria",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "mercadoria_id")
    )
	private Set<Mercadoria> mercadorias = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
    @JoinTable(
        name = "venda_servico",
        joinColumns = @JoinColumn(name = "venda_id"),
        inverseJoinColumns = @JoinColumn(name = "servico_id")
    )
	private Set<Servico> servicos = new HashSet<>();
	
	@OneToOne(fetch = FetchType.EAGER, cascade = { CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH })
	private Veiculo veiculo;
	
	public Venda() {}
	
	public Venda(Set<Mercadoria> mercadoria, Set<Servico> servico, Usuario cliente, Usuario funcionario, Veiculo veiculo) {
		this.cadastro = new Date();
		this.cliente = cliente;
		this.funcionario = funcionario;
		this.mercadorias = mercadoria;
		this.servicos = servico;
		this.veiculo = veiculo;	
	}
}