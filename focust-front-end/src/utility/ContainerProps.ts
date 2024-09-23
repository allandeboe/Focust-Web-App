/**
 *  ContainerProps.tsx -- "props" for Containers (see below for more details)
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
 *  along with this program. If not, see https://www.gnu.org/licenses/
 * 
 *  //////////////////////////////////////////////////////////////////////
 * 
 *  This is a TypeScript type that is used to allow Containers to encapsulate
 *  text much like "div"
 * 
 *  @see Container.tsx
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   September 22th, 2024
 */

export type ContainerProps = {
    children: | JSX.Element | JSX.Element[] | string | string[] | (string | JSX.Element)[]
}