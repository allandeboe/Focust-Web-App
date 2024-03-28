/**
 * ProjectMemberDetails
 *
 * This class represents the response that is used to
 * mark members of a project.
 *
 * @see com.focust.api.model.data.User
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

public class ProjectMemberDetails implements Response {

    @Getter
    private final Long userId;

    @Getter
    private final String username;

    @Getter
    private final ProjectRole projectRole;

    public ProjectMemberDetails(@NonNull Long userId, @NonNull String username, @NonNull ProjectRole projectRole) {
        this.userId = userId;
        this.username = username;
        this.projectRole = projectRole;
    }

}
