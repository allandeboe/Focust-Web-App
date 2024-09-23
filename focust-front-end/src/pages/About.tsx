/**
 *  About.tsx -- handles the about page
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
 *  This handles the about page.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 14th, 2023
 */
import { Component } from "react";

import { Heading1 } from '../components/headers/Heading1';
import { Heading2 } from '../components/headers/Heading2';
import { Container } from '../components/Container';
import { Segment } from '../components/Segment';
import { OpaqueSegment } from '../components/OpaqueSegment';
import { PageLink } from '../components/PageLink';

export default class About extends Component {

    render() {
        return (
            <div>
                <Segment className="text-center">
                    <Heading1>About Us</Heading1>
                </Segment>
                <Container>
                    <OpaqueSegment>
                        <Heading2>Allan DeBoe</Heading2>
                        <p>
                        Allan is a Software Engineering graduate from <PageLink to="https://www.asu.edu">Arizona State University</PageLink> and is the sole creator and developer of Focust - his personal flagship
                        project that demonstrates his skills as a full-stack developer.
                        </p>
                        <p>
                        He graduated from ASU in 2023 with a bachelor's in software engineering, and 3 years before that he got two associate's degrees 
                        from <PageLink to="https://www.gccaz.edu/">Glendale Community College (GCC)</PageLink> - an associate of science and an associate of arts. He first taught himself how to code in 2013, 
                        with Lua being his first programming language as he wanted to create Roblox games. This blossomed to him pursuing a degree in software engineering. Since he focused his attention 
                        on his education, he didn't do internships, and tried to compensate by working on projects - primarily Focust - to show that he has hands-on experience in writing software.
                        </p>
                        <p>
                        Outside of <PageLink to="https://code.visualstudio.com/">Visual Studio Code</PageLink> & <PageLink to="https://www.jetbrains.com/idea/">IntelliJ Idea</PageLink>, he has various hobbies he enjoys doing that 
                        doesn't involve a computer doing everything for him, like drawing & making music. 
                        </p>
                    </OpaqueSegment>
                </Container>
            </div>
        );
    }
}