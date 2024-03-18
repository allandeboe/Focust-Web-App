# Focust - Installation & Set-up Guide
This Markdown file is a guide of how to install the dependencies and set up both the front-end and back-end servers for this web application.

Obviously, this document assumes that you have already downloaded the repository from GitHub. All of the directories will be based on the main project directory.

## Essential Installations
Before you can install the relevant dependencies for and set up the front-end or back-end servers, you will need to install the following:

* [*Node.js*](https://nodejs.org/en/download) - should contain the *Node Package Manager* (`npm`), which is needed to install the dependencies for the front-end server.
* [*Java Development Kit (JDK) 17*](https://www.oracle.com/java/technologies/downloads/#java17) - at least version `17.0.3`. Using anything other than JDK 17 for this project will cause problems when it comes to building/compiling the project.
* [*MySQL Database (Community Edition)*](https://dev.mysql.com/downloads/installer/) - this document assumes this as the default. If you are planning on using a different database (like SQL Server or MongoDB), you will need to change some things if you want this program to work as intended. Using different editions of MySQL might (like MySQL Enterprise) might be fine. 

## Back-end Server (Java Spring)

### Setting Up
The dependencies the back-end needs should be there when downloading the project from GitHub, so, for the back-end, you only need to set up the back-end server so it can run as intended.

To do this, one needs to create an `application.properties` file under the `focust-back-end/src/main/resources` directory. It's this file that contains important and sensitive information like the database password. 

```properties
# Server-Mode
focust.server-mode = dev
server.port = [PORT] # Replace with the port number for the server

# Java Spring Settings
spring.jpa.hibernate.ddl-auto = update
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver

# Security Information

# I typically use [HOST] = mysql (OR localhost:3306 if running locally) and [DATABASE] = focust_db
spring.datasource.url = jdbc:mysql://[HOST]/[DATABASE]
# Replace [USERNAME] & [PASSWORD] with the username & password of the database root, respectively
spring.datasource.username = [USERNAME]
spring.datasource.password = [PASSWORD]

```



If you are using any database that is MySQL for this web server and have the appropriate Driver dependencie for said database, you will need to set `spring.datasource.driver-class-name` to whatever Driver class name is for the database. You will also need to set the `spring.datasource.url` to have the actual URL to the database. 

After writing the necessary lines to the newly-created `application.properties` file, save it.

### Running the Server
After all of that, you should now be able to run the back-end server. Open the terminal (if it isn't already open) at the `focust-back-end` directory and run the following commands:

*If you are on **Linux** (i.e. Ubuntu, Debian, etc.), run...*
```
maven clean
maven spring-boot:run
```

*If you are on **Windows**, run...*
```
mvnw clean
mvnw spring-boot:run
```

Or you can run the provided `run.bat`, which will essentially do the same thing but in one go.

Then, you can use something like *CURL* or *Postman* to interact with this server. Read the `README.md` file in the `focust-back-end` directory to see what commands you can write to the server. 

### Docker Container (NEW)
An alternative way of starting the program involves using a docker container, which is run on *Debian* (`debian:bookworm-20240311`). Assuming you already have *Docker* or *Docker Desktop* installed, you can set-up the docker container by first creating a `docker-compose.yml` file with the following content:

```yml

version: "3"

services:
  [HOST]:
    image: mysql:latest
    volumes:
      - mysql-data:/var/lib/mysql
    environment:
      MYSQL_DATABASE: [DATABASE]
      MYSQL_ROOT_PASSWORD: [PASSWORD]
    ports:
      - 3307:3306
    networks:
      - mysql-network
  
  app:
    build: .
    ports:
      - [PORT]:[PORT]
    networks:
      - mysql-network
    environment:
      MYSQL_HOST: [HOST]
      MYSQL_USER: [USERNAME]
      MYSQL_PASSWORD: [PASSWORD]
      MYSQL_DATABASE: [DATABASE]
    depends_on:
      - [HOST]

volumes:
  mysql-data: 

networks:
  mysql-network:

```

The values for `[PORT]`, `[USERNAME]`, `[PASSWORD]`, `[HOST]`, & `[DATABASE]` are the same as the ones listed in the `application.properties` file (Unless if `[HOST]` is `localhost:3306`, in which case you need to replace it with some name like `mysql`, like I did here)

After that, you can simply save this `docker-compose.yml` file into the `focust-back-end` directory, start up the terminal at that directory, and run the following command:

```
> docker-compose build
```

If you did everything right, you should not have any issues. If this step is succesful, you can now start up the docker container using the following command:

```
> docker compose up
```

The results at the very end should still be the same as running the server locally on your machine, and you can still interact with it using *Postman* or *CURL* in the same way. If you want to stop the server, press `CTRL + C`, and then write down

```
> docker compose down
```

## Front-end Server (React)

### Installing Dependencies
You can simply install all of the dependencies rather easily by running the following command in the terminal opened in the `focust-front-end` directory:

```
npm install
```

This command should install *all* of the dependencies listed in the `package.json` file in the `focust-front-end` directory.

### Setting Up
To set up the project, you need to first modify `package.json` file to change the value of `proxy` to the URL of the project: 

```json
    proxy: "http://localhost:[PORT]"
```

This is to allow the front-end server make appropriate request to the back-end server. By default, the port number is `8080`, as you might see in the `package.json` file.

After that, you are ready to build the project. To do that, you can run the following command in the terminal, assuming you have it still open in the `focust-front-end` directory from the previous subsection:

```
npm run build
```

### Running the server
Finally, you should now be able to run the front-end server. Open the terminal (if it isn't already open) at the `focust-front-end` directory and run the following command:

```
npm start
```

You should now go to `http://localhost:3000/` and see the website. 