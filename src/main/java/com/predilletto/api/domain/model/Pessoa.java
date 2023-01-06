package com.predilletto.api.domain.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Pessoa {
	
	@Id
	@EqualsAndHashCode.Include
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id; 
	
	@Size(max = 80)
	@NotNull  
	@NotBlank
	private String nome;
	
	@NotNull
	@JsonFormat(pattern="dd-MM-yyyy")
	private LocalDate dataNasc;
	
	
	@OneToOne
	private Endereco enderecoPrincipal;
	
	@JsonIgnore
	@OneToMany(mappedBy = "pessoa")
	private List<Endereco> enderecos= new ArrayList<>();
			
	
	public Endereco adicionarEndereco (Endereco endereco) {
		if (this.getEnderecoPrincipal()==null) { 
			this.setEnderecoPrincipal(endereco);
		}
		this.getEnderecos().add(endereco);
		return endereco;
	}
	


}
