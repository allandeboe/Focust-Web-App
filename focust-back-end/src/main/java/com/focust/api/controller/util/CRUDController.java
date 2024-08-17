/**
 * CRUDController
 *
 * This class is made to handle the typical CRUD (create, read, update, delete)
 * functionality found in typical REST controllers. This is used to ensure that
 * all of the controllers that are made for this project have nearly identical
 * implementations of the CRUD functionality, which is great when it comes to
 * maintainability as well as ensuring appropriate responses when CRUD is used.
 *
 * "T" refers to the table/repository data, which is any class that has a
 * corresponding JpaRepository sub-interface made for it.
 * @see com.focust.api.model.data
 *
 * "R" refers to the response data, usually data transfer objects (DTOs) that
 * are used to send information back to the client/front-end.
 *
 * "F" refers to the request/form data, usually data transfer objects (DTOs)
 * that the back-end server receives from the client/front-end
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 6th, 2023
 */
package com.focust.api.controller.util;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Request;
import com.focust.api.dto.util.Response;
import com.focust.api.dto.util.ResponseCreator;

/** Spring Framework **/
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/** Standard Java **/
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

///////////////////////////////////////////////////////////

public final class CRUDController {

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // CREATE

    public static <T, R extends Response, F extends Request<T>> ResponseEntity<R> create(JpaRepository<T, Long> repository, F newData, Class<R> resultClass) {

        try {
            T newEntry = repository.save(newData.unload());
            ResponseCreator<R, T> translator = new ResponseCreator<>(resultClass);
            return new ResponseEntity<>(translator.createResponse(newEntry), HttpStatus.CREATED);
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

    /* Used when there is a bunch of data and thus pagination is needed */
    public static <T, R extends Response> ResponseEntity<List<R>> getAll(JpaRepository<T, Long> repository, Class<R> resultClass, int pageNumber, int entriesPerPage) {

        try {

            if (pageNumber < 0 || entriesPerPage < 1) return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

            /* This should allow pagination, as the number of potential items might be very, very large
             * and thus sending the entire list would be rather impractical and may too much time.
             * (Yay for the fact that JpaRepository extends PagingAndSortingRepository.)
             */
            Pageable firstPage = PageRequest.of(pageNumber, entriesPerPage);
            Page<T> allEntries = repository.findAll(firstPage);
            if (allEntries.isEmpty()) { return new ResponseEntity<>(null, HttpStatus.NO_CONTENT); }

            /* add a corresponding DTO (i.e. "T") for every entry (i.e. "R") to ensure that
             * no sensitive/unwanted data gets sent over.
             */
            List<R> entries = new ArrayList<>();
            ResponseCreator<R, T> translator = new ResponseCreator<>(resultClass);
            for (T entry: allEntries) {
                entries.add(translator.createResponse(entry));
            }

            return new ResponseEntity<>(entries, HttpStatus.OK);

        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static <T, R extends Response> ResponseEntity<R> getById(JpaRepository<T, Long> repository, long id, Class<R> resultClass) {

        try {
            Optional<T> entry = repository.findById(id);

            if (entry.isPresent()) {
                ResponseCreator<R, T> translator = new ResponseCreator<>(resultClass);
                return new ResponseEntity<>(translator.createResponse(entry.get()), HttpStatus.OK);
            }
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // UPDATE

    public static <F, T extends Updatable<F>> ResponseEntity<HttpStatus> updateById(JpaRepository<T, Long> repository, long id, F newData) {

        try {
            Optional<T> entry = repository.findById(id);

            if (entry.isPresent()) {
                T model = entry.get();
                model.update(newData);
                repository.save(model);
                return new ResponseEntity<>(HttpStatus.OK);
            }

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////////////
    // Delete

    public static <T extends Deletable> ResponseEntity<HttpStatus> deleteAll(JpaRepository<T, Long> repository) {

        try {

            repository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    public static <T extends Deletable> ResponseEntity<HttpStatus> deleteById(JpaRepository<T, Long> repository, long id) {

        try {
            repository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            System.out.println();
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
