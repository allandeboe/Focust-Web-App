# Focust - Issue Tracker Web Application
![Banner for Focust, as an SVG](./references/focust-banner-ref.svg)
Focust is an issue tracker made using *Java Spring*, *MySQL*, *React.js*, *TypeScript*, and *TailwindCSS*. 

Currently, the back-end server is able to handle user creation and stores passwords via bcrypt into the database, and able to return information about users without leaking their sensitive information. Later, the project will support authentication tokens and authorization to prevent third-parties from modifying the database as well as allow certain permissions for site admins and project owners to modify the database contents, plus other details. 

The front-end currently has page transitions, a gradient background, a navigation bar, and a "users" page that will display all of the users stored in the database, if the back-end is running. Later, there will be user pages that will go more in-depth about a user (again, without revealing sensitive information), project pages, the ability to view and interact with a Kanban-styled taskboard to keep track of the progres made on tasks and user stories, plus other details.

## Why is it called "Focust"?
Focust is a combination between *focus* and *locusts* - bugs that [are known to form destructive swarms called *locust plagues*](https://www.livescience.com/locusts.html). Since this web app is an *issue tracker* (thus a *bug tracker*), the name is representative of how it allows developers to focus on keeping track of software bugs (or *"focus on locusts"*), as well as progress done on feature implementations.

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

* [**JEST**](https://jestjs.io/) - used primarily to test front-end code, which is useful when dealing with front-end functionality.

### Other
These are the dependencies that do not fall under the other two categories, and are 

* [**MySQL**](https://www.mysql.com/) - an open-sourced, SQL relational database from Oracle; used as the database of choice, due to how prominent it is in job descriptions ([*Microsoft SQL Server*](https://www.microsoft.com/en-us/sql-server/sql-server-downloads) could also work, although it will require one to change the JDBC Driver to the **MS SQL Server Driver** in *Spring Initializr*).

* [**Docker**](https://www.docker.com/) - a piece of open-source software that deals with deploying software applications in *containers*, which are essentially like small & compact virtual machines. This is used to allow a more easy deployment process for this project, if I do decide to have this web application run on the web. 

## Installation, Set-up, & Interaction
For instructions regarding the installation & set-up for both front-end and back-end servers, read the `INSTALLATION.md` file in this directory.