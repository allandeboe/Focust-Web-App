/**
 * User
 *
 * This is the class that corresponds to the "users" table
 * in the MySQL database.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 5th, 2023
 */
package com.focust.api.model.data;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.form.NewUserForm;
import com.focust.api.security.SecurityConfiguration;

/** JPA / Hibernate **/
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

/** Lombok **/
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.AccessLevel;

///////////////////////////////////////////////////////////

@NoArgsConstructor
@Entity @Table(name="users")
public class User {

    @Getter @Setter(AccessLevel.PROTECTED)
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private @Column(name="user_id") Long id;

    @Getter @Setter
    private @Column(length=24, nullable=false, unique=true) String username;

    @Getter @Setter
    private @Column(name="password", nullable=false) String passwordHash;

    @Getter @Setter
    private @Column(length=255, unique=true) String email;

    public User(NewUserForm formData) {
        this.username = formData.getUsername();
        this.passwordHash = SecurityConfiguration.getEncoder().encode(formData.getPassword());
        this.email = formData.getEmail();
    }

    /* The github username is a column for the "users" table as the target
     * audience for an issue tracker (i.e. this project) are software engineers
     * & software developers - all of who would most likely have a GitHub
     * account.
     *
     * This would also come in handy if we want to verify users using their
     * GitHub account.
     */
    @Getter @Setter
    private @Column(name="github", unique=true) String githubUsername;

}
