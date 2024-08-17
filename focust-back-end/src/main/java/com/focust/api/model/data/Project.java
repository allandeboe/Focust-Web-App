/**
 * Project -- Data class for user-made projects (more detailed description below).
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
 * This class corresponds to the "projects" table in the MySQL
 * database, which handles the Projects that users can join and
 * work under.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 9th, 2023
 */
package com.focust.api.model.data;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.model.relational.ProjectMembership;

/** JPA / Jakarta / Hibernate **/
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

/** Lombok **/
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

/** Standard Java **/
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;

///////////////////////////////////////////////////////////

@Entity @Table(name="projects")
public class Project {

    @Getter @Setter(AccessLevel.PROTECTED)
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private @Column(name="project_id") Long id;

    @Getter @Setter
    private @Column(name="project_name", length=32, nullable=false, unique=true) String name;

    @Getter @Setter
    private @Column(length=255) String description;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private @Column(name="created_on", updatable=false) ZonedDateTime creationDate;

    @Getter @Setter
    @OneToMany(mappedBy="project", cascade=CascadeType.ALL, orphanRemoval=true)
    private Set<ProjectMembership> members;

    public Project() {
        this.creationDate = ZonedDateTime.now();
        this.members = new HashSet<>();
    }

    // This method is used when a user joins or creates a project.
    public void addMember(ProjectMembership membership) {
        this.members.add(membership);
        for (ProjectMembership member: this.members) {
            member.setProject(this);
        }
    }

}
