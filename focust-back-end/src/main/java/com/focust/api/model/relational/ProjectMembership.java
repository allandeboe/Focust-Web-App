/**
 * ProjectMembership -- Relates projects with its members (more detailed description below).
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
 * This class represents a relational entity between "users" and "projects",
 * as the site should allow users should be allowed to create and join projects.
 *
 * @see com.focust.api.model.data.User
 * @see com.focust.api.model.data.Project
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 11th, 2023
 */
package com.focust.api.model.relational;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.enums.ProjectRole;
import com.focust.api.model.data.Project;
import com.focust.api.model.data.User;
import com.focust.api.model.relational.keys.ProjectMemberKey;

/** JPA / Hibernate **/
import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;

import jakarta.persistence.FetchType;
import jakarta.persistence.EnumType;

/** Lombok **/
import lombok.Getter;
import lombok.Setter;

///////////////////////////////////////////////////////////

@Entity
public class ProjectMembership {

    @Getter @Setter
    @EmbeddedId
    private ProjectMemberKey id;

    @Getter
    @ManyToOne(fetch=FetchType.LAZY) @MapsId("userId") @JoinColumn(name="user_id")
    private User user;

    @Getter
    @ManyToOne(fetch=FetchType.LAZY) @MapsId("projectId") @JoinColumn(name="project_id")
    private Project project;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="project_role")
    private ProjectRole role;

    public ProjectMembership() {
        this.id = new ProjectMemberKey();
    }

    public void setUser(User user) {
        this.user = user;
        this.id.setUserId(user.getId());
    }

    public void setProject(Project project) {
        this.project = project;
        this.id.setProjectId(project.getId());
    }

}
