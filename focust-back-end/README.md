# Focust - Back-End Server Directory
This directory contains the code for the RESTful, Java-based back-end server.

## Why is this back-end Monolithic?
The back-end is a *monolithic* architecture, which *does not scale very well* and is much less resilient compared to a microservice architecture. However, I chose this over implementing a microservice for various reasons:

* Setting up the project is much less complicated, as you only need to set up a back-end server and one database versus setting up *each and every microservice*. This is made worse by the fact that each microservice could have a unique back-end and database technology (which might be nice if I wanted to include technologies to my resume, but makes working on the back-end a complete nightmare)

* Complicated requests, like figuring out the list of users that are a part of a given project, is made easier if everything is in one schema (as we can use a *relational entity* between users and projects as well as using an `INNER JOIN` to get the data we want) versus if the data is scattered across different databases* and thus potentially require multiple API requests to get the data we need, which is less performant versus only making a single request.

[*] having microservices share a single database leads to a much less resilient architecture and is considered an *anti-pattern*, as there is only one point of failure that impacts *all microservices* versus multiples that each only affect their respective microservice.

## Regarding the REST Commands
All REST commands return and/or accept JSON, along with some HTTP Status Code. replace the value `[PORT]` with the port set by `server.port` in the `application.properties` file in the `focust-back-end/src/main/resources` directory.

## GET Requests

### Users
The following command can be used to get information about a single user with the given `[ID]`:
```
GET http://localhost:[PORT]/users/[ID]
```
The response contains the following information:
* `"id"` - the user ID of the user
* `"username"` - the user's username for the site
* `"email"` - the user's email address for the site.
 * `"github"`- the user's github username. If the user has not given the site said username, then the value would be `null`.

To get information about all of the users in the database, you can use the following command:
```
GET http://localhost:[PORT]/users/
```
Each element in the list contains the following information:
* `"id"` - the user ID of the user
* `"username"` - the user's username for the site.
* `"email"` - the user's email address for the site.
* `"github"`- the user's github username. If the user has not given the site said username, then the value would be `null`.

This particular request to get all of the users might be deprecated as, if there are a lot of users, sending the response JSON will be a nightmare. This request only exists for me as another way to see if the back-end server has successfully included a user into the database. 

## POST Requests

### Users
To create a new user, which is used for account creation, you can use the following command:
```
POST http://localhost:[PORT]/users/
```
The request body, which is in JSON, must contain the following information:
* `"username"` - the user's username for the site.
* `"password"` - the plaintext password for the user. This is fine as the site assumes that HTTPS will be used in production, meaning that the request is encrypted and thus cannot be easily extracted when using a packet sniffer like *WireShark*.
* `"email"` - the user's email address for the site.

The response will have a status of `204 CREATED` if a new user has actually been added, along with a JSON body containing information about the newly created user. If the user with the given username exists, a status of `200 OK` will be sent instead, with no JSON body.