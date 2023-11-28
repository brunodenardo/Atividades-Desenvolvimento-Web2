package com.autobots.automanager.entitades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.autobots.automanager.dto.DadosTelefone;

import lombok.Data;

@Data
@Entity
public class Telefone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String ddd;
	@Column(nullable = false)
	private String numero;
	
	public Telefone() {}
	
	public Telefone(DadosTelefone dadosTelefone) {
		this.ddd = dadosTelefone.ddd();
		this.numero = dadosTelefone.numero();
	}
}