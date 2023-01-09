package com.predilletto.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.predilletto.api.domain.model.Endereco;
import com.predilletto.api.services.EnderecosServices;

@RestController
@CrossOrigin
@RequestMapping("/api/pessoas")
public class EnderecoController {
	
	@Autowired
	private EnderecosServices enderecosServices;
	
	@PostMapping("/{pessoaId}/enderecos")
	@ResponseStatus(HttpStatus.CREATED)
	public Endereco adicionar(@PathVariable Long pessoaId, @RequestBody Endereco endereco) {

		return enderecosServices.criarEndereco(pessoaId,endereco);
	}
	
	@GetMapping("/{pessoaId}/enderecos")
	public List<Endereco> listar(@PathVariable Long pessoaId) {
		return enderecosServices.listar(pessoaId);
	}
	

	
}
