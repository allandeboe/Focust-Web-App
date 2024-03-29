/**
 * Updatable
 *
 * This interface is used for any model class that can be updated using some data transfer object (DTO) sent
 * by the client/front-end.
 *
 * "R" refers to the request data, usually data transfer objects (DTOs) that
 * are used to send information back to the client/front-end.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.controller.util;

///////////////////////////////////////////////////////////

public interface Updatable<R> {

    public void update(R new_data);

}
