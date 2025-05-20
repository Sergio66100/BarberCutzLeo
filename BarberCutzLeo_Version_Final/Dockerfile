FROM maven:3.9.6-eclipse-temurin-17 AS build
WORKDIR /app

# Copia el archivo pom.xml y descarga las dependencias
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copia el resto del proyecto y compila
COPY . .
RUN mvn clean package -DskipTests

# 🏃 Etapa final: imagen liviana con Java
FROM eclipse-temurin:17-jdk
WORKDIR /app

# Copia el .jar generado en la imagen final
COPY --from=build /app/target/*.jar app.jar

# Expone el puerto donde corre tu app
EXPOSE 9000


# Comando para ejecutar tu app Spring Boot
ENTRYPOINT ["java", "-jar", "app.jar"]