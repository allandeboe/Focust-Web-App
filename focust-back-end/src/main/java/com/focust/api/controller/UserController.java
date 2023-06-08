/***********************************************************************************************************************
 * UserController.java
 *
 * @see com.focust.api.model.User
 * @see com.focust.api.util.Controller
 *
 * This class represents the "Controller" corresponding to the table "users" in the Database.
 *
 * @author Allan DeBoe
 */
package com.focust.api.controller;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Java Standard Library
 */
import java.util.List;

/*
 * Focust
 */
import com.focust.api.model.User;
import com.focust.api.model.dto.SafeUserDTO;
import com.focust.api.repository.UserRepository;
import com.focust.api.util.Controller;

/*
 * Java Spring
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CLASS

@RestController
@RequestMapping("/users")
public final class UserController {

    @Autowired
    UserRepository userRepository;

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    // "http://localhost:8080/users"

    /**
     * getAllUsers(): ResponseEntity
     *
     * "GET http://localhost:8080/users"
     *
     * Returns a list of users on the site.
     *
     * @return a response to the client-side containing at least a Http Status Code.
     */
    @GetMapping("")
    public ResponseEntity<List<SafeUserDTO>> getAllUsers() {
        return Controller.getAll(userRepository);
    }

    /**
     * createUser(user: User): ResponseEntity
     *
     * "POST http://localhost:8080/users"
     *
     * Creates and adds a new user to the "users" table.
     *
     * For future implementations, the request body should be a Data Transfer Object
     * (DTO) that contains user essentials so that both "users" and "authentication-info"
     * tables will both have new entries.
     *
     * @return a response to the client-side containing at least a Http Status Code.
     */
    @PostMapping("")
    public ResponseEntity<SafeUserDTO> createUser(@RequestBody User user) {
        return Controller.create(userRepository, user);
    }

    /**
     * deleteAllUsers(): ResponseEntity
     *
     * "DELETE http://localhost:8080/users"
     *
     * Deletes all of the users from the site.
     *
     * Should only be possible when testing/debugging the server using a mock database, and thus
     * should be allowed to be used once deployed and connected to the real database
     * (for security reasons).
     *
     * @see com.focust.api.util.Controller
     *
     * @return a response to the client-side containing a Http Status Code.
     */
    @DeleteMapping("")
    public ResponseEntity<HttpStatus> deleteAllUsers() {
        return Controller.deleteAll(userRepository);
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    // "http://localhost:8080/users/{id}"

    /**
     * getUserById(id: long): ResponseEntity
     *
     * Used to extract user information (except for sensitive user data) form a user
     * with the given user id.
     *
     * @param id the id of the user in question.
     * @return a response to the client-side that contains at least a Http Status Code.
     */
    @GetMapping("/{id}")
    public ResponseEntity<SafeUserDTO> getUserById(@PathVariable("id") long id) {
        return Controller.getById(userRepository, id);
    }

    /**
     * updateUser(id: long, user: User): ResponseEntity
     *
     * updates the data stored in the database.
     *
     * @param id the id of the user to be updated
     * @param user a User instance that contains the data to be updated.
     * @return a response to the client-side that contains at least a Http Status Code.
     */
    @PutMapping("/{id}")
    public ResponseEntity<SafeUserDTO> updateUser(@PathVariable("id") long id, User user) {
        return Controller.update(userRepository, id, user);
    }

}
