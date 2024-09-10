# Controle de Sondas

Este projeto é uma API REST desenvolvida com Spring Boot que permite o controle de sondas em planetas. A API possibilita criar, mover, buscar e deletar sondas em um plano cartesiano com uma área limitada de 5x5.

## Objetivo

O objetivo é fornecer uma API que simula o controle de sondas em um planeta. A API gerencia sondas com base em comandos específicos e possibilita interações com múltiplas sondas em diferentes planetas.

## Detalhes sobre o funcionamento:
A sequência de comandos é um conjunto de instruções enviadas da terra para a sonda, onde :

M -> Andar para a frente na direção que está 1 posição.
L -> Virar a sonda para a esquerda (90 graus)
R -> Virar a sonda para a direita (90 graus)
A orientação da sonda dentro do plano cartesiano usa uma rosa dos ventos como referência


## Estrutura do Projeto

controle-sondas/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/
│   │   │       └── igorTeixeira/
│   │   │           └── controle_sondas/
│   │   │               ├── controller/
│   │   │               │   └── SondaController.java
│   │   │               ├── dtos/
│   │   │               │   ├── ComandoDTO.java
│   │   │               │   └── SondaDTO.java
│   │   │               ├── models/
│   │   │               │   ├── Planeta.java
│   │   │               │   └── Sonda.java
│   │   │               └── services/
│   │   │                   └── SondaService.java
│   │   └── resources/
│   │       ├── application.properties
│   │       └── static/
│   └── test/
│       └── java/
│           └── com/
│               └── igorTeixeira/
│                   └── controle_sondas/
│                       └── services/
│                           └── SondaServiceTest.java
├── .gitignore
├── pom.xml
└── README.md

## Dependências

O projeto usa as seguintes dependências:

- **Spring Boot**: Framework principal para o desenvolvimento da aplicação.
- **Spring Data JPA**: Para acesso e manipulação de dados no banco de dados.
- **PostgreSQL** e **MySQL**: Bancos de dados suportados.
- **Mockito** e **JUnit**: Para testes unitários.
- **Springdoc OpenAPI**: Para a geração da documentação da API.

## Endpoints da API

### Buscar todas as sondas
- **URL**: `/sonda/`
- **Método**: `GET`
- **Descrição**: Retorna uma lista de todas as sondas.

### Buscar sonda por ID
- **URL**: `/sonda/{id}`
- **Método**: `GET`
- **Descrição**: Retorna uma sonda específica com base no ID fornecido.
- **Resposta**: `200 OK` (se encontrada) ou `404 Not Found` (se não encontrada).

### Criar nova sonda
- **URL**: `/sonda/create`
- **Método**: `POST`
- **Descrição**: Cria uma nova sonda com base nos dados fornecidos.
- **Corpo da Requisição**: `SondaDTO`
- **Resposta**: `200 OK` (se criada com sucesso) ou `400 Bad Request` (se houver erro).

### Mover sonda
- **URL**: `/sonda/mover/{id}`
- **Método**: `POST`
- **Descrição**: Move a sonda para uma nova posição com base nos comandos fornecidos.
- **Corpo da Requisição**: `ComandoDTO`
- **Resposta**: `200 OK` (se movida com sucesso) ou `400 Bad Request` (se houver erro).

### Deletar sonda
- **URL**: `/sonda/{id}`
- **Método**: `DELETE`
- **Descrição**: Remove uma sonda com base no ID fornecido.
- **Resposta**: `204 No Content` (se deletada com sucesso) ou `404 Not Found` (se não encontrada).