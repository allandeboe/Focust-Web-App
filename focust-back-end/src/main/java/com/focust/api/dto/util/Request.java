/**
 * Request
 *
 * This class is used for any data transfer object (DTO) corresponding
 * to the data that the server receives from the client/front-end.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.util;

///////////////////////////////////////////////////////////

public interface Request<T> {

    public T unload();

}
