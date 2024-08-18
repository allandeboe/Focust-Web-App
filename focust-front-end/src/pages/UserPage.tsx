/**
 *  UserPage.tsx -- Handles the creation of the profile page of each user (see below for more details)
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
 *  This handles the general profile page for a given user. This is
 *  important as there will be users on the site and we 
 *  need a way to display general user information.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 24th, 2023
 */

import { Component } from "react";
import { useParams } from "react-router-dom";

import UserProjectList from "../components/UserProjectList";

import NoPage from "./NoPage";

import '../main_style.css';

///////////////////////////////////////

// This is to allow the class to extract the
// Route parameter "userId", which is needed to
// properly display the page.
export default function UserPage(props: any) {
    const params = useParams();
    console.log(params.id)
    return (
        <UserPageClass {...props} params={params} />
    );
}

///////////////////////////////////////

class UserPageClass extends Component {

    user_set: boolean;
    proxy_failed: boolean;

    constructor(props: any) {
        super(props);
        this.user_set = false;
        this.proxy_failed = false;
        this.state = {
            user: {}
        };
    }

    fetchUserData(id: number) {
        fetch(`/users/${id}`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then((response) => {
            if (response.status >= 300) { 
                this.proxy_failed = true;
                return {}; 
            }
            if (response.status === 204) {
                return {};
            }
            return response.json();
        }).then((data) => {
            if (!this.proxy_failed) {
                this.user_set = true;
                this.setState({user: data});
            }
        });
    }

    componentDidMount() {
        const { params }: any = this.props;
        this.fetchUserData(parseInt(params.userId));
    }

    render() {
        const { user }: any = this.state;

        if (!this.user_set) { return (<NoPage/>); }

        return (
            <div>
                <div className="segment text-center">
                    <h1>{user.username}</h1>
                </div>
                <div className="container">
                    <div className="dark-segment">
                        <table>
                            <thead>
                                <th>Email</th>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>{user.email}</td>
                                </tr>
                            </tbody>
                        </table>
                    </div>
                </div>
                <div className="container">
                    <div className="dark-segment">
                        <h2>Projects</h2>
                        <UserProjectList params={this.props}/>
                    </div>
                </div>
            </div>
        );
            
    }

}