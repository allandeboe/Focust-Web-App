# Focust - Java-Based Bugtracker and Project Management Web Application
*Focust* is a bugtracker and project management web application (like Jira) built using *Java Spring*, *React.js*, and *MySQL* as well as a flapship portfolio project to demonstrate my skills as a software engineer and full-stack web developer.

## Set Up (for Running Locally)
First, one should install *Node Project Manager* (`npm`), *Java Software Development Kit* (at least version 17), and *MySQL Database Community Edition*. 

Then, download the source code from this GitHub repository.

### Java Spring Back-end (REST API Server)
To compile the Java-based REST API web server, once must first create a `application.properties` file under the `focust-back-end/src/main/resources` directory and insert the following code to that file:

```
# Server-Mode
focust.server-mode = dev
server.port = 8080 # You can chance this to whatever is convenient

# Java Spring Settings
spring.jpa.hibernate.ddl-auto = update
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver

# Authorization Information
spring.datasource.url = jdbc:mysql://localhost:3306/focustdb # You will need to create a 'focustdb' database.
spring.datasource.username = [USERNAME] # Replace with the username of the MySQL root
spring.datasource.password = [PASSWORD] # Replace with the password for the MySQL root
```

Then save the file. 

After that, you should now be able to compile the program. To do that, open the terminal in the `focust-back-end` directory and run the following commands:

**Linux**
```
maven clean
maven spring-boot:run
```

**Windows**
```
mvnw clean
mvnw spring-boot:run
```

Then, you should be able to use *Postman Agent* or *CURL* to interact with the REST API server.
