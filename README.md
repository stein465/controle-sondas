# Controle de Sondas

Este projeto implementa um sistema de controle de sondas espaciais utilizando **Spring Boot** e **MySQL**. A aplicação foi projetada para rodar em um ambiente Docker Compose, garantindo maior flexibilidade e escalabilidade.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot**
- **JPA/Hibernate**
- **MySQL**
- **Docker Compose**
- **Amazon EC2 (AWS)**

## Deploy

Atualmente, o sistema está hospedado em uma instância **AWS EC2**, onde o **Docker Compose** gerencia os contêineres da aplicação Spring Boot e do banco de dados MySQL.

## Detalhes do Ambiente

- **Docker Compose**: A aplicação é executada em contêineres utilizando Docker Compose, o que facilita o gerenciamento de serviços.
  
- **Banco de Dados**: O banco de dados **MySQL** está sendo executado localmente em um contêiner dentro da instância EC2, substituindo o uso anterior do Amazon RDS.
  
- **AWS EC2**: Todo o ambiente, incluindo a aplicação Spring Boot e o banco de dados MySQL, está rodando diretamente em uma instância EC2, permitindo maior controle sobre o servidor e infraestrutura.

## Como Executar

1. Clone este repositório:

   ```bash
   git clone https://github.com/stein465/controle-sondas.git
   ```

2. Acesse a pasta do projeto:

   ```bash
   cd controle-sondas
   ```

3. Configure seu ambiente Docker:

   Certifique-se de que o **Docker** e o **Docker Compose** estão instalados e configurados corretamente na sua instância **EC2**.

4. Execute o Docker Compose:

   ```bash
   docker-compose up -d
   ```

   Isso irá iniciar a aplicação **Spring Boot** e o banco de dados **MySQL**.

---
