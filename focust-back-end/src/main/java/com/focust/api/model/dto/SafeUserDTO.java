/***********************************************************************************************************************
 * SafeUserDTO.java
 *
 * @see com.focust.api.model.User
 *
 * This class is a Data Transfer Object (DTO) that is meant to only transfer non-sensitive user data back to the
 * client. This is done to prevent a threat agent (i.e. a hacker) from easily having access to the password hash.
 *
 * While it seems at first that having just a password has wouldn't be a bad idea, we want to make sure that a data
 * breach isn't easily done by some script kiddie, hence the existence of these kinds of DTOs.
 *
 * @author Allan DeBoe
 */
package com.focust.api.model.dto;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Focust
 */
import com.focust.api.model.User;

/*
 * Lombok
 *
 * Used to reduce boilerplate code and thus make it more readable and also makes it easy to create
 * these classes faster, since I am making this project on a time restriction to make it portfolio-ready.
 *
 * Despite this, it does have security vulnerabilities. For example, the code that gets generated
 * for a getter method via the @Getter annotation in Lombok is vulnerable to external modification
 * as it returns the data member itself (i.e. the normal Java way of doing getter methods) rather
 * than a defensive copy to ensure encapsulation of mutable data.
 *
 * @see lombok.Getter
 * @see lombok.javac.handlers.HandleGetter
 * @see SEI CERT - OBJ05-J. Do not return references to private mutable class members
 *
 * Hopefully this gets fixed in the future, either by the people who created and/or are working on
 * Lombok or by myself if I ever decided to deal with the hell that is creating a custom Lombok
 * annotation (looking at the source code of Lombok makes my brain melt).
 */
import lombok.Getter;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CLASS

public final class SafeUserDTO {

    private final @Getter Long userId;
    private final @Getter String username;
    private final @Getter String githubUsername;
    private final @Getter String email;

    public SafeUserDTO(User user) {
        this.userId = user.getId();
        this.username = user.getUsername();
        this.githubUsername = user.getGithubUsername();
        this.email = user.getEmail();
    }

}
