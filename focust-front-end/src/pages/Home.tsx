/**
 * Home.tsx
 * 
 * This handles the homepage, which contains a brief description of what
 * the site is about.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 12th, 2023
 */
import { Component } from "react";

export default class Home extends Component {

    render() {
        return (
            <div>
                <div className="segment text-center">
                    <h1>Focust</h1>
                </div>
                <div className="dark-segment max-w-30">
                    <h1>An <a href="https://www.atlassian.com/software/jira">issue tracker web app</a> of all time!</h1>
                    <p>
                    Focust is an issue tracker web app that is made 
                    using <a href="https://react.dev/">React.js</a>
                    , <a href="https://www.typescriptlang.org/">TypeScript</a>
                    , <a href="https://tailwindcss.com/">Tailwind CSS</a>
                    , <a href="https://spring.io/">Java Spring</a>
                    , and <a href="https://www.mysql.com/">MySQL</a>.
                    </p>
                </div>
            </div>
        );
    }
}