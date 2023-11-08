/**
 * SecurityConfiguration
 *
 * This class contains methods that relate to security, from
 * what kind of hash will be used for passwords and such.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.security;

///////////////////////////////////////////////////////////

/** Spring Framework **/
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

///////////////////////////////////////////////////////////

public class SecurityConfiguration {

    public static PasswordEncoder getEncoder() {
        return new BCryptPasswordEncoder();
    }

}
