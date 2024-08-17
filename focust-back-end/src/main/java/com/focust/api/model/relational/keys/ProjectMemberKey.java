/**
 * ProjectMemberKey -- Composite key for "project_membership" table (more detailed description below).
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
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/** Standard Java **/
import java.io.Serializable;

///////////////////////////////////////////////////////////

@Embeddable
@NoArgsConstructor @AllArgsConstructor
@EqualsAndHashCode
public class ProjectMemberKey implements Serializable {

    @Getter @Setter
    @Column(name="user_id")
    private Long userId;

    @Getter @Setter
    @Column(name="project_id")
    private Long projectId;

    protected boolean canEqual(final Object other) {
        return other instanceof ProjectMemberKey;
    }

}
