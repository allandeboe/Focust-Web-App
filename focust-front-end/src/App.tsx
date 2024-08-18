/**
 *  App.tsx -- handles the general view of web application
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
 *  This handles the general view of the home-page and
 *  the web application as a whole.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 10th, 2023
 */

import React, { Component } from 'react';
import './main_style.css'; // Compiled version of './main_tailwind.css'

import Main from './Main';
import { Navigation } from './components/Navigation';

///////////////////////////////////////

export default class App extends Component {

    render() {
        return (
            <div>
                <Navigation/>
                <Main/>
            </div>
        );
    }
}