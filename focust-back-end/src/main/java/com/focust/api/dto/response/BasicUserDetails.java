/**
 * BasicUserDetails -- DTO Response consisting of non-sensitive user details (more detailed description below).
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
 * This class is one of the few classes that is used to
 * return user data back to the client/front-end.
 *
 * Since sending sensitive information, like password hashes,
 * back to the client is a recipe for a data breach, this
 * Response DTO only returns non-sensitive user information
 * about the user.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.response;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Response;
import com.focust.api.model.data.User;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;

///////////////////////////////////////////////////////////

public class BasicUserDetails implements Response {

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    private String email;

    @Getter
    private String github;

    public BasicUserDetails(@NonNull User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.github = user.getGithubUsername();
    }

}
