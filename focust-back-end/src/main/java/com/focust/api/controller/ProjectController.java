/**
 * ProjectController -- Controller for user-made projects (more detailed description below).
 * Copyright (C) 2023  Allan DeBoe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 * //////////////////////////////////////////////////////////////////////
 *
 * This class is the controller associated with the "User" model.
 * It translates REST commands into actions that affect the "users"
 * table in the database.
 *
 * @see com.focust.api.model.data.Project
 * @see com.focust.api.model.relational.ProjectMembership
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 17th, 2023
 */
package com.focust.api.controller;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.controller.util.CRUDController;
import com.focust.api.dto.request.NewProjectRequest;
import com.focust.api.dto.response.ProjectDetails;
import com.focust.api.dto.response.ProjectMemberDetails;
import com.focust.api.dto.util.ResponseCreator;
import com.focust.api.enums.ProjectRole;
import com.focust.api.model.data.Project;
import com.focust.api.model.data.User;
import com.focust.api.model.relational.ProjectMembership;
import com.focust.api.model.repository.ProjectRepository;
import com.focust.api.model.repository.UserRepository;

/** JPA / Jakarta **/
import jakarta.persistence.EntityNotFoundException;

/** Spring Framework **/
import org.springframework.beans.factory.annotation.Autowired;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/** Standard Java **/
import java.util.List;
import java.util.Optional;

///////////////////////////////////////////////////////////

@RestController
@RequestMapping(value="/projects")
public class ProjectController {

    @Autowired
    ProjectRepository projectRepository;

    @Autowired
    UserRepository userRepository;

    ///////////////////////////////////////////////////////

    @PostMapping(value="", produces="application/json")
    public ResponseEntity<ProjectDetails> create(@RequestBody NewProjectRequest formData) {

        Optional<Project> existingProject = Optional.ofNullable(projectRepository.getByProjectName(formData.getName()));
        if (existingProject.isPresent()) return new ResponseEntity<>(null, HttpStatus.OK);

        // The CRUDController.create() function isn't used as it is only used when we only need
        // to add a new entry to only one table. In this situation, we need two tables to update,
        // as we want to mark that a given user is the owner of the newly created project.
        try {
            Project newProject = formData.unload();
            User existingUser = userRepository.getReferenceById(formData.getCreatorId());

            // Creating a new membership here ensures that both the new Project
            // and the existing User objects have the same member.
            ProjectMembership membership = new ProjectMembership();
            membership.setRole(ProjectRole.PROJECT_OWNER);
            membership.setProject(newProject);
            membership.setUser(existingUser);

            newProject.addMember(membership);
            existingUser.addProject(membership);

            // Trying to save both the user and project repositories will
            // cause problems, so just don't.
            projectRepository.save(newProject);

            ResponseCreator<ProjectDetails, Project> translator = new ResponseCreator<>(ProjectDetails.class);
            return new ResponseEntity<>(translator.createResponse(newProject), HttpStatus.CREATED);
        }
        catch (EntityNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    ///////////////////////////////////////////////////////

    @GetMapping(value="", produces="application/json")
    public ResponseEntity<List<ProjectDetails>> getAll(@RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int entriesPerPage) {
        return CRUDController.getAll(projectRepository, ProjectDetails.class, page, entriesPerPage);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<ProjectDetails> getById(@PathVariable long id) {
        return CRUDController.getById(projectRepository, id, ProjectDetails.class);
    }

    @GetMapping(value="/{id}/members", produces="application/json")
    public ResponseEntity<List<ProjectMemberDetails>> getMembers(@PathVariable long id, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "15") int entriesPerPage) {
        try {

            if (page < 0 || entriesPerPage < 1) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            // userRepository is used as it makes it easier to get the needed data
            Pageable firstPage = PageRequest.of(page, entriesPerPage);
            Page<ProjectMemberDetails> allEntries = userRepository.getMembersOf(id, firstPage);
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
