/**
 * UserRepository
 *
 * This interface is used to allow the interaction between
 * The Java code and the database, at least when it comes
 * to the data about users.
 *
 * Usually, these would be empty and thus only contain
 * the basic functionality given to any JPA Repository,
 * but one can add abstract methods that map to SQL
 * queries for certain situations where using SQL
 * would be less of a headache to use.
 *
 * This will also be used for the REST controllers,
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 5th, 2023
 */
package com.focust.api.model.repository;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.model.data.User;

/** Spring Framework **/
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

///////////////////////////////////////////////////////////

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // Used when checking if a user already exists.
    @Query("SELECT u FROM User u WHERE u.username = :username")
    User getByUsername(@Param("username") String username);

}
