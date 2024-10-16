Controle de Sondas
Este projeto implementa um sistema de controle de sondas espaciais utilizando Spring Boot e MySQL. A arquitetura foi planejada para ser executada em um ambiente Docker Compose.

Tecnologias Utilizadas
Java 17
Spring Boot
JPA/Hibernate
MySQL
Docker Compose
Amazon EC2 (AWS)
Deploy
Atualmente, o sistema está configurado para rodar em uma instância EC2 da AWS, utilizando Docker Compose para orquestrar os contêineres da aplicação Spring Boot e do banco de dados MySQL.

Detalhes do Ambiente
Docker Compose: O projeto usa Docker Compose para rodar os serviços.
Banco de Dados: A aplicação não está mais utilizando o Amazon RDS. O MySQL agora é executado em um contêiner dentro da própria instância EC2, junto com o aplicativo Spring Boot.
AWS EC2: Todo o ambiente da aplicação (Spring Boot e MySQL) está rodando em uma instância EC2.
Como Executar
Clone este repositório:

bash
Copiar código
git clone https://github.com/stein465/controle-sondas.git
Acesse a pasta do projeto:

bash
Copiar código
cd controle-sondas
Configure seu ambiente Docker: Certifique-se de que o Docker e o Docker Compose estão instalados e configurados na sua instância EC2.

Execute o Docker Compose:

bash
Copiar código
docker-compose up -d
Isso irá iniciar a aplicação Spring Boot e o banco de dados MySQL.
