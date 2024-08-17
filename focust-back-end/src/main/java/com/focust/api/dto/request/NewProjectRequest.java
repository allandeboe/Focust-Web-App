/**
 * NewProjectRequest -- Request to add a new project to database (more detailed description below).
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
 * /////////////////////////////////////////////////////////////////////
 *
 * This class is a data transfer object (DTO) that is sent to
 * the back-end server containing details needed to add a new
 * project to the database.
 *
 * @see com.focust.api.model.data.Project
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 15th, 2023
 */
package com.focust.api.dto.request;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Request;
import com.focust.api.model.data.Project;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

///////////////////////////////////////////////////////////

@RequiredArgsConstructor
public class NewProjectRequest implements Request<Project> {

    @Getter @NonNull
    private final String name;

    @Getter @NonNull
    private final String description;

    @Getter @NonNull
    private final Long creatorId;

    @Override
    public Project unload() {
        Project project = new Project();
        project.setName(this.name);
        project.setDescription(this.description);
        return project;
    }

}
