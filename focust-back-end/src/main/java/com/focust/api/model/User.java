/***********************************************************************************************************************
 * User.java
 *
 * This class represents the "Model" corresponding to the table "users" in the Database.
 *
 * This class does NOT have authentication information as data members to avoid the REST API from exposing sensitive
 * data - like user password hashes - to the outside, which would otherwise make life easier for the hacker.
 *
 * @author Allan DeBoe
 */
package com.focust.api.model;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Focust
 */
import com.focust.api.model.dto.SafeUserDTO;
import com.focust.api.util.Copyable;
import com.focust.api.util.Transferable;
import com.focust.api.util.Updatable;

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
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/*
 * Java Persistence API (JPA)
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GenerationType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CLASS

@NoArgsConstructor
@RequiredArgsConstructor
@ToString
@Entity
@Table(name = "users")
public final class User implements Copyable, Updatable<User>, Transferable<SafeUserDTO> {

    /**
     * id: long
     *
     * This represents the "id" value for the user, which is unique for
     * all users and is generated automatically thanks to JPA.
     *
     * @return the id of the user.
     */
    @Getter
    private @Id @GeneratedValue(strategy = GenerationType.AUTO) long id;

    /**
     * username: String
     *
     * This represents the "username" value for the user, which is used
     * to help users of the site to identify each other in a more
     * human-friendly way.
     *
     * @param username the (new) username for the user.
     * @return the username of the user.
     */
    @Getter
    @Setter
    private @NonNull @Column(name = "username") String username;

    /**
     * githubUsername: String
     *
     * This represents the GitHub username of the user, if
     * the have one and decided to link theirs to the site.
     *
     * Since Focust is a Bugtracker, which is a tool used by
     * software developers to keep track of progress on bugs,
     * it makes sense for Focust to allow users to link their
     * Focust account with GitHub, which is a platform commonly
     * used by programmers to post code.
     *
     * @param githubUsername the user's (new) GitHub username.
     * @return the user's username on GitHub.
     */
    @Getter
    @Setter
    private @Column(name = "github") String githubUsername;

    /**
     * email: String
     *
     * This represents the email associated with the user.
     *
     * Since Focust is also used by Project managers, it makes
     * sense to also allow users to link their Focust account with
     * an email.
     *
     * @param email the user's (new) email address.
     * @return the user's email address.
     */
    @Getter
    @Setter
    private @Column(name = "email") String email;

    /**
     * User(username: String, githubUsername: String, email: String)
     *
     * @see com.focust.api.util.Copyable
     *
     * @param username the (new) username for the user.
     * @param githubUsername the (new) GitHub username for the user.
     * @param email the (new) email address for the user.
     */
    public User(@NonNull String username, String githubUsername, String email) {
        this.username = username;
        this.githubUsername = githubUsername;
        this.email = email;
    }

    /**
     * copy(): User
     *
     * @see com.focust.api.util.Copyable
     *
     * @return a (deep) copy of the User instance.
     * @throws CloneNotSupportedException
     */
    @Override
    public User copy() throws CloneNotSupportedException {
        return new User(this.getUsername(), this.getGithubUsername(), this.getEmail());
    }

    /**
     * update(instance: User): void
     *
     * @see Updatable
     *
     * @param instance the User instance that will be used to replace data.
     */
    public void update(User instance) {
        this.setUsername(instance.getUsername());
        this.setGithubUsername(instance.getGithubUsername());
        this.setEmail(instance.getEmail());
    }

    /**
     * createTransferObject(): SafeUserDTO
     *
     * @see com.focust.api.util.Transferable
     * @see com.focust.api.model.dto.SafeUserDTO
     *
     * @return a Data Transfer Object (DTO) for the non-sensitive user data.
     */
    @Override
    public SafeUserDTO createTransferObject() throws Exception {
        return new SafeUserDTO(this.copy());
    }

}
