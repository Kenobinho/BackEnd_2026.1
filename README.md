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

## DTOs

O projeto utiliza DTOs (Data Transfer Objects) para operações específicas de entrada/saída.

### AtualizarNotasRequestDTO

- Arquivo: [src/main/java/br/com/alunoonline/api/dtos/AtualizarNotasRequestDTO.java](src/main/java/br/com/alunoonline/api/dtos/AtualizarNotasRequestDTO.java)
- Finalidade: corpo da requisição para atualizar notas de uma matrícula (`PATCH /matriculas/atualizar-notas/{id}`).
- Campos:
  - `nota1` (Double): primeira nota — pode ser omitida para atualização parcial
  - `nota2` (Double): segunda nota — pode ser omitida para atualização parcial

Exemplo (atualizar as duas notas):

```json
{
  "nota1": 8.5,
  "nota2": 7.0
}
```

Exemplo (atualizar apenas `nota1` — PATCH parcial):

```json
{
  "nota1": 9.0
}
```

Comportamento: o serviço aplica apenas os campos presentes no DTO; se ambas as notas estiverem preenchidas, calcula a média e atualiza o `status` da matrícula (`APROVADO` / `REPROVADO`).

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

## 8. Disciplina

Seção para gerenciar disciplinas (carga horária, professor responsável e nome).

### 8.1 Criar Disciplina

- Metodo: `POST`
- Endpoint: `/disciplinas`
- Status de sucesso: `201 Created`

Body exemplo:

```json
{
  "nome": "Programação II",
  "cargaHoraria": 60,
  "professor": { "id": 3 }
}
```

Imagem (Insomnia - criar disciplina):

![Criar Disciplina](docs/imagens/insomnia/criar-disciplina.png)

### 8.2 Listar todas as disciplinas

- Metodo: `GET`
- Endpoint: `/disciplinas`
- Status de sucesso: `200 OK`

Imagem (Insomnia - listar disciplinas):

![Listar Disciplinas](docs/imagens/insomnia/listar-disciplinas.png)

### 8.3 Buscar Disciplina por ID

- Metodo: `GET`
- Endpoint: `/disciplinas/{id}`
- Status de sucesso: `200 OK`

Imagem (Insomnia - buscar disciplina por id):

![Buscar Disciplina por ID](docs/imagens/insomnia/buscar-disciplina-id.png)

### 8.4 Atualizar Disciplina por ID

- Metodo: `PUT`
- Endpoint: `/disciplinas/{id}`
- Status de sucesso: `204 No Content`

Body exemplo (atualizar cargaHoraria):

```json
{
  "nome": "Programação II - Avançado",
  "cargaHoraria": 80,
  "professor": { "id": 3 }
}
```

Imagem (Insomnia - atualizar disciplina):

![Atualizar Disciplina](docs/imagens/insomnia/atualizar-disciplina.png)

### 8.5 Deletar Disciplina por ID

- Metodo: `DELETE`
- Endpoint: `/disciplinas/{id}`
- Status de sucesso: `204 No Content`

Imagem (Insomnia - deletar disciplina por id):

![Deletar Disciplina](docs/imagens/insomnia/deletar-disciplina-id.png)

## 9. Matrícula

Esta seção descreve as operações de matrícula de alunos em disciplinas, incluindo criação, trancamento e atualização de notas.

### 8.1 Criar Matrícula

- Metodo: `POST`
- Endpoint: `/matriculas`
- Status de sucesso: `201 Created`

Body exemplo (referenciando `aluno.id` e `disciplina.id`):

```json
{
  "aluno": { "id": 2 },
  "disciplina": { "id": 3 }
}
```

Imagem (Insomnia - criar matrícula):

![Criar Matrícula](docs/imagens/insomnia/criar-matricula.png)

### 8.2 Trancar Matrícula

- Metodo: `PATCH`
- Endpoint: `/matriculas/trancar/{id}`
- Status de sucesso: `204 No Content`

Exemplo de uso no Insomnia: definir o método `PATCH` e enviar para `http://localhost:8080/matriculas/trancar/1`.

Imagem (Insomnia - trancar matrícula):

![Trancar Matrícula](docs/imagens/insomnia/trancar-matricula.png)

### 8.3 Atualizar notas (PATCH parcial)

- Metodo: `PATCH`
- Endpoint: `/matriculas/atualizar-notas/{id}`
- Status de sucesso: `204 No Content`

O endpoint aceita atualização parcial: envie apenas `nota1`, apenas `nota2`, ou as duas. Se as duas notas estiverem presentes, o sistema calcula a média e atualiza o `status` (APROVADO/REPROVADO) automaticamente.

Body exemplo (atualizar as duas notas):

```json
{
  "nota1": 8.5,
  "nota2": 7.0
}
```

Body exemplo (atualizar apenas a nota1):

```json
{
  "nota1": 9.0
}
```

Passo a passo rápido no Insomnia:

1. Método: `PATCH`
2. URL: `http://localhost:8080/matriculas/atualizar-notas/{id}` (substitua `{id}` pelo id da matrícula)
3. Headers: `Content-Type: application/json` (geralmente definido automaticamente)
4. Body: JSON com `nota1` e/ou `nota2` (raw -> JSON)
5. Enviar e verificar `204 No Content` como sucesso

Imagem (Insomnia - atualizar notas):

![Atualizar Notas](docs/imagens/insomnia/atualizar-notas.png)

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

![POST Aluno](docs/imagens/insomnia/criar-aluno.png)

- Listar alunos (GET /alunos)
  - Status: `200 OK`

![GET Alunos](docs/imagens/insomnia/listar-alunos.png)

- Buscar aluno por ID (GET /alunos/{id})
  - Status: `200 OK`

![GET Aluno por ID](docs/imagens/insomnia/buscar-aluno-id.png)

- Atualizar aluno (PUT /alunos/{id})
  - Status: `204 No Content`

![PUT Aluno](docs/imagens/insomnia/atualizar-aluno.png)

- Deletar aluno (DELETE /alunos/{id})
  - Status: `204 No Content`

![DELETE Aluno](docs/imagens/insomnia/deletar-aluno.png)

### 10.2 Professor

- Criar professor (POST /professores)
  - Status: `201 Created`

![POST Professor](docs/imagens/insomnia/criar-professor.png)

- Listar professores (GET /professores)
  - Status: `200 OK`

![GET Professores](docs/imagens/insomnia/listar-professores.png)

- Buscar professor por ID (GET /professores/{id})
  - Status: `200 OK`

![GET Professor por ID](docs/imagens/insomnia/buscar-professor-id.png)

- Atualizar professor (PUT /professores/{id})
  - Status: `204 No Content`

![PUT Professor](docs/imagens/insomnia/atualizar-professor.png)

- Deletar professor (DELETE /professores/{id})
  - Status: `204 No Content`

![DELETE Professor](docs/imagens/insomnia/deletar-professor.png)

## 11. Prints do DBeaver (tabelas e dados usados nos testes)

Capturas realizadas mostrando as tabelas `aluno` e `professor` com os dados utilizados nos testes.

- Tabela aluno

![Tabela Aluno](docs/imagens/dbeaver/dbeaver-aluno.png)

- Tabela professor

![Tabela Professor](docs/imagens/dbeaver/dbeaver-professor.png)

## 12. Autor

Projeto desenvolvido para a disciplina de Back-end (UNIESP).
