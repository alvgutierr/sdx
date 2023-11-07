# Documento de Descripción del API en Spring Boot y Aplicación Web en Angular

## Introducción

Este documento describe un API desarrollado en Spring Boot que proporciona servicios para una aplicación web construida con Angular. 
El API se encarga de gestionar la lógica del servidor y la persistencia de datos, mientras que la aplicación web en Angular es la interfaz de usuario que permite a los usuarios interactuar con los datos y realizar diversas operaciones.

## API en Spring Boot 
#### Ver instalacion en [API SpringBoot](https://github.com/alvgutierr/sdx/blob/main/springBoot/README.md)

### Descripción

El API en Spring Boot es una parte esencial de la aplicación que permite la comunicación entre el cliente (la aplicación web en Angular) y el servidor. Ofrece endpoints RESTful para realizar operaciones CRUD (Crear, Leer, Actualizar y Eliminar) en una colección de "Favoritos". Algunas de las características clave del API incluyen:

- Endpoints para crear, recuperar, actualizar y eliminar favoritos.
- Validaciones y manejo de errores adecuados.
- Persistencia de datos utilizando una base de datos H2.

### Ejemplo de Endpoints

- `GET /api/v1/favorites/`: Recupera todos los favoritos almacenados en la base de datos.
- `POST /api/v1/favorites/`: Crea un nuevo favorito con los datos proporcionados.
- `PUT /api/v1/favorites/`: Actualiza un favorito existente por su identificador.
- `DELETE /api/v1/favorites/{id}`: Elimina un favorito existente por su identificador.

## Aplicación Web en Angular
#### Ver instalacion en [Aplicación Angular](https://github.com/alvgutierr/sdx/blob/main/angular/README.md)

### Descripción

La aplicación web en Angular es la interfaz de usuario que permite a los usuarios gestionar sus favoritos. Algunas de las características clave de la aplicación incluyen:

- Vista de lista de favoritos.
- Funcionalidad de crear y eliminar favoritos.
- Integración con el API en Spring Boot para enviar y recibir datos de favoritos.

### Ejemplo de Funcionalidad

- Vista de lista de favoritos que muestra todos los favoritos almacenados.
- Boton para agregar un nuevo favorito con campos como título, descripción, etc.
- Opción para eliminar un favorito haciendo clic en un botón de eliminación.

## Conclusión

El API en Spring Boot y la aplicación web en Angular forman una solución completa para la gestión de favoritos. 
El API proporciona los servicios de backend necesarios para almacenar y recuperar datos, mientras que la aplicación web en Angular ofrece una interfaz amigable para que los usuarios interactúen con sus favoritos. 
Esta combinación permite una experiencia de usuario fluida y una gestión eficiente de datos.

¡Gracias por leer este documento de descripción! 
Si tienes alguna pregunta o necesitas más detalles sobre el API en Spring Boot o la aplicación web en Angular, me contactas a alvgutierr@gmail.com


