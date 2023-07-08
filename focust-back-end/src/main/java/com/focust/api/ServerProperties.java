/***********************************************************************************************************************
 * Properties.java
 *
 * This class acts as a module that contains functions that access properties of
 * the Java Spring App  (i.e. this web server) itself.
 *
 * @author Allan DeBoe
 */
package com.focust.api;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Java Standard Library
 */
import java.io.IOException;

/*
 * Java Spring
 */
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CLASS

public final class ServerProperties {

    @Autowired
    private static Environment environment;

    /**
     * isInDevMode(): boolean
     *
     * checks if the server is being run on "dev" (short for "developer") mode.
     *
     * @return whether or not the server is being run on "dev" mode.
     * @throws IOException
     */
    public static boolean isInDevMode() {
        return (environment.getProperty("focust.server-mode").equalsIgnoreCase("dev"));
    }

}
