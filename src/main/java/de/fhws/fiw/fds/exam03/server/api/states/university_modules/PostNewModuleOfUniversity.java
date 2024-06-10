package de.fhws.fiw.fds.exam03.server.api.states.university_modules;

import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.responseAdapter.JerseyResponse;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.post.AbstractPostRelationState;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.exam03.server.api.models.Module;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class PostNewModuleOfUniversity extends AbstractPostRelationState<Response, Module> {

    public PostNewModuleOfUniversity(ServiceContext serviceContext, long primaryId, Module modelToStore) {
        super(serviceContext, primaryId, modelToStore);
        this.suttonResponse = new JerseyResponse<>();
    }


    @Override
    protected NoContentResult saveModel() {
        return DaoFactory.getInstance().getUniversityModuleDao().create(this.primaryId, this.modelToStore);
    }

    @Override
    protected void defineTransitionLinks() {
        addLink(UniversityModuleUri.REL_PATH,
                UniversityModuleRelTypes.GET_ALL_LINKED_MODULES,
                getAcceptRequestHeader(),
                this.primaryId);

        addLink(UniversityModuleUri.REL_PATH_ID,
                UniversityModuleRelTypes.GET_SINGLE_MODULE,
                getAcceptRequestHeader(),
                this.primaryId,
                this.modelToStore.getId());
    }
}
