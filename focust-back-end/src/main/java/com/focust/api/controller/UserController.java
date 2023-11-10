/**
 * UserController
 *
 * This class is the controller associated with the "User" model.
 * It translates REST commands into actions that affect the "users"
 * table in the database.
 *
 * @see com.focust.api.model.data.User
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.controller;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.controller.util.CRUDController;
import com.focust.api.dto.view.UserDetails;
import com.focust.api.dto.form.NewUserForm;
import com.focust.api.model.data.User;
import com.focust.api.model.repository.UserRepository;

/** Standard Java **/
import java.util.List;
import java.util.Optional;

/** Spring Framework **/
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;

///////////////////////////////////////////////////////////

@RestController
@RequestMapping(value="/users")
public class UserController {

    @Autowired
    UserRepository userRepository;

    @PutMapping(value="", produces="application/json")
    public ResponseEntity<UserDetails> create(@RequestBody NewUserForm formData) {

        // Don't create a user that already exists...
        Optional<User> existingUser = Optional.ofNullable(userRepository.getByUsername(formData.getUsername()));
        if (existingUser.isPresent()) return new ResponseEntity<>(null, HttpStatus.OK);

        return CRUDController.create(userRepository, formData, UserDetails.class);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<UserDetails> getById(@PathVariable long id) {
        return CRUDController.getById(userRepository, id, UserDetails.class);
    }

    @GetMapping(value="", produces="application/json")
    public ResponseEntity<List<UserDetails>> getAll() {
        return CRUDController.getAll(userRepository, UserDetails.class);
    }

}
