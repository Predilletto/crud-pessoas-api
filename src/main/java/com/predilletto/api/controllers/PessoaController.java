package com.predilletto.api.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.predilletto.api.domain.model.Pessoa;
import com.predilletto.api.services.PessoaServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	private PessoaServices pessoaServices;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Pessoa adicionar(@Valid @RequestBody Pessoa pessoa) {

		return pessoaServices.salvar(pessoa);
	}

	@GetMapping
	public List<Pessoa> listar() {
		return pessoaServices.listar();
	}
	
	@GetMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> obter (@PathVariable Long pessoaId) {
		return pessoaServices.buscar(pessoaId)
				.map(ResponseEntity::ok)
				.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping("/{pessoaId}")
	public ResponseEntity<Pessoa> atualizar (@PathVariable Long pessoaId, @Valid @RequestBody Pessoa pessoa) {
		return pessoaServices.atualizar(pessoaId, pessoa);
	}
	
	@PatchMapping("/{pessoaId}/enderecos/{enderecoId}")
	public ResponseEntity<Pessoa> alterarEnderecoPrincipal (@PathVariable Long pessoaId, @PathVariable Long enderecoId) {
		return pessoaServices.alterarEnderecoPrincipal(pessoaId, enderecoId);
	}

	
}
