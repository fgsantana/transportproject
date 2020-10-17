# transport-project

### API REST que possui endpoints para criar, obter, alterar e excluir uma transportadora, além de um endpoint que retorna o endereço de acordo com o CEP enviado. Todos os endpoints possuem validação.
> A logo é salva no banco de dados (em byte array) e refenciada como OID na tabela de Transportadora. Assim que a imagem é inserida, é criada uma URL com a transportadora correspondente.

> A validação do CEP acontece com o consumo de uma API externa para retornar o endereço correspondente ao CEP (ex: caso o cep 50123453 não exista, o próprio servidor identifica a resposta da API externa e gera uma exceção)

### O frontend faz uso da API (requisições HTTP) e tem funcionalidades de listagem (com filtro por nome, modal e UF), criação e alteração de transportadoras. Há também a validação de campos na parte de criação e alteração (inclusive validação de CEP, como formato e existência). 

- Java 11
- Spring Boot
- Spring Web
- Spring Data JPA
- Hibernate Validator
- JUnit
- PostgreSQL
- Angular 10
- Maven
- Docker

- [ViaCEP](https://viacep.com.br/) - API externa para consulta de CEP


