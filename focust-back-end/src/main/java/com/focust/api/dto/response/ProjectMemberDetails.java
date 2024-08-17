/**
 * ProjectMemberDetails -- DTO Response consisting of details regarding the members of a given project (more detailed description below).
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
 * //////////////////////////////////////////////////////////////////////
 *
 * This class represents the response that is used to
 * mark members of a project.
 *
 * @see com.focust.api.model.data.User
 * @see com.focust.api.model.relational.ProjectMembership
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 22nd, 2023
 */
package com.focust.api.dto.response;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Response;
import com.focust.api.enums.ProjectRole;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;

///////////////////////////////////////////////////////////

public class ProjectMemberDetails implements Response {

    @Getter
    private final Long userId;

    @Getter
    private final String username;

    @Getter
    private final ProjectRole projectRole;

    public ProjectMemberDetails(@NonNull Long userId, @NonNull String username, @NonNull ProjectRole projectRole) {
        this.userId = userId;
        this.username = username;
        this.projectRole = projectRole;
    }

}
