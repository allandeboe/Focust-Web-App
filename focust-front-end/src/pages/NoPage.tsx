/**
 *  NoPage.tsx -- Used for any invalid url (see below for more details)
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
 *  This handles any invalid url for the site.
 *  
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 12th, 2023
 */

import { Component } from "react";

import { Heading1 } from '../components/headers/Heading1';
import { Container } from '../components/Container';
import { Segment } from '../components/Segment';
import { OpaqueSegment } from '../components/OpaqueSegment';
import { PageLink } from '../components/PageLink';

export default class Home extends Component {

    render() {
        return (
            <div className="text-center">
                <Segment>
                    <Heading1>Page not found or doesn't exist</Heading1>
                </Segment>
                <Container>
                    <OpaqueSegment>
                        <p>
                        To go back to the main page, <PageLink to="/">click here!</PageLink>
                        </p>
                    </OpaqueSegment>
                </Container>
            </div>
        );
    }
}