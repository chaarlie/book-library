The following API allows CRUD operations on a Book bean and returns the response in HTML/Plain text.
## Tools

- MySQL Server (port 3306)
- Maven
- Java 8

## How to run
<ol> 
<li>create database <strong> book_db</strong></li>
<li>modify application.properties and type in <em>user/pass</em> for  flyway and db user</li>
    
    spring.datasource.url = jdbc:mysql://localhost:3306/book_db?useSSL=false
    spring.datasource.username = root
    spring.datasource.password = Admin123
    
    spring.flyway.url = jdbc:mysql://localhost:3306/mysql
    spring.flyway.schemas = book_db
    spring.flyway.user = root
    spring.flyway.password = Admin123
      
  
<li>modify pom.xml credentials</li>

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

<li>in the root dir execute</li>
      
      mvn clean package
      mvn flyway:migrate
      mvn spring-boot:run
      

<li> app should be running on <em>localhost:8080/books/</em></li>
</ol>

Note: you can find here the Postman collection: https://www.dropbox.com/s/pzdk7rcdtnlpnw9/Book%20Operations.postman_collection.json?dl=0