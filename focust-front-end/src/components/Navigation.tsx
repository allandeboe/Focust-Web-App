/**
 *  Navigation.tsx -- Handles the navigation bar (see below for more details)
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
 *  This handles the navigation bar that appears
 *  on the top of the site.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 14th, 2023
 */

import { Component } from "react";
import { Link } from "react-router-dom";
import Banner from '../banner.svg';

export class Navigation extends Component {

    render() {
        return (
            <div className="navigation">
                <div className="text-center block" style={{ margin: "auto", width: "15%" }}>
                    <img src={Banner} alt="Focust Banner" className="w-full"/>
                </div>
                <nav>
                    <Link to="/">Home</Link>
                    <Link to="/about-us">About Us</Link>
                    <Link to="/users">Users</Link>
                </nav>
            </div>
        );
    }
}