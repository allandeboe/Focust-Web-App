/***********************************************************************************************************************
 * Copyable.java
 *
 * Used for "Model" classes (i.e. classes in com.focust.api.model) to be able to make a deep copy of itself without
 * exposing internal data members to outside classes.
 *
 * @see com.focust.api.model
 * @see <a href="https://refactoring.guru/design-patterns/prototype">Refactoring Guru - Prototype Design Pattern</a>
 *
 * Used instead of "java.lang.Cloneable" as the method "clone()" from "Cloneable" is protected, which makes it painful
 * to use with how Focust is structured in the back-end.
 *
 * @see java.lang.Cloneable
 *
 * @author Allan DeBoe
 */
package com.focust.api.util;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE

public interface Copyable {

    public <M> M copy() throws CloneNotSupportedException;

}
