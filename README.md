# API REST MVC - Java 17 + Spring Boot + PostgreSQL

## Descrição do Projeto

Este projeto consiste em uma API REST desenvolvida utilizando **Java 17**, **Spring Boot**, **Spring Data JPA** e **PostgreSQL**, seguindo o padrão arquitetural **MVC (Model-View-Controller)**.

A aplicação permite o gerenciamento de clientes através de operações REST, realizando persistência de dados em um banco PostgreSQL. O banco de dados é executado em um container Docker, enquanto a aplicação Spring Boot é executada localmente durante o desenvolvimento.

O objetivo deste projeto é fornecer uma base sólida para aplicações corporativas, adotando boas práticas de organização de código, separação de responsabilidades e integração com banco de dados relacional.

---

## Resumo

### Tecnologias Utilizadas

- Java 17
- Spring Boot 3.x
- Spring Data JPA (Hibernate)
- Maven
- PostgreSQL 16
- Docker
- Bean Validation (Jakarta Validation)

### Funcionalidades

- Cadastro de clientes
- Consulta de clientes
- Persistência em PostgreSQL
- Validação de dados
- API RESTful
- Integração com JPA/Hibernate

---

## Arquitetura

A aplicação segue o padrão MVC (Model-View-Controller), promovendo baixo acoplamento e alta coesão entre as camadas.

```text

┌─────────────────────────────┐
│ Spring Boot API             │
│ Java 17                     │
│ Maven                       │
│ MVC                         │
│ JPA/Hibernate               │
│ Executando localmente       │
└──────────────┬──────────────┘
               │ JDBC
               ▼
┌─────────────────────────────┐
│ PostgreSQL (Docker)         │
│ Dados Persistidos           │
└─────────────────────────────┘
```

## Camadas
```text
Cliente HTTP
      │
      ▼
┌─────────────────┐
│   Controller    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│    Service      │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│   Repository    │
└────────┬────────┘
         │
         ▼
┌─────────────────┐
│ PostgreSQL DB   │
└─────────────────┘
```

### Responsabilidades das Camadas

| Camada     | Função                                                                        |
|------------|-------------------------------------------------------------------------------|
| Controller | Responsável por receber as requisições HTTP e retornar as respostas da API.   |
| Service    | Responsável pelas regras de negócio da aplicação.                             |
| Repository | Responsável pela comunicação com o banco de dados através do Spring Data JPA. |
| Model      | Representa as entidades persistidas no banco de dados.                        |
| DTO        | Responsável pela transferência de dados entre cliente e servidor.             |


---

## Estrutura do Projeto

```text
src
└── main
    ├── java
    │   └── com/mvm/studies/api_docker
    │       ├── controller
    │       │   └── ClienteController.java
    │       │
    │       ├── service
    │       │   └── ClienteService.java
    │       │
    │       ├── repository
    │       │   └── ClienteRepository.java
    │       │
    │       ├── model
    │       │   └── Cliente.java
    │       │
    │       ├── dto
    │       │   └── ClienteDTO.java
    │       │
    │       └── ApiApplication.java
    │
    └── resources
        ├── application.yml
        └── application.properties

docker-compose.yml

README.md
```

---

## Banco de Dados

O PostgreSQL é executado via Docker.

### docker-compose.yml

```yaml
services:
  postgres:
    image: postgres:16

    container_name: postgres-db

    environment:
      POSTGRES_DB: api_db
      POSTGRES_USER: postgres
      POSTGRES_PASSWORD: postgres

    ports:
      - "5432:5432"

    volumes:
      - postgres_data:/var/lib/postgresql/data

volumes:
  postgres_data:
```

### Subir o Banco

```bash
docker compose up -d
```

### Verificar Containers

```bash
docker ps
```

---

## Configuração da Aplicação

### application.yml

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/api_db
    username: postgres
    password: postgres

  jpa:
    show-sql: true

    hibernate:
      ddl-auto: update

server:
  port: 8080
```

---

## Executando a Aplicação

### Executar com Maven

```bash
mvn spring-boot:run
```

### Gerar JAR

```bash
mvn clean package
```

### Executar JAR

```bash
java -jar target/api.jar
```

---

## Endpoints

### Criar Cliente

```http
POST /api/clientes
```

Request:

```json
{
  "nome": "João Silva",
  "email": "joao@email.com"
}
```

---

### Listar Clientes

```http
GET /api/clientes
```

Response:

```json
[
  {
    "id": 1,
    "nome": "João Silva",
    "email": "joao@email.com"
  }
]
```

---

## Boas Práticas Aplicadas

- Arquitetura MVC
- Separação de responsabilidades
- Injeção de dependências
- Persistência com JPA/Hibernate
- Validação de dados
- Banco de dados isolado em Docker
- Configuração externa via application.yml

---

## Melhorias Futuras

- Autenticação JWT
- Spring Security
- Swagger/OpenAPI
- Flyway para versionamento do banco
- Testes unitários com JUnit
- Testes de integração com Testcontainers
- Logs estruturados
- Observabilidade com Actuator

---

## Autor

Projeto desenvolvido como base para estudos e implementação de APIs REST utilizando Java 17, Spring Boot, JPA e PostgreSQL.