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

/** JPA / Hibernate **/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/** Lombok **/
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

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



}
