/***********************************************************************************************************************
 * Transferable.java
 *
 * This interface exists to allow the Controller class to return objects that won't reveal sensitive data that only
 * the server should know.
 *
 * @author Allan DeBoe
 */
package com.focust.api.util;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE

public interface Transferable<D> {

    public D createTransferObject() throws Exception;

}
