/**
 * NewUserForm
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
package com.focust.api.dto.form;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Form;
import com.focust.api.model.data.User;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

///////////////////////////////////////////////////////////

@RequiredArgsConstructor
public class NewUserForm implements Form<User> {

    @Getter @NonNull
    private final String username;

    @Getter @NonNull
    private final String password;

    @Getter @NonNull
    private final String email;

    @Override
    public User unload() {
        return new User(this);
    }

}
