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

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.exam03.server.api.models.University;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewUniversity extends AbstractPostState<Response, University> {

    public PostNewUniversity(ServiceContext serviceContext, University modelToStore) {
        super(serviceContext, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getUniversityDao().create(this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityUri.REL_PATH_ID, UniversityRelTypes.GET_SINGLE_UNIVERSITY, getAcceptRequestHeader(), this.modelToStore.getId());
        addLink(UniversityUri.REL_PATH, UniversityRelTypes.GET_ALL_UNIVERSITIES, getAcceptRequestHeader());

    }
}
