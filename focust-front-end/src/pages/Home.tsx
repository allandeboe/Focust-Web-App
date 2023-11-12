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
                <h1>Focust is an <a href="https://www.atlassian.com/software/jira">issue tracker web app</a> of all time!</h1>
                <p>
                Focust is an issue tracker web application that I have made just to put
                something on my resume to show that I know how to  
                use <a href="https://react.dev/">React.js</a>
                , <a href="https://www.typescriptlang.org/">TypeScript</a>
                , <a href="https://tailwindcss.com/">Tailwind CSS</a>
                , <a href="https://spring.io/">Java Spring</a>
                , <a href="https://www.mysql.com/">MySQL</a>
                , and so on because you need proof that you know how to code and not
                just lucked your way out of community college and university with a 
                Software Engineering degree - <i>duhhh!</i>
                </p>
            </div>
        );
    }
}