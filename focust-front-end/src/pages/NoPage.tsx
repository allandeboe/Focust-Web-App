/**
 * NoPage.tsx
 * 
 * This handles any invalid url for the site.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 12th, 2023
 */
import { Component } from "react";
import { Link } from "react-router-dom";

export default class Home extends Component {

    render() {
        return (
            <div className="text-center">
                <div className="segment">
                    <h1>Page not found or doesn't exist</h1>
                </div>
                <div className="container">
                    <div className="dark-segment">
                        <p>
                        To go back to the main page, <Link to="/">click here!</Link>
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}