/**
 * CRUDController
 *
 * This class is made to handle the typical CRUD (create, read, update, delete)
 * functionality found in typical REST controllers. This is used to ensure that
 * all of the controllers that are made for this project have nearly identical
 * implementations of the CRUD functionality, which is great when it comes to
 * maintainability as well as ensuring appropriate responses when CRUD is used.
 *
 * "R" refers to the repository/received data, which is any class that has a
 * corresponding JpaRepository sub-interface made for it.
 * @see com.focust.api.model.data
 *
 * "T" refers to the transfer data, usually data transfer objects (DTOs) that
 * are used to send information back to the client/front-end.
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.controller.util;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Form;
import com.focust.api.dto.util.Translator;
import com.focust.api.dto.util.View;

/** Standard Java **/
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/** Spring Framework **/
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

///////////////////////////////////////////////////////////

public final class CRUDController {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CREATE

    public static <R, V extends View, C extends Form<R>> ResponseEntity<V> create(JpaRepository<R, Long> repository, C newData, Class<V> resultClass) {

        try {
            R newEntry = repository.save(newData.unload());
            Translator<V, R> translator = new Translator<>(resultClass);
            return new ResponseEntity<>(translator.translate(newEntry), HttpStatus.CREATED);
        }
        catch (DataIntegrityViolationException e) {
            return new ResponseEntity<>(null, HttpStatus.OK);
        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // READ

    public static <R, V extends View> ResponseEntity<List<V>> getAll(JpaRepository<R, Long> repository, Class<V> resultClass) {

        try {
            /* add a corresponding DTO (i.e. "T") for every entry (i.e. "R") to ensure that
             * no sensitive/unwanted data gets sent over.
             */
            List<V> entries = new ArrayList<V>();
            Translator<V, R> translator = new Translator<>(resultClass);
            for (R entry: repository.findAll()) {
                entries.add(translator.translate(entry));
            }

            if (entries.isEmpty()) { return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); }
            return new ResponseEntity<>(entries, HttpStatus.OK);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static <R, V extends View> ResponseEntity<V> getById(JpaRepository<R, Long> repository, long id, Class<V> resultClass) {

        try {
            Optional<R> entry = repository.findById(id);

            if (entry.isPresent()) {
                Translator<V, R> translator = new Translator<>(resultClass);
                return new ResponseEntity<>(translator.translate(entry.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // UPDATE

    public static <C, R extends Updatable<C>> ResponseEntity<HttpStatus> updateById(JpaRepository<R, Long> repository, long id, C newData) {

        try {
            Optional<R> entry = repository.findById(id);

            if (entry.isPresent()) {
                R model = entry.get();
                model.update(newData);
                repository.save(model);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Delete

    public static <R extends Deletable> ResponseEntity<HttpStatus> deleteAll(JpaRepository<R, Long> repository) {

        try {
            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static <R extends Deletable> ResponseEntity<HttpStatus> deleteById(JpaRepository<R, Long> repository, long id) {

        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
