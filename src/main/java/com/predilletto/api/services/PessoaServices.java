package com.predilletto.api.services;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.predilletto.api.domain.model.Endereco;
import com.predilletto.api.domain.model.Pessoa;
import com.predilletto.api.repository.EnderecoRepository;
import com.predilletto.api.repository.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@AllArgsConstructor
@Service
public class PessoaServices {

	
	private PessoaRepository pessoaRepository;
	
	private EnderecoRepository enderecoRepository; 

	@Transactional
	public Pessoa salvar(Pessoa pessoa) {
		return pessoaRepository.save(pessoa);
	}

	public Optional<Pessoa> buscar(Long pessoaId) {
		return pessoaRepository.findById(pessoaId);
	}

	public List<Pessoa> listar() {
		return pessoaRepository.findAll();
	}
	
	public ResponseEntity<Pessoa> atualizar (Long pessoaId, Pessoa pessoa){ 
		if (!pessoaRepository.existsById(pessoaId)) {
			return ResponseEntity.notFound().build();
		}
		pessoa.setId(pessoaId);
		salvar(pessoa);
		return ResponseEntity.ok(pessoa);
	}
	
	public ResponseEntity<Pessoa> alterarEnderecoPrincipal (Long pessoaId, Long enderecoId) {
		if (!pessoaRepository.existsById(pessoaId)||!enderecoRepository.existsById(enderecoId)) {
			return ResponseEntity.notFound().build();
		}
		Pessoa pessoa= pessoaRepository.findById(pessoaId).get();
		Endereco endereco= enderecoRepository.findById(enderecoId).get();
		pessoa.setEnderecoPrincipal(endereco);
		return ResponseEntity.ok(pessoa);
		
	}
	

}
