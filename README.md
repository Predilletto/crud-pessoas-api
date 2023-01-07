# CRUD Responsável por gerenciar Pessoas e Endereços.


## Desafio Java

#### Usando Spring boot, crie uma API simples para gerenciar Pessoas. Esta API deve permitir:
- Criar uma pessoa:
  - ***Endpoint: localhost:port/api/pessoas*** 
    
    - Metodo: POST
    
    - Request Body: 
        - nome: String (Não Nulo/ Não Vazio /maxímo de 80 caracteres)
        - dataNasc: Date (Não Nulo/ formato de data dd-MM-yyyy)
    - Response Body:
        - Entidade Pessoa criada no Banco. 
    - Response Code:
        - 201 (Created) para Sucesso.
        - 400 (Bad Request) para Falha.
- Editar uma pessoa
  - ***Endpoint: localhost:port/api/pessoas/{pessoaId}***
    
    - Metodo: PUT
    
    - Request Body: 
        - nome: String (Não Nulo/ Não Vazio /maxímo de 80 caracteres)
        - dataNasc: Date (Não Nulo/ Formato de data dd-MM-yyyy)
    - Response Body:
        - Entidade Pessoa editada no Banco. 
    - Response Code:
        - 200 (OK) para Sucesso.
        - 400 (Bad Request) para Falha.
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

### Requisitos
- Todas as respostas da API devem ser JSON
- Banco de dados H2
