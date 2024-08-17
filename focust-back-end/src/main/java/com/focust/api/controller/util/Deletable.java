/**
 * Deletable -- Interface for deletable data (more detailed description below).
 * Copyright (C) 2023  Allan DeBoe
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see https://www.gnu.org/licenses/.
 *
 * //////////////////////////////////////////////////////////////////////
 *
 * This interface is used for any model class that is allowed to have the entries of its
 * corresponding database table deleted via REST commands.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */

package com.focust.api.controller.util;

///////////////////////////////////////////////////////////

public interface Deletable {}
