/**
 * NewUserRequest -- Request to add a new user to database (more detailed description below).
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
 * This class is a data transfer object (DTO) that is sent to
 * the back-end server containing details needed to add a new
 * user to the database.
 *
 * @see com.focust.api.model.data.User
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.request;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Request;
import com.focust.api.model.data.User;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

///////////////////////////////////////////////////////////

@RequiredArgsConstructor
public class NewUserRequest implements Request<User> {

    @Getter @NonNull
    private final String username;

    @Getter @NonNull
    private final String password;

    @Getter @NonNull
    private final String email;

    @Override
    public User unload() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        return user;
    }

}
