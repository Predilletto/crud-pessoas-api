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
## Buscar Pessoa
**Request**:
 
- **URL**: localhost:port/api/pessoas/{pessoaId} 
- **METHOD**: GET 
- **BODY**: BLANK 

 
**Successful Response**:
 
- **STATUS CODE**: 200
- **BODY**:
```json
{
   ...Pessoa // astributos da Pessoa que foi recuperada do banco de dados através do ID. 
}
```
 
**Error Response**:
 
- **STATUS CODE**: 404
- **BODY**:
```json
{
   Status code // 404 
}
```
 
---
## Listar Pessoas
**Request**:
 
- **URL**: localhost:port/api/pessoas
- **METHOD**: GET
- **BODY**: BLANK 

 
**Successful Response**:
 
- **STATUS CODE**: 200
- **BODY**:
```json
{
   ...Lista {Pessoas} // Lista com todas as pessoas cadastradas até o momento no banco.
}
```
 
**Error Response**:
 
- **STATUS CODE**: 404
- **BODY**:
```json
{
   Status code //404 
}
```
 
---
## Criar Endereço para Pessoa
**Request**:
 
- **URL**: localhost:port/api/pessoas/{pessoaId}/enderecos
- **METHOD**: POST
- **BODY**:
```json
{
    logradouro: String // Não nula 
    cep: String // Não Nula, minimo de 8 e máximo de 9 carecteres 
    numero: String // Máximo de 4 caracteres 
    cidade: String // Não Nula 
}
```
 
**Successful Response**:
 
- **STATUS CODE**: 201
- **BODY**:
```json
{
   ...Endereco // atributos do endereço que foi criada.
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
## Listar Pessoas
**Request**:
 
- **URL**: localhost:port/api/pessoas/{pessoaId}/enderecos
- **METHOD**: GET
- **BODY**: BLANK 

 
**Successful Response**:
 
- **STATUS CODE**: 200
- **BODY**:
```json
{
   ...Lista {Enderecos} // Lista com todas os endereços cadastradas até o momento no banco e que pertencem a pessoa informada.
}
```
 
**Error Response**:
 
- **STATUS CODE**: 404
- **BODY**:
```json
{
  Status code // 404
}
```
---         
         
## Alterar Endereço 
**Request**:
 
- **URL**: localhost:port/api/pessoas/{pessoaId}/enderecos/{enderecoId} 
- **METHOD**: PATCH 
- **BODY**: BLANK 

 
**Successful Response**:
 
- **STATUS CODE**: 200
- **BODY**:
```json
{
   ...Endereco // Atributos do novo endereço principal.
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
    

