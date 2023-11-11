/**
 * App.tsx
 * 
 * This handles the general view of the home-page and
 * the web application as a whole.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 10th, 2023
 */
import React, { Component } from 'react';
import './main_style.css';

// List of Users on the site...
import { UserList } from "./components/UserList";

///////////////////////////////////////

export default class App extends Component {

render() {
    return (
        <div className="App">
            <h1>Users</h1>
            <hr/>
            <UserList/>
        </div>
    );
    }

}