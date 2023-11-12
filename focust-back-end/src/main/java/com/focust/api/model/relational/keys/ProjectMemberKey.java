/**
 * ProjectMemberKey
 *
 * This class is used as a composite key for the "project_membership" table
 *
 * @see com.focust.api.model.data.User
 * @see com.focust.api.model.data.Project
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 11th, 2023
 */
package com.focust.api.model.relational.keys;

///////////////////////////////////////////////////////////

/** JPA / Hibernate **/
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/** Lombok **/
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/** Standard Java **/
import java.io.Serializable;

///////////////////////////////////////////////////////////

@Embeddable
@AllArgsConstructor @Getter @Setter
public class ProjectMemberKey implements Serializable {

    @Column(name="user_id")
    private Long userId;

    @Column(name="project_id")
    private Long projectId;

}
