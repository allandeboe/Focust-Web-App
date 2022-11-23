/***********************************************************************************************************************
 * Updateable.java
 *
 * @see com.focust.api.model
 *
 * Used by "Model" classes to make it easy to update internal data members in a generalized way that allows
 * code to be DRY.
 *
 * @author Allan DeBoe
 */
package com.focust.api.util;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE

public interface Updatable<M> {

    public void update(M instance);

}
