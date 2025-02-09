# Usamos una imagen base con Java 21
FROM openjdk:21-jdk-slim

# Directorio de trabajo dentro del contenedor
WORKDIR /app

# Copiar el archivo JAR generado al contenedor
COPY target/gundam-0.0.1-SNAPSHOT.jar gundam-api.jar

# Exponer el puerto donde corre la API (por defecto 8080)
EXPOSE 8080

# Comando para ejecutar la aplicación
ENTRYPOINT ["java", "-jar", "gundam-api.jar"]