package com.autobots.automanager.entitades;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.autobots.automanager.dto.DadosMercadoria;

import lombok.Data;

@Data
@Entity
public class Mercadoria {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable = false)
	private Date validade;
	
	@Column(nullable = false)
	private Date fabricao;
	
	@Column(nullable = false)
	private Date cadastro;
	
	@Column(nullable = false)
	private String nome;
	
	@Column(nullable = false)
	private long quantidade;
	
	@Column(nullable = false)
	private double valor;
	
	@Column()
	private String descricao;
	
	public Mercadoria() {}
	
	public Mercadoria(DadosMercadoria dadosMercadoria) {
		this.validade = dadosMercadoria.validade();
		this.fabricao = dadosMercadoria.fabricao();
		this.cadastro = dadosMercadoria.cadastro();
		this.nome = dadosMercadoria.nome();
		this.quantidade = dadosMercadoria.quantidade();
		this.valor = dadosMercadoria.valor();
		this.descricao = dadosMercadoria.descricao();
	}
}