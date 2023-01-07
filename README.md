# CRUD Responsável por gerenciar Pessoas e Endereços.


## Desafio Java

#### Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:
## Criar pessoa
**Request**:
 
- **URL**: localhost:port/api/pessoas
- **METHOD**: POST
- **BODY**:
```json
{
  nome: String // Não nula, não vazia, máximo de 80 caracteres
  dataNasc: String // Não nula, formatadata em dd-MM-yyyy
}
```
 
**Successful Response**:
 
- **STATUS CODE**: 201
- **BODY**:
```json
{
   ...Pessoa // atributos da pessoa que foi criada.
}
```
 
**Error Response**:
 
- **STATUS CODE**: 400
- **BODY**:
```json
{
   "type": String // Tipo de excecção que foi lançada 
    "title": String // Descrição do titulo de Erro
    "status": int // 400 
    "detail": String // Detalhes sobre o erro 
    "instance": String // Linkando para onde o erro teria acontecido 
}
```
 
---

## Editar Pessoa 
**Request**:
 
- **URL**: localhost:port/api/pessoas/{pessoaId}
- **METHOD**: PUT
- **BODY**:
```json
{
  nome: String // Não nula, não vazia, máximo de 80 caracteres
  dataNasc: String // Não nula, formatadata em dd-MM-yyyy
}
```
 
**Successful Response**:
 
- **STATUS CODE**: 200
- **BODY**:
```json
{
   ...Pessoa // atributos da pessoa que foi editada.
}
```
 
**Error Response**:
 
- **STATUS CODE**: 400
- **BODY**:
```json
{
   "type": String // Tipo de excecção que foi lançada 
    "title": String // Descrição do titulo de Erro
    "status": int // 400 
    "detail": String // Detalhes sobre o erro 
    "instance": String // Linkando para onde o erro teria acontecido 
}
```
 
---

- Consultar uma pessoa
  - ***Endpoint: localhost:port/api/pessoas/{pessoaId}***
    
    - Metodo: GET
    
    - Request Body: 
        -Parametro Id passado na URL {pessoaId}
    - Response Body:
        - Entidade Pessoa recuperada do Banco. 
    - Response Code:
        - 200 (OK) para Sucesso.
        - 404 (NOT FOUND) para Falha.
-Listar pessoas
  - ***Endpoint: localhost:port/api/pessoas/***
    
    - Metodo: GET
    
    - Request Body: 
        -Corpo Vazio (Irrelevante para o Metodo GET) 
    - Response Body:
        - Lista das entidades de Pessoas cadastradas com sucesso no banco até então.
        - Lista Vazia caso o banco não tenha sido populado.
    - Response Code:
        - 200 (OK) para Sucesso.
        - 400 (Bad Request) para Falha.
- Criar endereço para pessoa
  - ***Endpoint: localhost:port/api/pessoas/{pessoaId}/enderecos***
    
    - Metodo: POST
    
    - Request Body: 
        - Parametro Id de Pessoa passada na URL {pessoaId}
        - logradouro: String (Não Nulo) 
        - cep: String (Não Nulo / minimo de 8 caracteres e máximo de 9)  /**cep e numero juntos formam uma chave unica**/
        - numero: String (máximo de 4 caracteres)  /**numero e cep juntos formam uma chave unica**/
        - cidade: String (Não Nulo) 
    - Response Body:
        - Entidade Endereco cadastrada no Banco. /**por convenção da API todo primeiro endereço cadastrado para cada Pessoa, se torna seu endereço Principal(Podendo ser alterado posteriormente)
  
  
    - Response Code:
        - 201 (CREATED) para Sucesso.
        - 400 (Bad Request) para Falha.
- Listar endereços da pessoa
  - ***Endpoint: localhost:port/api/pessoas/{pessoaId}/enderecos***
    
    - Metodo: GET
    
    - Request Body: 
        - Parametro Id de Pessoa passada na URL {pessoaId}
    - Response Body:
        - Lista de Enderecos  cadastrada em determinada Entidade Pessoa no Banco. 
        - Lista Vazia caso o banco não tenha sido populado com endereços para essa pessoa. 
    - Response Code:
         - 200 (OK) para Sucesso.
         - 404 (NOT FOUND) para Falha caso Pessoa não exista no banco.
         - 400 (BAD REQUEST) para falha padrão.
         
         
- Poder informar qual endereço é o principal da pessoa
  - ***Endpoint: localhost:port/api/pessoas/{pessoaId}/enderecos/{enderecoId}***
    
    - Metodo: PATCH
    
    - Request Body: 
        - Parametro Id de Pessoa passada na URL {pessoaId} 
        - Parametro Id de Endereco para substituir antigo endereço principal na URL {enderecoId} 
    - Response Body:
        - Entidade Endereco que foi substituido como endereço principal de Pessoa. 
    - Response Code:
         - 200 (OK) para Sucesso.
         - 404 (NOT FOUND) para Falha caso Pessoa ou Endereco não exista no banco.
         - 400 (BAD REQUEST) para falha padrão.

### Uma Pessoa deve ter os seguintes campos:
- Nome
- Data de nascimento
- Endereço:
  - Logradouro
  - CEP
  - Número
  - Cidade

## UML das Entidades no banco: 
![image](https://user-images.githubusercontent.com/77293081/211125118-47b8174e-2684-4eb1-b0be-b0e7106ca4e0.png)

## Informações sobre a aplicação: 
- Utilizando o MAVEN para levantar e iniciar a aplicação : 
  - Utilizando o ".mvwn install", para instalar e e buildar a aplicação após isso, pode ser utilizado a linha "mvwn verify" para iniciar todos os testes de integração da aplicação.
- Utilizando alguma IDE para levantar e iniciar a aplicação :
  - Utilizando alguma IDE compativel com JAVA e Spring Boot pode se iniciar o JAR após o build da aplicação com Maven de forma mais direta e utilizando uma interface.
- PORT e BANCO DE DADOS:
  - Essa aplicação por padrão irá ser levantada no port 8080 essa configuração pode ser alterada em aplication.properties utilizando a anotação server.port= <port desejado>  
  - O banco de dados utilizado nessa API é de modelo SQL utilizando a biblioteca H2, então qualquer dado que tenha sido salvo no banco, irá ser "resetado" quando a coenxão com o banco for fechada, para utilizar essa API em modo com persistência de dados é necessário uma nova configuração de banco no arquivo "aplication.properties" 
  
- **TESTES** 
   - Entendendo o propósito desse desafio, essa aplicação contém apenas alguns testes de integração utilizando a biblioteca RestAssured para realizar apenas testes de API ou seja END-TO-END, acredito que testes unitários para essa simples aplicação não seria necessário 
  - Todos os testes funcionam de forma independente e trabalham com uma função que reseta os dados do banco para um estado estatico após cada teste. 
  - Todos os testes se encontram na ordem pela qual cada END POINT aqui foi explicado. 
    

### Requisitos
- Todas as respostas da API devem ser JSON
- Banco de dados H2
