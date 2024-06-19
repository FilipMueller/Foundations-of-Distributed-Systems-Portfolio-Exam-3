package de.fhws.fiw.fds.exam03.server.api.states.university_modules;

import de.fhws.fiw.fds.exam03.Start;

public interface UniversityModuleUri {

    String SHOW_ALL_PARAMETER = "showAll";
    String PATH_ELEMENT = "universities/{id}/modules";

    String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
    String REL_PATH_SHOW_ALL = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=true";
    String REL_PATH_SHOW_ONLY_LINKED = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + "?" + SHOW_ALL_PARAMETER + "=false";
    String REL_PATH_ID = REL_PATH + "/{id}";

    String QUERY_BY_NAME = "?moduleName=";
    String NAME = "name";
    String QUERY_SORT_BY_ATTRIBUTE = "?orderByAttribute=";
    String ATTRIBUTE ="attribute";
    String QUERY_BY_ORDER = "&ascending=";
    String ORDER = "true";
    String REL_PATH_WITH_QUERY_BY_NAME = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + QUERY_BY_NAME + NAME;
    String REL_PATH_WITH_QUERY_BY_SORT = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT + QUERY_SORT_BY_ATTRIBUTE + ATTRIBUTE + QUERY_BY_ORDER + ORDER;
}
