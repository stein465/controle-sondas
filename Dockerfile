# Use uma imagem base do Java
FROM openjdk:17-jdk-alpine AS build


WORKDIR /app/controle-sondas

COPY . .


RUN ./mvnw clean package -DskipTests

FROM openjdk:17-jdk-alpine

WORKDIR /app/controle-sondas

COPY --from=build /app/controle-sondas/target/*.jar app.jar

ENTRYPOINT ["java", "-jar", "app.jar"]


EXPOSE 8080
