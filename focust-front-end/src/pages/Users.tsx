/**
 *  Users.tsx -- handles the users page (see below for more details)
 *  Copyright (C) 2023  Allan DeBoe
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/
 * 
 *  //////////////////////////////////////////////////////////////////////
 * 
 *  This handles the users page, which lists out all of the users
 *  of the site.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 12th, 2023
 */
import { Component } from "react";
import { UserList } from "../components/UserList";

export default class Users extends Component {

    render() {
        return (
            <div>
                <div className="segment text-center">
                    <h1>Users</h1>
                </div>
                <div className="container">
                    <div className="dark-segment">
                        <UserList/>
                    </div>
                </div>
            </div>
        );
    }

}