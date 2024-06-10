package de.fhws.fiw.fds.exam03.server.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.exam03.server.api.models.Module;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;

public class QueryByModuleName<R> extends AbstractRelationQuery<R, Module> {

    private String moduleName;

    private int waitingTime;

    public QueryByModuleName(long primaryId, String moduleName, int offset, int size) {
        super(primaryId);
        this.moduleName = moduleName;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public int getWaitingTime() {
        return waitingTime;
    }

    public void setWaitingTime(int waitingTime) {
        this.waitingTime = waitingTime;
    }

    @Override
    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getUniversityModuleDao().readByModuleName(this.primaryId, this.moduleName, searchParameter);
    }
}
