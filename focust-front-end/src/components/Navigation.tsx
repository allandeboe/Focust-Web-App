/**
 * Navigation.tsx
 * 
 * This handles the navigation bar that appears
 * on the top of the site.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 14th, 2023
 */

import { Component } from "react";
import { Link } from "react-router-dom";

export class Navigation extends Component {

    render() {
        return (
            <nav className="bg-transparent p-4 text-center">
                <Link to="/">Home</Link>
                <Link to="/about-us">About Us</Link>
                <Link to="/users">Users</Link>
            </nav>
        );
    }
}