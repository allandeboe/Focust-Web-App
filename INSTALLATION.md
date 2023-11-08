# Focust - Installation & Set-up Guide
This Markdown file is a guide of how to install the dependencies and set up both the front-end and back-end servers for this web application.

Obviously, this document assumes that you have already downloaded the repository from GitHub. All of the directories will be based on the main project directory.

## Essential Installations
Before you can install the relevant dependencies for and set up the front-end or back-end servers, you will need to install the following:

* [*Node.js*](https://nodejs.org/en/download) - should contain the *Node Package Manager* (`npm`), which is needed to install the dependencies for the front-end server.
* [*Java Development Kit (JDK) 17*](https://www.oracle.com/java/technologies/downloads/#java17) - at least version `17.0.3`. Using anything other than JDK 17 for this project will cause problems when it comes to building/compiling the project.
* [*MySQL Database (Community Edition)*](https://dev.mysql.com/downloads/installer/) - this document assumes this as the default. If you are planning on using a different database (like SQL Server or MongoDB), you will need to change some things if you want this program to work as intended. Using different editios of MySQL might (like MySQL Enterprise) might be fine. 

## Back-end Server (Java Spring)

### Installing Dependencies
[Work in Progress]


### Setting Up
After installing the dependencies on the device locally, one needs to create an `application.properties` file under the `focust-back-end/src/main/resources` directory. It's this file that contains important and sensitive information like the database password. 

```
# Server-Mode
focust.server-mode = dev
server.port = 8080 # This can be changed to whatever best suits you.

# Java Spring Settings
spring.jpa.hibernate.ddl-auto = update
spring.datasource.driver-class-name = com.mysql.cj.jdbc.Driver

# Security Information
spring.datasource.url = jdbc:mysql://localhost:3306/focustdb
spring.datasource.username = [USERNAME] # Replace with the username of the database root
spring.datasource.password = [PASSWORD] # Replace with the password for the database root.

```
the `jdbc:mysql://localhost:3306/focustdb` database url can be replaced with whatever database url best suits the project. I am adding it here as an example of what kind of database url the `spring.datasource.url` variable is looking for.

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

Then, you can use something like *CURL* or *Postman* to interact with this server. Read the `README.md` file in the `focust-back-end` directory to see what commands you can write to the server. 

## Front-end Server (React)

### Installing Dependencies
You can simply install all of the dependencies rather easily by running the following command in the terminal opened in the `focust-front-end` directory:

```
npm install
```

This command should install *all* of the dependencies listed in the `package.json` file in the `focust-front-end` directory.

### Setting Up
To set up the project, you can run the following command in the terminal, assuming you have it still open in the `focust-front-end` directory from the previous subsection:

```
npm run build
```

### Running the server
Finally, you should now be able to run the front-end server. Open the terminal (if it isn't already open) at the `focust-front-end` directory and run the following command:

```
npm start
```

You should now go to `http://localhost:3000/` and see the website. 