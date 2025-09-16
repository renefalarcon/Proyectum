# Proyecto Spring Boot

Este proyecto fue desarrollado con **IntelliJ IDEA** y utiliza **Spring Boot**, **Maven** y **H2 Database**. Implementa un sistema de **login** y un **CRUD de productos**, funcionando a través de **tokens de autenticación**.

## Requisitos

- Java 17+
- Maven 3+
- IDE: IntelliJ IDEA (recomendado)
- Navegador web para probar endpoints o Postman/Insomnia

## Pasos para ejecutar el proyecto

1. **Abrir el proyecto en IntelliJ IDEA**  
   Importa el proyecto como un proyecto Maven.

2. **Compilar el proyecto con Maven**  
   Ejecuta el siguiente comando en la terminal del proyecto:
   ```bash
   mvn clean compile package

3. Ejecutar la aplicación Spring Boot

   Dentro de IntelliJ, ejecuta la clase principal con Run o usando: mvn spring-boot:run


4. **Acceso a la aplicación**

   La aplicación se iniciará en el puerto 8080: http://localhost:8080


5. **Base de datos H2**

   Al iniciar la aplicación, se crea automáticamente una base de datos H2.
   Las credenciales se encuentran en application.properties:
   * spring.datasource.url=jdbc:h2:mem:testdb
   * spring.datasource.username=usuario
   * spring.datasource.password=contraseña


6. **Autenticación**

   El sistema funciona mediante token JWT.

   Debes incluir el token en el header Authorization para acceder a los endpoints protegidos.

Endpoints principales
   Endpoint	Método	Descripción
   * /login	POST	Autenticación de usuario
   * /productos	GET	Listar todos los productos
   * /productos	POST	Crear nuevo producto
   * /productos/{id}	PUT	Actualizar producto 
   * /productos/{id}	DELETE	Eliminar producto

Notas

Puedes acceder a la consola H2 en:
http://localhost:8080/h2-console
