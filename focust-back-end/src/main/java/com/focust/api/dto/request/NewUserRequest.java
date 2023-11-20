/**
 * NewUserRequest
 *
 * This class is a data transfer object (DTO) that is sent to
 * the back-end server containing details needed to add a new
 * user to the database.
 *
 * @see com.focust.api.model.data.User
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.dto.request;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Request;
import com.focust.api.model.data.User;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

///////////////////////////////////////////////////////////

@RequiredArgsConstructor
public class NewUserRequest implements Request<User> {

    @Getter @NonNull
    private final String username;

    @Getter @NonNull
    private final String password;

    @Getter @NonNull
    private final String email;

    @Override
    public User unload() {
        User user = new User();
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setEmail(this.email);
        return user;
    }

}
