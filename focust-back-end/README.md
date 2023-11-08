# Focust - Back-End Server Directory
This directory contains the code for the RESTful, Java-based back-end server.

## REST commands
All REST commands return and/or accept JSON, along with some HTTP Status Code. replace the value `[PORT]` with the port set by `server.port` in the `application.properties` file in the `focust-back-end/src/main/resources` directory.

### Users
The following REST commands handles interacting with the `users` table in the database. Since there are sensitive data that is stored in this database table (like *password hashes*), I used *data transfer objects* (DTOs) to avoid sending over said senitive information back to the client, which would otherwise be a recipe for a disasterous data breach. 

* `GET http://localhost:[PORT]/users/[ID]` returns the user with the given `ID`, containing the following information:
    * `"id"` - the user ID of the user
    * `"username"` - the user's username for the site
    * `"email"` - the user's email address for the site.
    * `"github"`- the user's github username. If the user has not given the site said username, then the value would be `null`.

* `GET http://localhost:[PORT]/users/` returns the list of all of the users, each containing the following information:
    * `"id"` - the user ID of the user
    * `"username"` - the user's username for the site.
    * `"email"` - the user's email address for the site.
    * `"github"`- the user's github username. If the user has not given the site said username, then the value would be `null`.

* `PUT http://localhost:[PORT]/users/` adds a new user to the site. You must send over some `JSON` that contains the following:
    * `"username"` - the user's username for the site.
    * `"password"` - the plaintext password for the user. This data gets encrypted when received and stored by the server. This would be fine if HTTPS is used, as the JSON would be encrypted when sent over and decrypted once the server receives it, but the project (at the moment) uses HTTP instead of HTTPS, so a hacker might be lucky if they managed to use a packet sniffer like *Wireshark* to sniff out this request and get the password.
    * `"email"` - the user's email address for the site.