/**
 * About.tsx
 * 
 * This handles the about page.
 * 
 * @author Allan DeBoe <allan.m.deboe@gmail.com>
 * @date November 14th, 2023
 */
import { Component } from "react";
import { Link } from "react-router-dom";

export default class About extends Component {

    render() {
        return (
            <div>
                <div className="segment text-center">
                    <h1>About Us</h1>
                </div>
                <div className="items-start justify-evenly flex-wrap">
                    <div className="dark-segment max-w-50">
                        <h2>Allan DeBoe</h2>
                        <p>
                        Allan is a Software Engineering graduate from <Link to="https://www.asu.edu">Arizona State University</Link> and is the sole creator and developer of Focust, which he made in an attempt
                        to help him get an entry-level position and start a career.
                        </p>
                        <p>
                        Considering he wanted to focus on his education, he doesn't have any relevant work experience, which puts him at a disadvantage once he graduated, as many 
                        entry-level job positions he could otherwise qualify for requires at least <b>1-2 years</b> of professional experience - something <i>he doesn't have</i>. Worse yet,
                        since he graduated with little to no intention of getting his masters, he doesn't even qualify for many internships, most of which are only applicable to non-alumni.
                        </p>
                        <p>
                        So, to make himself stand out and to increase his chances of getting his foot in the door, he decided to work on a <i>massive flagship project</i> when everyone else is making 
                        toy projects like calculators or games; he worked on making an <b>issue tracker web application</b> - <b><Link to="/">Focust</Link></b>.
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}