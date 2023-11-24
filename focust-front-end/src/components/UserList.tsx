/**
 * UserList.tsx
 * 
 * This handles the list of users on the site. This
 * will only work once the back-end is active.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 10th, 2023
 */
import React, { Component } from 'react';
import { Link } from "react-router-dom";
import '../main_style.css';

///////////////////////////////////////

// As "BasicUserDetails" is what the back-end server
// will send back.
interface BasicUserDetails {
    id: number;
    username: string;
    email: string;
    github?: string;
}
  
///////////////////////////////////////

function Loading() {
    return (
        <p className="italic">Loading...</p>
    );
}

function NoUsersDisplay() {
    return (
        <p className="italic">No user data found.</p>
    );
}

function BasicUserDisplay(user: BasicUserDetails) {
    return (
        <tr key={user.id}>
            <td><Link to={`/users/${user.id}`}>{user.username}</Link></td>
            <td>{user.email}</td>
        </tr>
    );
}

///////////////////////////////////////

// Gets the list of users
export class UserList extends Component {
  
    set: boolean;
    proxy_failed: boolean;

    constructor(props: any) {
        super(props);
        this.set = false;
        this.proxy_failed = false;
        this.state = {users: []};
    }
  
    componentDidMount() {
        fetch('/users', {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then((response) => {
            if (response.status >= 300) { 
                this.proxy_failed = true;
                return []; 
            }
            if (response.status === 204) {
                return [];
            }
            return response.json();
        }).then((data) => {
            if (!this.proxy_failed) {
                this.set = true;
                this.setState({users: data});
            }
        });
    }
  
    render() {

        const {users, isLoading}: any = this.state;
  
        if (isLoading) { return (<Loading/>); }
        if (!this.set) { return (<NoUsersDisplay/>); }
        if (users.length < 1) { return (<NoUsersDisplay/>); }

        const userList = users.map(BasicUserDisplay);

        return (
            <table>
                <thead>
                    <th>Username</th>
                    <th>Email</th>
                </thead>
                <tbody>
                    {userList}
                </tbody>
            </table>
        );
  
    }
  
}