/**
 * ProjectRepository
 *
 * This interface is used to allow the interaction between
 * The Java code and the database, at least when it comes
 * to the data about projects.
 *
 * Usually, these would be empty and thus only contain
 * the basic functionality given to any JPA Repository,
 * but one can add abstract methods that map to SQL
 * queries for certain situations where using SQL
 * would be less of a headache to use.
 *
 * This will also be used for the REST controllers,
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 10th, 2023
 */
package com.focust.api.model.repository;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.response.UserProjectDetails;
import com.focust.api.model.data.Project;

/** Spring Framework **/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

///////////////////////////////////////////////////////////

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.name = :project_name")
    Project getByProjectName(@Param("project_name") String name);

    @Query("SELECT NEW com.focust.api.dto.response.UserProjectDetails(p.id, p.name, m.role) FROM Project p INNER JOIN p.members m WHERE m.user.id = :userId")
    Page<UserProjectDetails> getJoinedProjects(@Param("userId") Long userId, Pageable pageable);

}
