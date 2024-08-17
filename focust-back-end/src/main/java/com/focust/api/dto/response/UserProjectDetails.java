/**
 * UserProjectDetails -- DTO Response consisting of details regarding the projects a given user is a part of (more detailed description below).
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
 * mark the projects that a given user is a member of.
 *
 * @see com.focust.api.model.data.Project
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

public class UserProjectDetails implements Response {

    @Getter
    private final Long projectId;

    @Getter
    private final String projectName;

    @Getter
    private final ProjectRole projectRole;

    public UserProjectDetails(@NonNull Long projectId, @NonNull String projectName, @NonNull ProjectRole projectRole) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectRole = projectRole;
    }

}
