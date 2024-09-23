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

import { Heading1 } from '../components/headers/Heading1';
import { Heading2 } from '../components/headers/Heading2';
import { Container } from '../components/Container';
import { Segment } from '../components/Segment';
import { OpaqueSegment } from '../components/OpaqueSegment';
import { PageLink } from '../components/PageLink';

export default class Home extends Component {

    render() {
        return (
            <div>
                <Segment className="text-center">
                    <Heading1>Focust</Heading1>
                </Segment>
                <Container>
                    <OpaqueSegment>
                        <Heading2>An <PageLink to="https://www.atlassian.com/software/jira">issue tracker web app</PageLink> of all time!</Heading2>
                        <p>
                        Focust is an issue tracker web app that is made 
                        using <PageLink to="https://react.dev/">React.js</PageLink>
                        , <PageLink to="https://www.typescriptlang.org/">TypeScript</PageLink>
                        , <PageLink to="https://tailwindcss.com/">Tailwind CSS</PageLink>
                        , <PageLink to="https://spring.io/">Java Spring</PageLink>
                        , and <PageLink to="https://www.mysql.com/">MySQL</PageLink>.
                        </p>
                    </OpaqueSegment>
                    <OpaqueSegment>
                        <Heading2>Lorem Ipsum</Heading2>
                        <p>  </p>
                    </OpaqueSegment>
                </Container>
            </div>
        );
    }
}