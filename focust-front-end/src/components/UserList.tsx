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
        <p>Loading...</p>
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
  
// Gets the list of users
export class UserList extends Component {
  
    constructor(props: any) {
        super(props);
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
        .then(response => response.json())
        .then(data => this.setState({users: data}))
    }
  
    render() {
  
        const {users, isLoading}: any = this.state;
  
        if (isLoading) { return (<Loading></Loading>); }
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