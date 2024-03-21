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
                <div className="container">
                    <div className="dark-segment">
                        <h2>Allan DeBoe</h2>
                        <p>
                        Allan is a Software Engineering graduate from <Link to="https://www.asu.edu">Arizona State University</Link> and is the sole creator and developer of Focust - his personal flagship
                        project that demonstrates his skills as a full-stack developer.
                        </p>
                        <p>
                        He graduated from ASU in 2023 with a bachelor's in software engineering, and 3 years before that he got two associate's degrees 
                        from <Link to="https://www.gccaz.edu/">Glendale Community College (GCC)</Link> - an associate of science and an associate of arts. He first taught himself how to code in 2013, 
                        with Lua being his first programming language as he wanted to create Roblox games. This blossomed to him pursuing a degree in software engineering. Since he focused his attention 
                        on his education, he didn't do internships, and tried to compensate by working on projects - primarily Focust - to show that he has hands-on experience in writing software.
                        </p>
                        <p>
                        Outside of <Link to="https://code.visualstudio.com/">Visual Studio Code</Link> & <Link to="https://www.jetbrains.com/idea/">IntelliJ Idea</Link>, he has various hobbies he enjoys doing that 
                        doesn't involve a computer doing everything for him, like drawing & making music. 
                        </p>
                    </div>
                </div>
            </div>
        );
    }
}