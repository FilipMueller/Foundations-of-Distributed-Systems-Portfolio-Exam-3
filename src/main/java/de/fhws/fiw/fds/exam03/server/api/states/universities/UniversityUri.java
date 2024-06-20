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

import de.fhws.fiw.fds.exam03.Start;

public interface UniversityUri {

    String PATH_ELEMENT = "universities";
    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_ID = REL_PATH + "/{id}";

    String QUERY_BY_NAME = "?universityName=";
    String NAME = "name";
    String QUERY_SORT_BY_ATTRIBUTE = "?orderByAttribute=";
    String ATTRIBUTE ="attribute";
    String QUERY_BY_ORDER = "&ascending=";
    String ORDER = "true";
    String REL_PATH_WITH_QUERY_BY_NAME = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + QUERY_BY_NAME + NAME;
    String REL_PATH_WITH_QUERY_BY_SORT = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + QUERY_SORT_BY_ATTRIBUTE + ATTRIBUTE + QUERY_BY_ORDER + ORDER;

    String OFFSET = "?offset=";
    String START = "start";
    String SIZE = "&size=";
    String DIMENSION = "dimension";
    String REL_PATH_WITH_PAGE_AND_OFFSET = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + OFFSET + START + SIZE + DIMENSION;
}
