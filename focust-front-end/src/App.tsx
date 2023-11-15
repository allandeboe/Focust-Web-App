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