/**
 * Users.tsx
 * 
 * This handles the users page, which lists out all of the users
 * of the site.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 12th, 2023
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