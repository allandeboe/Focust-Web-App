/**
 * UserController
 *
 * This class is the controller associated with the "User" model.
 * It translates REST commands into actions that affect the "users"
 * table in the database.
 *
 * @see com.focust.api.model.data.User
 * @see com.focust.api.model.relational.ProjectMembership
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.controller;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.controller.util.CRUDController;
import com.focust.api.dto.response.BasicUserDetails;
import com.focust.api.dto.request.NewUserRequest;
import com.focust.api.dto.response.UserProjectDetails;
import com.focust.api.model.data.User;
import com.focust.api.model.repository.ProjectRepository;
import com.focust.api.model.repository.UserRepository;

/** Standard Java **/
import java.util.List;
import java.util.Optional;

/** Spring Framework **/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

    @Autowired
    ProjectRepository projectRepository;

    ///////////////////////////////////////////////////////

    @PostMapping(value="", produces="application/json")
    public ResponseEntity<BasicUserDetails> create(@RequestBody NewUserRequest formData) {

        // Don't create a user that already exists...
        Optional<User> existingUser = Optional.ofNullable(userRepository.getByUsername(formData.getUsername()));
        if (existingUser.isPresent()) return new ResponseEntity<>(null, HttpStatus.OK);

        return CRUDController.create(userRepository, formData, BasicUserDetails.class);
    }

    ///////////////////////////////////////////////////////

    @GetMapping(value="", produces="application/json")
    public ResponseEntity<List<BasicUserDetails>> getAll() {
        return CRUDController.getAll(userRepository, BasicUserDetails.class);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<BasicUserDetails> getById(@PathVariable long id) {
        return CRUDController.getById(userRepository, id, BasicUserDetails.class);
    }

    @GetMapping(value="/{id}/projects", produces="application/json")
    public ResponseEntity<List<UserProjectDetails>> getJoinedProjects(@PathVariable long id) {
        try {

            // projectRepository is used as it makes it easier to get the needed data
            Pageable firstPage = PageRequest.of(0, 15);
            Page<UserProjectDetails> allEntries = projectRepository.getJoinedProjects(id, firstPage);
            if (allEntries.isEmpty()) { return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); }

            return new ResponseEntity<>(allEntries.toList(), HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ///////////////////////////////////////////////////////

}
