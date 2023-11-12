/**
 * ProjectMembership
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

    @Getter @Setter
    @ManyToOne @MapsId("userId") @JoinColumn(name = "user_id")
    private User user;

    @Getter @Setter
    @ManyToOne @MapsId("projectId") @JoinColumn(name = "project_id")
    private Project project;

    @Getter @Setter
    @Enumerated(EnumType.STRING)
    @Column(name="project_role")
    private ProjectRole role;

}
