/**
 * ProjectRole -- Enum for user role within projects (more detailed description below).
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
 * This enum is used to mark what role the user has in a given project.
 *
 * @see com.focust.api.model.data.User
 * @see com.focust.api.model.data.Project
 * @see com.focust.api.model.relational.ProjectMembership
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 11th, 2023
 */
package com.focust.api.enums;

///////////////////////////////////////////////////////////

public enum ProjectRole {
    DEVELOPER,
    PROJECT_OWNER
}
