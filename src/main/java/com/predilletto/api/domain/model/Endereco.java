package com.predilletto.api.domain.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.annotation.Nonnull;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;


@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"cep","numero"}))
public class Endereco {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@JsonIgnore
	@ManyToOne
	@OneToOne
	private Pessoa pessoa;

	@Nonnull
	private String logradouro;

	@Nonnull
	@Size(min = 8, max = 9)
	private String cep;
	
	@Size(max=4)
	private String numero;
	
	@Nonnull
	private String cidade;  
	
	
	
	
}
