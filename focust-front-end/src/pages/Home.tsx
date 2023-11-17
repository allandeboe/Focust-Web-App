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
import { Link } from "react-router-dom";

export default class Home extends Component {

    render() {
        return (
            <div>
                <div className="segment text-center">
                    <h1>Focust</h1>
                </div>
                <div className="items-start justify-evenly flex-wrap">
                    <div className="dark-segment max-w-30">
                        <h1>An <Link to="https://www.atlassian.com/software/jira">issue tracker web app</Link> of all time!</h1>
                        <p>
                        Focust is an issue tracker web app that is made 
                        using <Link to="https://react.dev/">React.js</Link>
                        , <Link to="https://www.typescriptlang.org/">TypeScript</Link>
                        , <Link to="https://tailwindcss.com/">Tailwind CSS</Link>
                        , <Link to="https://spring.io/">Java Spring</Link>
                        , and <Link to="https://www.mysql.com/">MySQL</Link>.
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}