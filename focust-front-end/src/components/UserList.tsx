/**
 * userList.tsx
 * 
 * This handles the list of users on the site. This
 * will only work once the back-end is active.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 10th, 2023
 */
import React, { Component } from 'react';
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
        <p className="text-center italic">Loading...</p>
    );
}

function FailedToDisplayUsers() {
    return (
        <p className="text-center italic">Sorry! We weren't able to get the user data.</p>
    );
}
  
function BasicUserDisplay(user: BasicUserDetails) {
    return (
        <tr key={user.id}>
            <td>{user.username}</td>
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
        const userList = users.map(BasicUserDisplay);

        // So that the site is still going even if the back-end server is not available...
        if (!this.set) { return (<FailedToDisplayUsers/>); }

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