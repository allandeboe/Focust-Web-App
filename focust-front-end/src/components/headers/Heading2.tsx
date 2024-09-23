/**
 *  Heading2.tsx -- replaces "h2" (see below for more details)
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
 *  This component replaces the need for "h2" to reduce the 
 *  "main_tailwind.css" file to contain only custom utility classes and
 *  transition logic. 
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   September 22th, 2024
 */

import { Component } from "react";
import { HeadingProps } from '../../utility/HeadingProps';

export class Heading2 extends Component<HeadingProps> {

    render() {
        return (
            <h1 className="font-bold text-extra-large leading-none">
                {this.props.children}
            </h1>
        );
    }

}