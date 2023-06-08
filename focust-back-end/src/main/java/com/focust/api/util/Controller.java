/***********************************************************************************************************************
 * Controller.java
 *
 * @see com.focust.api.controller
 *
 * This class exists to isolate common logic used by Controllers so that the code can be DRY and makes it easier to
 * create new Controllers if necessary.
 *
 * More accurately, this class contains functions that allow Controllers to implement
 * CRUD (Create, Read, Update, & Delete) functionality, which is usually identical across most controllers with a
 * few exceptions.
 *
 * All functions here should be static and use generics.
 *
 * the generic "M" represents any class from the "com.focust.api.model" package!
 * @see com.focust.api.model
 *
 * @author Allan DeBoe
 */
package com.focust.api.util;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// DEPENDENCIES / IMPORTS

/*
 * Java Standard Library
 */
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/*
 * Java Spring
 */
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.focust.api.ServerProperties;

////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
// CLASS

public final class Controller {

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    // "CREATE" FUNCTIONS

    /**
     * create(repository: JpaRepository, newEntry: M)
     *
     * This function is a "CREATE" function that creates a new entry on the databased
     * corresponding to the given "Model" (i.e. generic "M") instance.
     *
     * @param repository the repository (JpaRepository Instance) corresponding to the "Model" class.
     * @param newEntry an instance of the "Model" class to be stored in the database.
     * @param <D> a Data Transfer Object (DTO) to return back to the client-side.
     * @param <M> the "Model" class itself
     * @return a response to the client containing at least a Http Status Code.
     */
    public static <D, M extends Copyable & Transferable<D>> ResponseEntity<D> create(JpaRepository<M, Long> repository, M newEntry) {
        try {
            M new_entry = repository.save(newEntry.copy());
            return new ResponseEntity<>(new_entry.createTransferObject(), HttpStatus.CREATED);
        }
        catch (Exception e) {
            e.printStackTrace(); // for debugging purposes (should only be seen by developers!)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    // "READ" FUNCTIONS

    /**
     * getAll(repository: JpaRepository): ResponseEntity
     *
     * This function is a "READ" function that returns entire list of entries
     * of the table in the database corresponding to the "Model" class
     *
     * @param repository the repository (JpaRepository Instance) corresponding to the "Model" class.
     * @param <D> a Data Transfer Object (DTO) to return back to the client-side.
     * @param <M> the "Model" class itself.
     * @return a response to the client containing at least a Http Status Code.
     */
    public static <D, M extends Transferable<D>> ResponseEntity<List<D>> getAll(JpaRepository<M, Long> repository) {
        try {
            List<D> entries = new ArrayList<D>();

            for (M entry : repository.findAll()) {
                entries.add(entry.createTransferObject());
            }

            if (entries.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace(); // for debugging purposes (should only be seen by developers!)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * getById(repository: JpaRepository, id: long): ResponseEntity
     *
     * This function is a "READ" function that returns the entry from the table in the database
     * corresponding to the "Model" class with the given id.
     *
     * @param repository the repository (JpaRepository Instance) corresponding to the "Model" class.
     * @param id the id of the "Model" entry on its corresponding table in the database.
     * @param <D> a Data Transfer Object (DTO) to return back to the client-side.
     * @param <M> the "Model" class itself.
     * @return a response to the client containing at least a Http Status Code.
     */
    public static <D, M extends Transferable<D>> ResponseEntity<D> getById(JpaRepository<M, Long> repository, long id) {
        try {
            Optional<M> entry_data = repository.findById(id); // Monads!? In my Java!? 😤

            if (entry_data.isPresent()) {
                return new ResponseEntity<>(entry_data.get().createTransferObject(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            e.printStackTrace(); // for debugging purposes (should only be seen by developers!)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    // "UPDATE" FUNCTIONS

    /**
     * update(repository: JpaRepository, id: long, newData: M)
     *
     * This function is a "UPDATE" function that updates the entry in the database corresponding to the "Model" with
     * the given id with new data stored in another "Model" instance (i.e. "M" instance)
     *
     * @param repository the repository (JpaRepository Instance) corresponding to the "Model" class.
     * @param id the id of the "Model" entry to be updated.
     * @param newData the "Model" instance containing new data to update the "Model" entry with.
     * @param <M> the "Model" class itself.
     * @return a response to the client containing at least a Http Status Code.
     */
    public static <D, M extends Updatable<M> & Transferable<D>> ResponseEntity<D> update(JpaRepository<M, Long> repository, long id, M newData) {
        try {
            Optional<M> entry_data = repository.findById(id);

            if (entry_data.isPresent()) {
                M updated_data = entry_data.get();
                updated_data.update(newData);
                return new ResponseEntity<>(repository.save(updated_data).createTransferObject(), HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception e) {
            e.printStackTrace(); // for debugging purposes (should only be seen by developers!)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~//
    // "DELETE" FUNCTIONS

    /**
     * deleteAll(repository: JpaRepository)
     *
     * this function is a "DELETE" function that will delete all of the entries corresponding to
     * the "Model" class from the database. Useful for testing purposes but should not be used
     * in production (as hackers can just wipe the database using this function, which can cause
     * issues).
     *
     * @param repository the repository (JpaRepository Instance) corresponding to the "Model" class.
     * @param <M> the "Model" class itself.
     * @return a response to the client containing a Http Status Code.
     */
    public static <M> ResponseEntity<HttpStatus> deleteAll(JpaRepository<M, Long> repository) {
        try {
            if (ServerProperties.isInDevMode()) {
                repository.deleteAll();
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch (Exception e) {
            e.printStackTrace(); // for debugging purposes (should only be seen by developers!)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * delete(repository: JpaRepository, id: long)
     *
     * this function is a "DELETE" function that will delete an entry - with a given id - corresponding to
     * the "Model" class from the database.
     *
     * Should only be possible when testing/debugging the server using a mock database, and thus
     * should be allowed to be used once deployed and connected to the real database
     * (for security reasons).
     *
     * @param repository the repository (JpaRepository Instance) corresponding to the "Model" class.
     * @param id the id of the "Model" entry to be deleted.
     * @param <M> the "Model" class itself.
     * @return a response to the client containing a Http Status Code.
     */
    public static <M> ResponseEntity<HttpStatus> delete(JpaRepository<M, Long> repository, long id) {
        try {
            if (ServerProperties.isInDevMode()) {
                repository.deleteById(id);
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch (Exception e) {
            e.printStackTrace(); // for debugging purposes (should only be seen by developers!)
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
