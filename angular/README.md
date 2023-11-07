# Descripción Aplicación Angular y Guía de Ejecución

## Aplicacion web Angular

Este proyecto fue generado con las siguientes versiones:
 - Angular CLI: 16.2.9.
 - Node: 20.9.0
 - Package Manager: npm 10.2.3

### Características Clave

- Visualización de noticias, la aplicación permite a los usuarios ver noticias recopiladas de una API de noticias.
- Visualización de detalles de noticias, Los usuarios pueden hacer clic en una noticia de la lista para ver más detalles.
- Favoritos, los usuarios pueden marcar una noticia como favorita para guardarla y acceder fácilmente a ella más tarde.
- Gestión de favoritos, la aplicación permite a los usuarios gestionar su lista de favoritos, incluyendo agregar y eliminar noticias de la lista de favoritos.
- Integración de APIs, la aplicación consume dos APIs diferentes: un API de noticias para obtener las noticias y un API de favoritos para gestionar los favoritos de los usuarios.
- Construcción con Angular, la aplicación está construida utilizando Angular, lo que la hace modular, eficiente y de alto rendimiento.

## Instrucciones para Ejecutar la aplicación Angular (TestSodexo)

Asegúrate de tener Node.js instalado en tu sistema.

Instala Angular CLI globalmente mediante el siguiente comando:
```bash
npm install -g @angular/cli
```
Clonar el Repositorio:
```bash
git clone https://github.com/alvgutierr/sdx.git
```

Navega al Directorio del Proyecto:
```bash
cd angular\test-sodexo
```

Instalar las Dependencias:
```bash
npm install
```

Ejecutar la Aplicación en un Servidor de Desarrollo:
```bash
ng serve
```

Abre tu Navegador Web y Ve a la Siguiente URL:
```bash
http://localhost:4200
```

La aplicación se cargará en tu navegador, y podrás explorarla y probarla. La aplicación se recargará automáticamente si realizas cambios en el código fuente.

## Ejecutar la aplicacion Angular en un Contenedor Docker
### Esta sección describe cómo construir y ejecutar la aplicacione Angular en un contenedor Docker, lo que facilita su despliegue en diferentes entornos

Además de la ejecución local, puedes ejecutar la aplicaion Angular en un contenedor Docker para facilitar la distribución y el despliegue.

### Requisitos para Docker

Asegúrate de tener Docker instalado en tu sistema. Puedes descargar e instalar Docker desde el sitio web oficial [Docker Hub](https://www.docker.com/get-started).

### Construir la Imagen Docker

1. **Construir la Imagen**: Navega al directorio raíz del proyecto de la aplicacion Angular y ejecuta el siguiente comando para construir una imagen Docker del API:

   ```bash
    docker build -t web-fav .
    ```
  Esto creará una imagen Docker con el nombre web-fav basada en el Dockerfile del proyecto.

2. **Ejecutar el Contenedor Docker**
  Ejecutar el Contenedor: Una vez construida la imagen, puedes ejecutar la aplicacion Angular en un contenedor Docker con el siguiente comando:

  ```bash
  docker run -d --name web-favorites -it -p 81:80 web-fav
  ```
  - `-d`: Ejecuta el contenedor en segundo plano.  
  - `--name web-favorites`: Asigna un nombre al contenedor (en este caso, web-favorites).  
  - `-it`: Habilita la interacción con el contenedor.  
  - `-p 81:80`: Mapea el puerto del host local (81) al puerto del contenedor (80).  
  
  La aplicacion Angular se ejecutará en el contenedor Docker y estará disponible en el puerto 81 del host local. Puedes acceder a la aplicacion angular utilizando la URL http://localhost:81.
  
  ¡Listo! Ahora tienes la aplicacion Angular ejecutándose en un contenedor Docker para una fácil distribución y despliegue.

Nota: Asegúrate de que los servicios de la API de noticias y la API de favoritos estén en funcionamiento y configurados correctamente en la aplicación para que puedas utilizar todas las funciones de manera adecuada.

Con estos pasos, deberías poder ejecutar la aplicación Angular "TestSodexo" en tu entorno de desarrollo local y comenzar a trabajar con ella.
