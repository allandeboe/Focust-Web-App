/**
 * Project
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

/** JPA / Hibernate **/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;

import jakarta.persistence.TemporalType;
import jakarta.persistence.GenerationType;

/** Lombok **/
import lombok.Getter;
import lombok.Setter;

import lombok.AccessLevel;

/** Standard Java / JDBC **/
import java.util.Date;
import java.util.List;

///////////////////////////////////////////////////////////

@Entity @Table(name="projects")
public class Project {

    @Getter @Setter(AccessLevel.PROTECTED)
    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    private @Column(name="project_id") Long id;

    @Getter @Setter
    private @Column(name="project_name", length=32, nullable=false, unique=true) String name;

    @Getter @Setter
    private @Column(length=255) String description;

    @Getter @Setter
    @Temporal(TemporalType.TIMESTAMP)
    private @Column(name="created_on", updatable = false) Date creation_date;

    @Getter @Setter
    @OneToMany(mappedBy="project")
    private List<ProjectMembership> members;

}
