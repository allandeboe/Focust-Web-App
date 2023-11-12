/**
 * UserDetails
 *
 * This class is a data transfer object (DTO) used to send basic
 * information about the user without revealing sensitive user
 * information (like password hashes).
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.view;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.View;
import com.focust.api.model.data.User;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;

///////////////////////////////////////////////////////////

public class BasicUserDetails implements View {

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
