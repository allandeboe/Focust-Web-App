/**
 *  Home.tsx -- handles the home page (see below for more details)
 *  Copyright (C) 2023  Allan DeBoe
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/
 * 
 *  //////////////////////////////////////////////////////////////////////
 * 
 *  This handles the homepage, which contains a brief description of what
 *  the site is about.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 12th, 2023
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
                <div className="container">
                    <div className="dark-segment">
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
                    <div className="dark-segment">
                        <h1>Lorem Ipsum</h1>
                        <p>  </p>
                    </div>
                </div>
            </div>
        );
    }
}