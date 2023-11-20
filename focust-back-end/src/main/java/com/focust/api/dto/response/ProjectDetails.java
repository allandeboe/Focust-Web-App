package com.focust.api.dto.response;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Response;
import com.focust.api.model.data.Project;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;

///////////////////////////////////////////////////////////

public class ProjectDetails implements Response {

    @Getter
    private Long id;

    @Getter
    private String name;

    @Getter
    private String description;

    public ProjectDetails(@NonNull Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
    }

}
