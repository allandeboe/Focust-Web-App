/**
 * NewProjectRequest
 *
 * This class is a data transfer object (DTO) that is sent to
 * the back-end server containing details needed to add a new
 * project to the database.
 *
 * @see com.focust.api.model.data.Project
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 15th, 2023
 */
package com.focust.api.dto.request;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Request;
import com.focust.api.model.data.Project;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

///////////////////////////////////////////////////////////

@RequiredArgsConstructor
public class NewProjectRequest implements Request<Project> {

    @Getter @NonNull
    private final String name;

    @Getter @NonNull
    private final String description;

    @Getter @NonNull
    private final Long creatorId;

    @Override
    public Project unload() {
        Project project = new Project();
        project.setName(this.name);
        project.setDescription(this.description);
        return project;
    }

}
