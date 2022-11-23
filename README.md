#Focust - Bugtracker and Project Management Web App
*Focust* is a bugtracker and project management web application built using Java Spring, React.js, and MySQL. 

It is meant to be a flapship portfolio project to demonstrate my skills as a software engineer and software developer regarding web development. Since it was made specifically for the portfolio, it is not meant to actually be used (even though it is meant to be built as if it was) and will be slowly expanded with some feature updates upon after it is considered *complete*. (i.e. meets the requirements I gave the project).

## Set Up (for Running Locally)
First, one should install *Node Project Manager* (`npm`), *Java Software Development Kit* (at least version 17), and *MySQL Database Community Edition*. 

Then, download the source code from this GitHub repository.

### Java Spring Back-end (REST API Server)
To compile the Java-based Back-end, once must first create a `application.properties` file under the `focust-back-end/src/main/resources` directory and include the following code:

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

After that, you should now be able to compile the program. To do that, open the terminal in the `focust-back-end` directory and run the following command:

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