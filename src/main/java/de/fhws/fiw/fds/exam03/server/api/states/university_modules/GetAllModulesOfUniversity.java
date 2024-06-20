package de.fhws.fiw.fds.exam03.server.api.states.university_modules;

import de.fhws.fiw.fds.exam03.server.api.states.universities.UniversityRelTypes;
import de.fhws.fiw.fds.exam03.server.api.states.universities.UniversityUri;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionRelationState;
import de.fhws.fiw.fds.exam03.server.api.models.Module;
import jakarta.ws.rs.core.Response;

public class GetAllModulesOfUniversity extends AbstractGetCollectionRelationState<Response, Module> {

    public GetAllModulesOfUniversity(ServiceContext serviceContext, long primaryId, AbstractRelationQuery<Response, Module> query) {
        super(serviceContext, primaryId, query);
        this.suttonResponse = new JerseyResponse<>();
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityModuleUri.REL_PATH,
                UniversityModuleRelTypes.CREATE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniversityModuleUri.REL_PATH_ID,
                UniversityModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniversityUri.REL_PATH_ID,
                UniversityRelTypes.GET_SINGLE_UNIVERSITY,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniversityModuleUri.REL_PATH_WITH_PAGE_AND_OFFSET,
                UniversityModuleRelTypes.GET_ALL_MODULES_BY_PAGE_AND_OFFSET,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniversityModuleUri.REL_PATH_WITH_QUERY_BY_NAME,
                UniversityModuleRelTypes.GET_ALL_MODULES_QUERY_BY_NAME,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniversityModuleUri.REL_PATH_WITH_QUERY_BY_SORT,
                UniversityModuleRelTypes.GET_ALL_MODULES_QUERY_BY_SORT,
                getAcceptRequestHeader(),
                this.primaryId);
    }
}
