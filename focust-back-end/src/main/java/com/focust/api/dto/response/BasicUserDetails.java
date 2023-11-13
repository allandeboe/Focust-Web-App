/**
 * BasicUserDetails
 *
 * This class is one of the few classes that is used to
 * return user data back to the client/front-end.
 *
 * Since sending sensitive information, like password hashes,
 * back to the client is a recipe for a data breach, this
 * Response DTO only returns non-sensitive user information
 * about the user.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.response;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Response;
import com.focust.api.model.data.User;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;

///////////////////////////////////////////////////////////

public class BasicUserDetails implements Response {

    @Getter
    private Long id;

    @Getter
    private String username;

    @Getter
    private String email;

    @Getter
    private String github;

    public BasicUserDetails(@NonNull User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.github = user.getGithubUsername();
    }

}
