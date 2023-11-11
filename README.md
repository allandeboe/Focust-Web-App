# Focust - Issue Tracker Web Application

**Focust** is an (currently) on-going flagship project that is meant to demonstrate my skills in *Java*, *JavaScript* (TypeScript), *CSS*, *HTML*, *SQL* and more, especially in the context of web (full stack) development.

When the project gets completed, Focust will allow users to create or join projects, assign themselves (or others, depending on project role) to tasks in a Kanban-styled taskboard to keep track of the progress made on each task, from bug fixes to feature implementations.

Focust has a RESTful, Java-based back end server that handles interactions with the database (I chose *MySQL*) as well as authentication/authorization, and *React.js* front end server written primarily in *TypeScript* (basically, JavaScript with types) to give the web application its look and feel.

## Why is it called "Focust"?
Focust is a combination between *focus* and *locusts* - bugs that [are known to form destructive swarms called *locust plagues*](https://www.livescience.com/locusts.html). Since this web app is an *issue tracker* (thus a *bug tracker*), the name is representative of how it allows developers to focus on keeping track of software bugs (or *"focus on locusts"*), as well as progress done on features.

(Or at least what the project will become once completed, as, at the moment) both the front end and back end are rather incomplete, but I'm still working on the project to slowly bring in these features that make an issue tracker an issue tracker).

## Dependencies

### Java Dependencies
These are the dependencies that involve the Java programming language, all of which are used for the back end web server.

* [**Java Spring / Spring Boot**](https://spring.io/) - a massive Java framework primarily used to help create Java-based web applications. Also has an easy project set-up tool known as [*Spring Initializr*](https://start.spring.io/), which is used to include most of the dependencies listed in this sub-section.

    * ***Spring Web*** - the main Spring dependency that is used to create RESTful web servers using Spring's *Model-View-Controller* (MVC) system.

    * [***Spring Security***](https://spring.io/projects/spring-security) - has customizible support for authentication & authorization, and provides some nice protections against certain attacks like clickjacking, session fixation, etc.; Can be integrated with Spring Web's MVC system.

    * [***Spring HATEOAS***](https://spring.io/projects/spring-hateoas) - used to help ease the creation of REST representations that follow the *HATEOAS* (Hypermedia As The Engine Of Application State) principle, which is essential for the "uniform interface" feature that RESTful applications have.

* [**Project Lombok**](https://projectlombok.org/) - a Java library that have special Java annotations that act as stand-ins for common Java boilerplate code, which can help speed up development time and make the code more readable.

* [**Hibernate**](https://hibernate.org/) - a Java *object-relational mapping* (ORM) tool; allows one to create database tables from Java Classes; comes with the **Spring Data JPA** dependency on *Spring Initializr*, which also has support to specify *SQL* queries for the relational database if the need arises.

* **MySQL Driver** - used to allow the web server to use the MySQL database (see *MySQL*). Changing the kind of database will require this dependency to change to another JDBC Driver Dependency in *Spring Initializr*.

### JavaScript / TypeScript Dependencies
All of these will use the [*Node.js Project Manager*](https://www.npmjs.com/) (`npm`) to install of the dependencies. These are used primarily for the front end server, which handles the site's appearance.

* [**TypeScript**](https://www.typescriptlang.org/) - a variant of JavaScript that is *strongly-typed*, which helps in correctness of code, especially inputs where what kind of data that gets put in matters.

* [**React.js**](https://react.dev/) - a commonly used front-end framework made by *Meta* (formerly known as *Facebook*). Used to design the visuals and structure of the website from the user's perspective.

* [**TailwindCSS**](https://tailwindcss.com/) - contains many useful CSS classes that makes it clear what stylings are applied to the JSX/HTML markup, which removes a lot of headaches and improves productivity at least when dealing with stylizing the site.

### Other
These are the dependencies that do not fall under the other two categories, and are 

* [**MySQL**](https://www.mysql.com/) - an open-sourced, SQL relational database from Oracle; used as the database of choice, due to how prominent it is in job descriptions ([*Microsoft SQL Server*](https://www.microsoft.com/en-us/sql-server/sql-server-downloads) could also work, although it will require one to change the JDBC Driver to the **MS SQL Server Driver** in *Spring Initializr*).

## Installation, Set-up, & Interaction
For instructions regarding the installation & set-up for both front-end and back-end servers, read the `INSTALLATION.md` file in this directory. 

