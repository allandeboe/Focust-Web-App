/**
 *  UserProjectList.tsx -- Handles the list of projects a user is a part of (see below for more details)
 *  Copyright (C) 2023  Allan DeBoe
 * 
 *  This program is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.
 * 
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 * 
 *  You should have received a copy of the GNU General Public License
 *  along with this program.  If not, see https://www.gnu.org/licenses/
 * 
 *  //////////////////////////////////////////////////////////////////////
 * 
 *  This handles the section of the user page that lists
 *  off the projects that a given user is a part of.
 * 
 *  @author Allan DeBoe <allan.m.deboe@gmail.com>
 *  @date   November 24th, 2023
 */

import { Component } from "react";
import { useParams } from "react-router-dom";

///////////////////////////////////////

// Contains the projects that the user
// is a part of.
interface UserProjectDetails {
    projectId: number;
    projectName: string;
    projectRole: string;
}

///////////////////////////////////////

function Loading() {
    return (
        <p className="italic">Loading...</p>
    );
}

function ProjectRetrievalErrorDisplay() {
    return (
        <p className="italic">Unable to get project data.</p>
    );
}

function NoProjectsDisplay() {
    return (
        <p className="italic">The user isn't a member of any projects.</p>
    );
}

///////////////////////////////////////

function ProjectRoleDisplay({ projectRole }: { projectRole: String }) {
    if (projectRole === "PROJECT_OWNER") return <td>Owner</td>;
    if (projectRole === "DEVELOPER") return <td>Developer</td>;

    return <td className="italic">N/A</td>;
}

function BasicProjectDisplay(project: UserProjectDetails) {
    return (
        <tr key={project.projectId}>
            <td>{project.projectName}</td>
            <ProjectRoleDisplay projectRole={project.projectRole} />
        </tr>
    );
}

///////////////////////////////////////

// This is to allow the class to extract the
// Route parameter "userId", which is needed to
// properly display the page.
export default function UserProjectList(props: any) {
    const params = useParams();
    return <UserProjectListClass {...props} params={params} />;
}

///////////////////////////////////////

class UserProjectListClass extends Component {

    projects_set: boolean;
    proxy_failed: boolean;

    constructor(props: any) {
        super(props);
        this.projects_set = false;
        this.proxy_failed = false;
        this.state = {
            projects: []
        };
    }

    fetchData(id: number) {
        fetch(`/users/${id}/projects`, {
            method: 'GET',
            headers: {
                'Accept': 'application/json',
                'Content-Type': 'application/json'
            }
        })
        .then((response) => {
            if (response.status >= 300) { 
                this.proxy_failed = true;
                return []; 
            }
            if (response.status === 204) {
                return [];
            }
            return response.json();
        })
        .then((data) => {
            if (!this.proxy_failed) {
                this.projects_set = true;
                this.setState({projects: data});
            }
        });
    }

    componentDidMount() {
        const { params }: any = this.props;
        this.fetchData(parseInt(params.userId));
    }

    render() {
        const {projects, isLoading}: any = this.state;

        if (isLoading) { return (<Loading/>); }
        if (!this.projects_set) { return (<ProjectRetrievalErrorDisplay/>); }
        if (projects.length < 1) { return (<NoProjectsDisplay/>); }

        const projectList = projects.map(BasicProjectDisplay);

        return (
            <table>
                <thead>
                    <th>Project</th>
                    <th>Role</th>
                </thead>
                <tbody>
                    {projectList}
                </tbody>
            </table>
        );
    }

}