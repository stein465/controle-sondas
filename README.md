
# Controle de Sondas

Este projeto é uma API RESTful desenvolvida em Java com Spring Boot para gerenciar e controlar sondas espaciais. A aplicação utiliza um banco de dados MySQL hospedado na AWS RDS e foi containerizada com Docker.

## Tecnologias Utilizadas

- Java 17
- Spring Boot
- JPA (Java Persistence API)
- MySQL (Amazon RDS)
- Docker
- Maven
- Swagger (para documentação de API)

## Como Rodar o Projeto

### Rodando Localmente

1. Clone o repositório:
   ```bash
   git clone https://github.com/stein465/controle-sondas.git



### Rodando com Docker

Se preferir usar Docker para rodar o projeto, siga os passos abaixo:

1. Certifique-se de ter o **Docker** instalado.
2. No diretório do projeto, crie uma imagem Docker:
   ```bash
   docker build -t controle-sondas .
   ```
3. Execute o contêiner:
   ```bash
   docker run -d -p 8080:8080 --name controle-sondas-app controle-sondas
   ```
4. A aplicação estará disponível em `http://localhost:8080`.

### Swagger

A documentação da API foi gerada usando **Swagger**. Após iniciar a aplicação, você pode acessar a documentação completa no seguinte endereço:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`

Com a Swagger UI, é possível visualizar e testar as rotas disponíveis diretamente pela interface web.

## Configuração do Banco de Dados

Este projeto utiliza MySQL hospedado na AWS RDS. Certifique-se de configurar as credenciais de acesso ao banco no arquivo `application.properties` ou no contêiner Docker:


## Funcionalidades

- Cadastro de sondas
- Consulta de posições
- Atualização de posições das sondas
- Remoção de sondas
### Alterações feitas:
1. **Docker**: Instruções detalhadas sobre como rodar o projeto usando Docker (`build` e `run`).
2. **Swagger**: Informações sobre o Swagger e como acessar a documentação da API.
3. **Banco de Dados**: Inclusão do uso do MySQL hospedado no RDS e instruções para configurar o acesso ao banco de dados.
4. **Futuras Melhorias**: Sugestões para melhorias futuras, que também podem ser úteis para impressionar recrutadores.

Agora o README está mais completo e profissional, tornando o projeto mais acessível para quem quer rodá-lo e entender sua estrutura!
