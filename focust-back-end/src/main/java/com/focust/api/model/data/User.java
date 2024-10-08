/**
 * User -- Data class for users of the site (more detailed description below).
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
 * This is the class that corresponds to the "users" table
 * in the MySQL database.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 5th, 2023
 */
package com.focust.api.model.data;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.model.relational.ProjectMembership;
import com.focust.api.security.util.SecurityUtil;

/** JPA / Hibernate **/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;

import jakarta.persistence.CascadeType;
import jakarta.persistence.GenerationType;
import jakarta.persistence.TemporalType;

/** Lombok **/
import lombok.Getter;
import lombok.Setter;

import lombok.AccessLevel;

/** Standard Java / JDBC **/
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

///////////////////////////////////////////////////////////

@Entity @Table(name="users")
public class User {

    @Getter @Setter(AccessLevel.PROTECTED)
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private @Column(name="user_id") Long id;

    @Getter @Setter
    private @Column(length=24, nullable=false, unique=true) String username;

    @Getter @Setter
    private @Column(name="password", nullable=false) String passwordHash;

    @Getter @Setter
    private @Column(length=255, unique=true) String email;

    /* The github username is a column for the "users" table as the target
     * audience for an issue tracker (i.e. this project) are software engineers
     * & software developers - all of who would most likely have a GitHub
     * account.
     *
     * This would also come in handy if we want to verify users using their
     * GitHub account.
     */
    @Getter @Setter
    private @Column(name="github", unique=true) String githubUsername;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private @Column(name="joined_on", updatable=false) ZonedDateTime joinDate;

    @Getter @Setter
    @OneToMany(mappedBy="user", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<ProjectMembership> projects;

    public User() {
        this.joinDate = ZonedDateTime.now();
        this.projects = new HashSet<>();
    }

    public void setPassword(String password) {
        this.passwordHash = SecurityUtil.getEncoder().encode(password);
    }

    public void addProject(ProjectMembership membership) {
        this.projects.add(membership);
        for (ProjectMembership project: this.projects) {
            project.setUser(this);
        }
    }

}
