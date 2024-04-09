# Focust - Back-End Server Directory
This directory contains the code for the RESTful, Java-based back-end server.

## Why is this back-end Monolithic?
The back-end is a *monolithic* architecture, which *does not scale very well* and is much less resilient compared to a microservice architecture. However, I chose this over implementing a microservice for various reasons:

* Setting up the project is much less complicated, as you only need to set up a back-end server and one database versus setting up *each and every microservice*. This is made worse by the fact that each microservice could have a unique back-end and database technology (which might be nice if I wanted to include technologies to my resume, but makes working on the back-end a complete nightmare)

* Complicated requests, like figuring out the list of users that are a part of a given project, is made easier if everything is in one schema (as we can use a *relational entity* between users and projects as well as using an `INNER JOIN` to get the data we want) versus if the data is scattered across different databases* and thus potentially require multiple API requests to get the data we need, which is less performant versus only making a single request.

[*] having microservices share a single database leads to a much less resilient architecture and is considered an *anti-pattern*, as there is only one point of failure that impacts *all microservices* versus multiples that each only affect their respective microservice.

## Regarding the REST Requests
All REST requests return and/or accept JSON, along with some HTTP Status Code. replace the value `[PORT]` with the port set by `server.port` in the `application.properties` file in the `focust-back-end/src/main/resources` directory.

### Note (As of April 8th, 2024)
All requests, except for `GET` requests, will require a login, which is for Spring Security. For that, you will need to add the following lines to the `application.properties` file if you ever want to plan sending `POST` requests:

```properties
# ...

# Spring Security Authentication.
spring.security.user.name = [SPRING_USER]
spring.security.user.password = [SPRING_PASSWORD]
```

This will be replaced with JWT Tokens in a future commit, but, for now, keep this in mind when interacting with the back-end server using CURL or Postman.

## GET Requests

### Users
To get information about all of the users in the database, you can use the following request:
```
GET http://localhost:[PORT]/users
```
Each element in the list contains the following information:
* `"id"` - the user ID of the user
* `"username"` - the user's username for the site.
* `"email"` - the user's email address for the site.
* `"github"`- the user's github username. If the user has not given the site said username, then the value would be `null`.

If there are no users stored, then a `204 NO CONTENT` HTTP response will be given.

---

The following request can be used to get information about a single user with the given `[ID]`:
```
GET http://localhost:[PORT]/users/[ID]
```
The response contains the following information:
* `"id"` - the id of the user.
* `"username"` - the user's username for the site.
* `"email"` - the user's email address for the site.
* `"github"`- the user's github username. If the user has not given the site said username, then the value would be `null`.

---
The following request can be used to get information about the projects that a user with the given `[ID]` is a member of:
```
GET http://localhost:[PORT]/users/[ID]/projects
```
The response contains a list of JSON objects, each containing the following information:
* `"projectId"` - the id of the project.
* `"projectName"` - the name of the project.
* `"projectRole"` - the project role that the user has for said project.

If the user isn't a part of any projects, then a `204 NO CONTENT` HTTP response will be given.

### Projects

To get information about all of the projects in the database, you can use the following request:
```
GET http://localhost:[PORT]/projects
```
Each element in the list contains the following information:
* `"id"` - the id of the project.
* `"name"` - the name of the project.
* `"description"` - the description given for the project.

If there are no projects stored, then a `204 NO CONTENT` HTTP response will be given.

---

The following request can be used to get information about a single project with the given `[ID]`:
```
GET http://localhost:[PORT]/projects/[ID]
```
The response contains the following information:
* `"id"` - the id of the project.
* `"name"` - the name of the project.
* `"description"` - the description given for the project.

---

The following request can be used to get information about the users who are members of a project with the given `[ID]`:

```
GET http://localhost:[PORT]/projects/[ID]/members
```
Each element of the resulting list contains the following information
* `"userId"` - the id of the user.
* `"username"` - the username of the user.
* `"projectRole"` - the role that the user has for the project.

## POST Requests

### Users
To create a new user, which is used for account creation, you can use the following request:
```
POST http://localhost:[PORT]/users
```
The request body, which is in JSON, must contain the following information:
* `"username"` - the user's username for the site.
* `"password"` - the plaintext password for the user. This is fine as the site assumes that HTTPS will be used in production, meaning that the request is encrypted and thus cannot be easily extracted when using a packet sniffer like *WireShark*.
* `"email"` - the user's email address for the site.

The response will have a status of `201 CREATED` if a new user has actually been added, along with a JSON body containing information about the newly created user. If the user with the given username exists, a status of `200 OK` will be sent instead, with no JSON body.

### Projects
To create a new project, the following request is used:
```
POST http://localhost:[POST]/projects
```
The request body, which is in JSON, must contain the following information:
* `"name"` - the name of the project.
* `"description"` - the description of the project.
* `"creatorId"` - the id of the user who is creating the project. This is necessary as we need to create an entry in the relational entity table between users and projects that marks that this user is the owner of the project.

The response will have a status of `201 CREATED` if a new project has actually been added, along with a JSON body containing information about the newly created project. If the project with the given name exists, a status of `200 OK` will be sent instead, with no JSON body.