package com.predilletto.api;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.predilletto.api.services.PessoaServices;

@SpringBootTest
class CrudPessoasApiApplicationTests {
	
	@Autowired
	PessoaServices pessoaServices;

	@Test
	void listarVazio() {
		
	}

}
