/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package de.fhws.fiw.fds.exam03.server.api.states.universities;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.exam03.server.api.models.University;
import jakarta.ws.rs.core.Response;

public class GetAllUniversities extends AbstractGetCollectionState<Response, University> {

    public GetAllUniversities(ServiceContext serviceContext, AbstractQuery<Response, University> query) {
        super(serviceContext, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityUri.REL_PATH, UniversityRelTypes.CREATE_UNIVERSITY, getAcceptRequestHeader());
        addLink(UniversityUri.REL_PATH_ID, UniversityRelTypes.GET_SINGLE_UNIVERSITY, getAcceptRequestHeader());
        addLink(UniversityUri.REL_PATH_WITH_PAGE_AND_OFFSET, UniversityRelTypes.GET_ALL_UNIVERSITIES_BY_PAGE_AND_OFFSET, getAcceptRequestHeader());
        addLink(UniversityUri.REL_PATH_WITH_QUERY_BY_NAME, UniversityRelTypes.GET_ALL_UNIVERSITIES_QUERY_BY_NAME, getAcceptRequestHeader());
        addLink(UniversityUri.REL_PATH_WITH_QUERY_BY_SORT, UniversityRelTypes.GET_ALL_UNIVERSITIES_QUERY_BY_SORT, getAcceptRequestHeader());
    }
}
