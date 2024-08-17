/**
 * ResponseCreator -- Converts table data to data-transfer objects (more detailed description below).
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
 * /////////////////////////////////////////////////////////////////////
 *
 * This class is used to convert one an object, usually table data (T),
 * into a Response object (R).
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.util;

///////////////////////////////////////////////////////////

/** Lombok **/
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

///////////////////////////////////////////////////////////

@RequiredArgsConstructor
public final class ResponseCreator<R extends Response, T> {

    @NonNull
    private final Class<R> referenceClass;

    public R createResponse(T t) throws Exception {

        /* I struggled for a while to fix the problem of certain exceptions, like "NoSuchMethodException"
         * and "IllegalArgumentException", that popped up when trying to use ".getDeclaredConstructor().newInstance(r)".
         * However, that is because I didn't know at the time that ".getDeclaredConstructor()" can accept inputs,
         * primarily a Class array, that determines what kind of parameters characterize the Constructor we want, which
         * was the class for T. After fixing that, this function works as intended.
         */
        Class[] parameterType = new Class[1];
        parameterType[0] = t.getClass();

        return this.referenceClass.getDeclaredConstructor(parameterType).newInstance(t);
    }

}
