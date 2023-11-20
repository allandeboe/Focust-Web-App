/**
 * ProjectController
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
import com.focust.api.dto.util.ResponseCreator;
import com.focust.api.enums.ProjectRole;
import com.focust.api.model.data.Project;
import com.focust.api.model.data.User;
import com.focust.api.model.relational.ProjectMembership;
import com.focust.api.model.repository.ProjectRepository;

/** Spring Framework **/
import com.focust.api.model.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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


        // return CRUDController.create(projectRepository, formData, ProjectDetails.class);
    }

    @GetMapping(value="/{id}", produces="application/json")
    public ResponseEntity<ProjectDetails> getById(@PathVariable long id) {
        return CRUDController.getById(projectRepository, id, ProjectDetails.class);
    }

    @GetMapping(value="", produces="application/json")
    public ResponseEntity<List<ProjectDetails>> getAll() {
        return CRUDController.getAll(projectRepository, ProjectDetails.class);
    }

    ///////////////////////////////////////////////////////
    
    
}
