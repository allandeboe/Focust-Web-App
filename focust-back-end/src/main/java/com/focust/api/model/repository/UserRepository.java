/**
 * UserRepository -- JPA repository for site users (more detailed description below).
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
 * /////////////////////////////////////////////////////////////
 *
 * This interface is used to allow the interaction between
 * The Java code and the database, at least when it comes
 * to the data about users.
 *
 * Usually, these would be empty and thus only contain
 * the basic functionality given to any JPA Repository,
 * but one can add abstract methods that map to SQL
 * queries for certain situations where using SQL
 * would be less of a headache to use.
 *
 * This will also be used for the REST controllers.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 5th, 2023
 */
package com.focust.api.model.repository;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.response.ProjectMemberDetails;
import com.focust.api.model.data.User;

/** Spring Framework **/
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

///////////////////////////////////////////////////////////

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used when checking if a user already exists.
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getByUsername(@Param("username") String username);

    // This is what raw-dogging SQL looks like...
    @Query("SELECT NEW com.focust.api.dto.response.ProjectMemberDetails(u.id, u.username, m.role) FROM User u INNER JOIN u.projects m WHERE m.project.id = :projectId")
    Page<ProjectMemberDetails> getMembersOf(@Param("projectId") Long projectId, Pageable pageable);

}
