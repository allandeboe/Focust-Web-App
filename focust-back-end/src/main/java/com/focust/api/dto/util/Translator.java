/**
 * Translator
 *
 * This class is used to convert one object to another via the target class's constructor.
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
public final class Translator<V extends View, R> {

    @NonNull
    private final Class<V> referenceClass;

    public V translate(R r) throws Exception {

        /* I struggled for a while to fix the problem of certain exceptions, like "NoSuchMethodException"
         * and "IllegalArgumentException", that popped up when trying to use ".getDeclaredConstructor().newInstance(r)".
         * However, that is because I didn't know at the time that ".getDeclaredConstructor()" can accept inputs,
         * primarily a Class array, that determines what kind of parameters characterize the Constructor we want, which
         * was the class for R. After fixing that, this function works as intended.
         */
        Class[] parameterType = new Class[1];
        parameterType[0] = r.getClass();

        return this.referenceClass.getDeclaredConstructor(parameterType).newInstance(r);
    }

}
