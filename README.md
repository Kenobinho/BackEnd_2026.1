# API Aluno Online

API REST desenvolvida com Spring Boot para gerenciamento de **Aluno** e **Professor**, com operações completas de CRUD para as duas entidades.

## 1. Explicacao do projeto

Este projeto implementa o back-end de um sistema academico simples, com foco em:

- Cadastro de alunos
- Consulta de alunos
- Atualizacao de alunos
- Remocao de alunos
- Cadastro de professores
- Consulta de professores
- Atualizacao de professores
- Remocao de professores

A API persiste os dados em banco PostgreSQL utilizando Spring Data JPA.

## 2. Tecnologias utilizadas

- Java 21
- Spring Boot 4.0.3
- Spring Web MVC
- Spring Data JPA
- PostgreSQL
- Lombok
- Maven

## 3. Arquitetura utilizada

A aplicacao segue uma arquitetura em camadas:

- **Controller**: recebe as requisicoes HTTP e retorna as respostas
- **Service**: contem a logica de negocio
- **Repository**: acesso ao banco via JPA
- **Model**: entidades mapeadas para tabelas

Fluxo da requisicao:

1. Cliente envia request HTTP para um endpoint
2. Controller recebe e delega para o Service
3. Service aplica regras e chama o Repository
4. Repository executa operacoes no banco
5. Resultado volta para Service e Controller
6. Controller retorna a response HTTP

## 4. Estrutura do projeto

```text
src/main/java/br/com/alunoonline/api/
  AlunoOnlineApplication.java
  Controller/
    AlunoController.java
    ProfessorController.java
  service/
    AlunoService.java
    ProfessorService.java
  repository/
    AlunoRepository.java
    ProfessorRepository.java
  model/
    Aluno.java
    Professor.java

src/main/resources/
  application.properties
```

## 5. Detalhamento do codigo

### 5.1 Entidades (Model)

#### Aluno

Tabela: `aluno`

Campos:

- id (Long, chave primaria, auto incremento)
- nomeCompleto (String)
- matricula (String)
- cpf (String)
- email (String)

#### Professor

Tabela: `professor`

Campos:

- id (Long, chave primaria, auto incremento)
- nomeCompleto (String)
- cpf (String)
- email (String)

### 5.2 Repositories

- `AlunoRepository extends JpaRepository<Aluno, Long>`
- `ProfessorRepository extends JpaRepository<Professor, Long>`

Com isso, a API herda automaticamente metodos como `save`, `findAll`, `findById` e `deleteById`.

### 5.3 Services

#### AlunoService

Responsavel por:

- criar aluno
- listar todos os alunos
- buscar aluno por id
- deletar aluno por id
- atualizar aluno por id

#### ProfessorService

Responsavel por:

- criar professor
- listar todos os professores
- buscar professor por id
- deletar professor por id
- atualizar professor por id

### 5.4 Controllers e endpoints

Base URL local:

```text
http://localhost:8080
```

---

## 6. CRUD completo de Aluno

### 6.1 Criar Aluno

- Metodo: `POST`
- Endpoint: `/alunos`
- Status de sucesso: `201 Created`

Body exemplo:

```json
{
  "nomeCompleto": "Maria Silva",
  "matricula": "20260001",
  "cpf": "12345678900",
  "email": "maria.silva@exemplo.com"
}
```

### 6.2 Listar todos os Alunos

- Metodo: `GET`
- Endpoint: `/alunos`
- Status de sucesso: `200 OK`

Resposta exemplo:

```json
[
  {
    "id": 1,
    "nomeCompleto": "Maria Silva",
    "matricula": "20260001",
    "cpf": "12345678900",
    "email": "maria.silva@exemplo.com"
  }
]
```

### 6.3 Buscar Aluno por ID

- Metodo: `GET`
- Endpoint: `/alunos/{id}`
- Status de sucesso: `200 OK`

Resposta exemplo:

```json
{
  "id": 1,
  "nomeCompleto": "Maria Silva",
  "matricula": "20260001",
  "cpf": "12345678900",
  "email": "maria.silva@exemplo.com"
}
```

### 6.4 Atualizar Aluno por ID

- Metodo: `PUT`
- Endpoint: `/alunos/{id}`
- Status de sucesso: `204 No Content`

Body exemplo:

```json
{
  "nomeCompleto": "Maria Silva Atualizada",
  "matricula": "20260001",
  "cpf": "12345678900",
  "email": "maria.atualizada@exemplo.com"
}
```

### 6.5 Deletar Aluno por ID

- Metodo: `DELETE`
- Endpoint: `/alunos/{id}`
- Status de sucesso: `204 No Content`

---

## 7. CRUD completo de Professor

### 7.1 Criar Professor

- Metodo: `POST`
- Endpoint: `/professores`
- Status de sucesso: `201 Created`

Body exemplo:

```json
{
  "nomeCompleto": "Carlos Mendes",
  "cpf": "98765432100",
  "email": "carlos.mendes@exemplo.com"
}
```

### 7.2 Listar todos os Professores

- Metodo: `GET`
- Endpoint: `/professores`
- Status de sucesso: `200 OK`

Resposta exemplo:

```json
[
  {
    "id": 1,
    "nomeCompleto": "Carlos Mendes",
    "cpf": "98765432100",
    "email": "carlos.mendes@exemplo.com"
  }
]
```

### 7.3 Buscar Professor por ID

- Metodo: `GET`
- Endpoint: `/professores/{id}`
- Status de sucesso: `200 OK`

Resposta exemplo:

```json
{
  "id": 1,
  "nomeCompleto": "Carlos Mendes",
  "cpf": "98765432100",
  "email": "carlos.mendes@exemplo.com"
}
```

### 7.4 Atualizar Professor por ID

- Metodo: `PUT`
- Endpoint: `/professores/{id}`
- Status de sucesso: `204 No Content`

Body exemplo:

```json
{
  "nomeCompleto": "Carlos Mendes Atualizado",
  "cpf": "98765432100",
  "email": "carlos.atualizado@exemplo.com"
}
```

### 7.5 Deletar Professor por ID

- Metodo: `DELETE`
- Endpoint: `/professores/{id}`
- Status de sucesso: `204 No Content`

---

## 8. Configuracao do banco de dados (PostgreSQL)

Arquivo: `src/main/resources/application.properties`

Configuracoes atuais:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/aluno_online
spring.datasource.username=coloque seu banco aqui
spring.datasource.password=coloque sua senha aqui
spring.datasource.driver-class-name=org.postgresql.Driver
spring.jpa.database-platform=org.hibernate.dialect.PostgreSQLDialect
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```

## 9. Como executar o projeto

### 9.1 Pre-requisitos

- Java 21 instalado
- Maven instalado (ou usar o wrapper `./mvnw`)
- PostgreSQL ativo com banco `aluno_online` criado

### 9.2 Passo a passo

1. Ajuste usuario e senha no arquivo `application.properties`
2. Abra o terminal na raiz do projeto
3. Execute o comando:

```bash
./mvnw spring-boot:run
```

4. A API estara disponivel em:

```text
http://localhost:8080
```

## 10. Prints das requisicoes feitas no Insomnia

Capturas realizadas com sucesso (status HTTP validos para cada operacao).

### 10.1 Aluno

- Criar aluno (POST /alunos)
  - Status: `201 Created`

![POST Aluno](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.27.40.png)

- Listar alunos (GET /alunos)
  - Status: `200 OK`

![GET Alunos](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.27.45.png)

- Buscar aluno por ID (GET /alunos/{id})
  - Status: `200 OK`

![GET Aluno por ID](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.27.53.png)

- Atualizar aluno (PUT /alunos/{id})
  - Status: `204 No Content`

![PUT Aluno](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.28.02.png)

- Deletar aluno (DELETE /alunos/{id})
  - Status: `204 No Content`

![DELETE Aluno](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.27.57.png)

### 10.2 Professor

- Criar professor (POST /professores)
  - Status: `201 Created`

![POST Professor](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.26.18.png)

- Listar professores (GET /professores)
  - Status: `200 OK`

![GET Professores](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.26.24.png)

- Buscar professor por ID (GET /professores/{id})
  - Status: `200 OK`

![GET Professor por ID](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.26.29.png)

- Atualizar professor (PUT /professores/{id})
  - Status: `204 No Content`

![PUT Professor](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.26.45.png)

- Deletar professor (DELETE /professores/{id})
  - Status: `204 No Content`

![DELETE Professor](docs/imagens/insomnia/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.26.35.png)

## 11. Prints do DBeaver (tabelas e dados usados nos testes)

Capturas realizadas mostrando as tabelas `aluno` e `professor` com os dados utilizados nos testes.

- Tabela aluno

![Tabela Aluno](docs/imagens/dbeaver/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.28.26.png)

- Tabela professor

![Tabela Professor](docs/imagens/dbeaver/Captura%20de%20Tela%202026-04-07%20%C3%A0s%2015.28.12.png)

## 12. Autor

Projeto desenvolvido para a disciplina de Back-end (UNIESP).
