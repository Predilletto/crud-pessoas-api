package com.predilletto.api;



import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasSize;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.annotation.DirtiesContext.ClassMode;

import com.predilletto.api.domain.model.Endereco;
import com.predilletto.api.domain.model.Pessoa;
import com.predilletto.api.services.EnderecosServices;
import com.predilletto.api.services.PessoaServices;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

@DirtiesContext(classMode = ClassMode.BEFORE_EACH_TEST_METHOD)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CrudPessoasApiApplicationTestsIT {
	
	
	
	@LocalServerPort
	private int port;
	
	@Autowired
	private PessoaServices pessoaServices;
	
	@Autowired
	private EnderecosServices enderecosServices;
	

	
	
	
	@BeforeEach
	public void setup() {
		populateTest();
		RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
		RestAssured.port=port;
		RestAssured.basePath="/api/pessoas";
		
		
	}
	
	@Test
	public void Deve_RetornarStatus201_Quando_Criarpessoa () { 
		given()
			.body("{ \"nome\": \"Rafael\", "
					+ "\"dataNasc\": \"20-01-2000\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void Deve_RetornarStatus400_Quando_CriarPessoaInvaida() {
		given()
			.body("{ \"nome\": \"\", "
					+ "\"dataNasc\": \"20-01-200220\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post()
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	
	@Test
	public void Deve_ChecarSePessoaFoiEditada_Quando_Editarpessoa () { 
		given()
			.body("{ \"nome\": \"João Monteiro\", "
					+ "\"dataNasc\": \"20-02-1993\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/2")
		.then()
			.body("nome", is("João Monteiro"),
					"dataNasc", is("20-02-1993"));
	}
	
	@Test
	public void Deve_Retornar400BadRequest_Quando_EditarPessoaDeFormaInvalida () { 
		given()
			.body("{ \"nome\": \"João Monteiro\", "
					+ "\"dataNasc\": \"20-02-123123993\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.put("/2")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void Deve_RetornarStatus200_Quando_BuscarumaPessoa() { 
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/1")
		.then()
			.statusCode(HttpStatus.OK.value());
	}
	
	@Test
	public void Deve_RetornarStatus404_Quando_BuscarumaPessoaNãoExistente() { 
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/99")
		.then()
			.statusCode(HttpStatus.NOT_FOUND.value());
	}
	
	
	@Test
	public void Deve_Conter3Pessoas_Quando_ListarPessoas() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get()
		.then()
			.body("", hasSize(3));
	}
	
	
	@Test
	public void Deve_RetornarStatus201_Quando_CriarEnderecoEmPessoa2 () { 
		given()
			.body("{ \"logradouro\": \"Rua Santa Catarina\","
					+ " \"cep\": \"88708-450\","
					+ "\"numero\": \"90\","
					+ "\"cidade\": \"Tubarão\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/2/enderecos")
		.then()
			.statusCode(HttpStatus.CREATED.value());
	}
	
	@Test
	public void Deve_RetornarStatus400_Quando_CriarEnderecoEmPessoa2deFormaInvalida () { 
		given()
			.body("{ \"logradouro\": \"Rua Santa Catarina\","
					+ " \"cep\": \"88708-450123123\","
					+ "\"numero\": \"90\","
					+ "\"cidade\": \"Tubarão\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/2/enderecos")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void Deve_RetornarStatus400_Quando_CriarEnderecoEmPessoa1ComMesmoCepENumero () { 
		given()
			.body("{ \"logradouro\": \"Rua Santa Catarina\","
					+ " \"cep\": \"88708-450\","
					+ "\"numero\": \"212\","
					+ "\"cidade\": \"Tubarão\" }")
			.contentType(ContentType.JSON)
			.accept(ContentType.JSON)
		.when()
			.post("/1/enderecos")
		.then()
			.statusCode(HttpStatus.BAD_REQUEST.value());
	}
	
	@Test
	public void Deve_Conter3Enderecos_Quando_ListarEnderecosdePessoa1() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/1/enderecos")
		.then()
			.body("", hasSize(3));
	}
	
	@Test
	public void Deve_RetornarUmaListaVazia_Quando_ListarEnderecosdePessoa2() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/2/enderecos")
		.then()
			.statusCode(HttpStatus.OK.value())
			.body("", hasSize(0)); 
	}
	
	@Test
	public void Deve_RetornarOEndereçoPrincipalDeID1_Quando_BuscarEnderecoPrincipalPessoa1() {
		given()
			.accept(ContentType.JSON)
		.when()
			.get("/1")
		.then()
			.body("enderecoPrincipal.id", is(1));
	
	}
	
	@Test
	public void Deve_RetornarOEndereçoPrincipalDeID2_Quando_AlterarEnderecoPrincipalPessoa1() {
		given()
			.accept(ContentType.JSON)
		.when()
			.patch("/1/enderecos/2")
		.then()
			.body("enderecoPrincipal.id", is(2));
	
	}
	
	
	
	
	
	
	public void populateTest() { 
		Pessoa pessoa1= new Pessoa();
		pessoa1.setNome("Carlos");
		pessoa1.setDataNasc(LocalDate.of(2000, 01, 30));
		pessoaServices.salvar(pessoa1);
		
		Pessoa pessoa2= new Pessoa();
		pessoa2.setNome("João");
		pessoa2.setDataNasc(LocalDate.of(1999, 02, 20));
		pessoaServices.salvar(pessoa2);
		
		Pessoa pessoa3= new Pessoa();
		pessoa3.setNome("Daniel");
		pessoa3.setDataNasc(LocalDate.of(1995, 03, 25));
		pessoaServices.salvar(pessoa3);
		
		
		Endereco endereco1=new Endereco();
		endereco1.setPessoa(pessoa1);
		endereco1.setLogradouro("Rua Santa Catarina");
		endereco1.setCep("88708-450");
		endereco1.setNumero("212");
		endereco1.setCidade("Tubarão");
		enderecosServices.criarEndereco(pessoa1.getId(), endereco1);
		Endereco endereco2=new Endereco();
		endereco2.setLogradouro("Rua Manoel Antônio Goulart");
		endereco2.setCep("88708-786");
		endereco2.setNumero("242");
		endereco2.setCidade("Tubarão");
		enderecosServices.criarEndereco(pessoa1.getId(), endereco2);
		
		Endereco endereco3=new Endereco();
		endereco3.setLogradouro("Rua Francisco Marcelino");
		endereco3.setCep("88708-718");
		endereco3.setNumero("132");
		endereco3.setCidade("Tubarão");
		enderecosServices.criarEndereco(pessoa1.getId(), endereco3);
		
	}



}
