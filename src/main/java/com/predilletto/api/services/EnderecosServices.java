package com.predilletto.api.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.predilletto.api.domain.model.Endereco;
import com.predilletto.api.domain.model.Pessoa;
import com.predilletto.api.repository.EnderecoRepository;
import com.predilletto.api.repository.PessoaRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class EnderecosServices {
	
	
	private EnderecoRepository enderecoRepository;
	
	private PessoaRepository pessoaRepository; 
	
	@Transactional
	public Endereco criarEndereco (Long pessoaId,  Endereco endereco) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
		endereco.setPessoa(pessoa);
		pessoa.adicionarEndereco(endereco);
		
		return enderecoRepository.save(endereco);
		
	}
	
	
	public List<Endereco> listar(Long pessoaId) {
		Pessoa pessoa = pessoaRepository.findById(pessoaId).get();
		return pessoa.getEnderecos();
	}
	
	
	
	
	
	
	

}
