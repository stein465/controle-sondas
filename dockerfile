# Use uma imagem base do Java
FROM openjdk:17-jdk-slim

# Define o diretório de trabalho
WORKDIR /app

# Copie o JAR gerado para o diretório de trabalho
COPY target/*.jar app.jar

# Exponha a porta que a aplicação usará
EXPOSE 8080

# Comando para executar a aplicação
ENTRYPOINT ["java", "-jar", "app.jar"]