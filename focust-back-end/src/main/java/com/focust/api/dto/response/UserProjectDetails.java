/**
 * ProjectMemberDetails
 *
 * This class represents the response that is used to
 * mark the projects that a given user is a member of.
 *
 * @see com.focust.api.model.data.Project
 * @see com.focust.api.model.relational.ProjectMembership
 *
 * @author Allan DeBoe (allan.m.deboe@gmail.com)
 * @date November 22nd, 2023
 */
package com.focust.api.dto.response;

///////////////////////////////////////////////////////////

/** Focust **/
import com.focust.api.dto.util.Response;
import com.focust.api.enums.ProjectRole;

/** Lombok **/
import lombok.Getter;
import lombok.NonNull;

///////////////////////////////////////////////////////////

public class UserProjectDetails implements Response {

    @Getter
    private Long projectId;

    @Getter
    private String projectName;

    @Getter
    private ProjectRole projectRole;

    public UserProjectDetails(@NonNull Long projectId, @NonNull String projectName, @NonNull ProjectRole projectRole) {
        this.projectId = projectId;
        this.projectName = projectName;
        this.projectRole = projectRole;
    }

}
