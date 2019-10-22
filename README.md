# Biblioteca 

El proyecto a continuación simula una librería, utilizando un API RestFul. Está hecho bajo el framework Spring 5 y utilizando la base de datos MySQL.  
## Herramientas para correr el proyecto 

- MySQL Server (corriendo en el 3306)
- Maven
- Java 8

## Cómo correr el proyecto
<ol> 
<li>crear una base de datos llamada <strong> book_db</strong></li>
<li>modificar application.properties y escribir <em>usuario/contraseña</em> para las variables de flyway y user</li>
    
    spring.datasource.url = jdbc:mysql://localhost:3306/book_db?useSSL=false
    spring.datasource.username = root
    spring.datasource.password = Admin123
    
    spring.flyway.url = jdbc:mysql://localhost:3306/mysql
    spring.flyway.schemas = book_db
    spring.flyway.user = root
    spring.flyway.password = Admin123
      
  
<li>modificar la etiqueta del pom.xml para las credenciales y db</li>

    <plugin>
      <groupId>org.flywaydb</groupId>
      <artifactId>flyway-maven-plugin</artifactId>
      <version>6.0.7</version>
      <configuration>
          <url>jdbc:mysql://localhost:3306</url>
          <user>root</user>
          <password>Admin123</password>
          <schemas>
              <schema>book_db</schema>
          </schemas>
      </configuration>
    </plugin>

<li> en la raíz del directorio, abrir la línea de comandos y ejecutar:</li>
      
      mvn clean package
      mvn flyway:migrate
      mvn spring-boot:run
      

<li> la aplicación estará corriendo en <em>localhost:8080/books/</em></li>
</ol>

Nota: en este enlace se puede acceder a la colleción de Postman: https://www.dropbox.com/s/pzdk7rcdtnlpnw9/Book%20Operations.postman_collection.json?dl=0