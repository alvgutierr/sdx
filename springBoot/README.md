# Descripción API en Spring Boot y Guía de Ejecución

## API en Spring Boot

El API en Spring Boot es una aplicación backend desarrollada utilizando el framework Spring Boot, que proporciona un conjunto de servicios RESTful para interactuar con las noticias favoritas y realizar operaciones específicas.

### Características Clave

- Implementado en Java version: 11.0.15, vendor: Oracle Corporation.
- Utiliza Spring Boot para una configuración rápida y sencilla.
- Ofrece endpoints RESTful para realizar operaciones como crear, leer, actualizar y eliminar (CRUD) datos.
- Conecta con una base de datos H2 para almacenar y recuperar información.
- Es consumido por la aplicacion web desarrollada en Angular.

## Instrucciones para Ejecutar el API en Spring Boot

### Requisitos del Entorno

Asegúrate de que tu entorno cumpla con los siguientes requisitos:

- **Maven**: Asegúrate de tener Apache Maven instalado `apache-maven-3.8.6`. Verifica la instalación ejecutando `mvn -version`.

- **Java**: Asegúrate de tener Java 11. Verifica la versión de Java ejecutando `java -version`.

### Pasos para Ejecutar el API

1. **Clonar el Repositorio**: Clona el repositorio del API desde un sistema de control de versiones como Git.

2. **Navegar al Directorio del Proyecto**: Abre una terminal y navega al directorio raíz del proyecto del API.

3. **Compilar el Proyecto**: Ejecuta los siguientes comando para compilar el proyecto utilizando Maven.

Ejecutar el API: Ejecutar los test unitarios con el siguiente comando:
  ```bash
  mvn test
  ```
Ejecutar el API: Construir el jar con el siguiente comando:
   ```bash
   mvn clean install
   ```
Ejecutar el API: Una vez compilado, ejecuta el API con el siguiente comando:
  ```bash
    java -jar target/favoritos-api-0.0.1-SNAPSHOT.jar
  ```
  
Verificar el API: El API debería estar en funcionamiento y escuchando en el puerto 8089. Puedes acceder al API a través de un navegador web o utilizar herramientas como Postman para realizar solicitudes.

## Ejecutar el API en un Contenedor Docker
### Esta sección describe cómo construir y ejecutar el API en un contenedor Docker, lo que facilita su despliegue en diferentes entornos

Además de la ejecución local, puedes ejecutar el API en un contenedor Docker para facilitar la distribución y el despliegue.

### Requisitos para Docker

Asegúrate de tener Docker instalado en tu sistema. Puedes descargar e instalar Docker desde el sitio web oficial [Docker Hub](https://www.docker.com/get-started).

### Construir la Imagen Docker

1. **Construir la Imagen**: Navega al directorio raíz del proyecto del API y ejecuta el siguiente comando para construir una imagen Docker del API:

   ```bash
   docker build -t api-fav .
    ```
  Esto creará una imagen Docker con el nombre api-fav basada en el Dockerfile del proyecto.

2. **Ejecutar el Contenedor Docker**
  Ejecutar el Contenedor: Una vez construida la imagen, puedes ejecutar el API en un contenedor Docker con el siguiente comando:

  ```bash
  docker run -d --name api-favorites -it -p 8089:8089 api-fav
  ```
  - `-d`: Ejecuta el contenedor en segundo plano.  
  - `--name api-favorites`: Asigna un nombre al contenedor (en este caso, api-favorites).  
  - `-it`: Habilita la interacción con el contenedor.  
  - `-p 8089:8089`: Mapea el puerto del host local (8089) al puerto del contenedor (8089).  
  
  El API en Spring Boot se ejecutará en el contenedor Docker y estará disponible en el puerto 8089 del host local. Puedes acceder al API utilizando la URL http://localhost:8089.
  
  ¡Listo! Ahora tienes el API en Spring Boot ejecutándose en un contenedor Docker para una fácil distribución y despliegue.
