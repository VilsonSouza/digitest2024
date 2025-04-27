Aqui está um exemplo completo de um `README.md` para o seu projeto:


# Projeto de Estacionamento - API REST

Este é um projeto de API REST para gerenciar o estacionamento de veículos. Ele utiliza o Spring Boot, H2 Database (em memória) e Swagger para documentação da API.

## Tecnologias Usadas

- **Spring Boot** 2.7.17
- **Spring Data JPA**
- **H2 Database (em memória)**
- **Swagger (Springdoc OpenAPI)**
- **Maven** como gerenciador de dependências

## Endpoints da API

### 1. Obter todos os estacionamentos
- **URL**: `/api/parking`
- **Método HTTP**: `GET`
- **Descrição**: Retorna uma lista de todos os estacionamentos.

### 2. Obter estacionamento por ID
- **URL**: `/api/parking/{id}`
- **Método HTTP**: `GET`
- **Descrição**: Retorna o estacionamento com o ID fornecido.
- **Parâmetro de URL**:
  - `id` (UUID): ID do estacionamento.

### 3. Criar um novo estacionamento
- **URL**: `/api/parking`
- **Método HTTP**: `POST`
- **Descrição**: Cria um novo estacionamento.
- **Body** (JSON):
  ```json
  {
    "placa": "ABC1234",
    "modelo": "Fusca",
    "cor": "Azul"
  }
  

### 4. Atualizar um estacionamento
- **URL**: `/api/parking/{id}`
- **Método HTTP**: `PUT`
- **Descrição**: Atualiza um estacionamento existente.
- **Parâmetro de URL**:
    - `id` (UUID): ID do estacionamento.
- **Body** (JSON):
  ```json
  {
    "placa": "XYZ9876",
    "modelo": "Civic",
    "cor": "Preto"
  }
  ```

### 5. Deletar um estacionamento
- **URL**: `/api/parking/{id}`
- **Método HTTP**: `DELETE`
- **Descrição**: Deleta o estacionamento com o ID fornecido.
- **Parâmetro de URL**:
    - `id` (UUID): ID do estacionamento.

## Swagger - Documentação da API

A documentação interativa da API pode ser acessada através do Swagger UI:

- **URL**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

## Banco de Dados H2

O banco de dados H2 está configurado em memória e pode ser acessado através da interface web H2 Console:

- **URL**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)
- **JDBC URL**: `jdbc:h2:mem:testdb`
- **Usuário**: `de sua preferência`
- **Senha**: `de sua preferência`

## Como Rodar o Projeto

### 1. Clonar o repositório

```bash
git clone https://github.com/seuusuario/seu-repositorio.git
cd seu-repositorio
```

### 2. Rodar o projeto

Execute o seguinte comando para rodar o projeto com o Spring Boot:

```bash
mvn spring-boot:run
```

Ou, se você tiver o JAR gerado:

```bash
java -jar target/myinterview-1.0-SNAPSHOT.jar
```

### 3. Acessar os Endpoints

Uma vez que o projeto esteja em execução, você pode acessar os endpoints da API no seu navegador ou usar uma ferramenta como o Postman.

- **API REST**: [http://localhost:8080/api/parking](http://localhost:8080/api/parking)
- **Swagger UI**: [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)
- **H2 Console**: [http://localhost:8080/h2-console](http://localhost:8080/h2-console)

 Dependências do Projeto

Este projeto usa as seguintes dependências principais:

- **Spring Boot Starter Web**: Para criação da API REST.
- **Spring Boot Starter Data JPA**: Para integração com o banco de dados H2.
- **H2 Database**: Banco de dados em memória.
- **Swagger (Springdoc OpenAPI)**: Para documentação da API.
- **JUnit e Mockito**: Para testes (ainda não implementados).




