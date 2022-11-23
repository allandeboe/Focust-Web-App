/***********************************************************************************************************************
 * Copyable.java
 *
 * @see org.springframework.data.jpa.repository.JpaRepository
 * @see com.focust.api.model.User
 * @see com.focust.api.controller.UserController
 *
 * Used to allow the "UserController" class to interact with the database.
 *
 * @author Allan DeBoe
 */
package com.focust.api.repository;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Focust
 */
import com.focust.api.model.User;

/*
 * Java Spring
 */
import org.springframework.data.jpa.repository.JpaRepository;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// INTERFACE

public interface UserRepository extends JpaRepository<User, Long> {}
