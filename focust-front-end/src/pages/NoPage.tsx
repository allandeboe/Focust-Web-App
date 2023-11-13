/**
 * NoPage.tsx
 * 
 * This handles any invalid url for the site.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 12th, 2023
 */
import { Component } from "react";

export default class Home extends Component {

    render() {
        return (
            <div className="text-center">
                <h1>Page not found or doesn't exist</h1>
                <p>
                To go back to the main page, <a href="/">click here!</a>
                </p>
            </div>
        );
    }
}