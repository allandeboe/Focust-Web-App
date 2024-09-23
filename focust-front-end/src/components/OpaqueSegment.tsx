/**
 *  OpaqueSegment.tsx -- replaces ".dark-segment" (see below for more details)
 *  Copyright (C) 2024  Allan DeBoe
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
 *  This component is used to replace the CSS Class ".dark-segment" to reduce
 *  the "main_tailwind.css" file to contain only custom utility classes
 *  and transition logic. 
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   September 22nd, 2024
 */

import { Component } from "react";
import { Props } from '../utility/Props';
import { Segment } from './Segment';

export class OpaqueSegment extends Component<Props> {

    render() {
        return (
            <Segment className={`bg-dark-gray grow basis-96 rounded-lg ${this.props.className}`}>
                {this.props.children}
            </Segment>
        );
    }

}