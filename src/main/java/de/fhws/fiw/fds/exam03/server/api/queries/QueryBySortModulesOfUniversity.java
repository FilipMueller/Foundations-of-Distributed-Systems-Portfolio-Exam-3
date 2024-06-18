package de.fhws.fiw.fds.exam03.server.api.queries;

import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.exam03.server.api.models.Module;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryBySortModulesOfUniversity<R> extends AbstractRelationQuery<R, Module> {
    private String orderByAttribute;
    private boolean ascending;

    public QueryBySortModulesOfUniversity(long primaryId, String orderByAttribute, boolean ascending, int offset, int size) {
        super(primaryId);
        this.ascending = ascending;
        this.orderByAttribute = orderByAttribute;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }

    public String getOrderByAttribute() {
        return orderByAttribute;
    }

    public void setOrderByAttribute(String orderByAttribute) {
        this.orderByAttribute = orderByAttribute;
    }

    public boolean isAscending() {
        return ascending;
    }

    public void setAscending(boolean ascending) {
        this.ascending = ascending;
    }

    @Override
    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getUniversityModuleDao().readAllModulesByAttributeAndOrder(this.primaryId, this.orderByAttribute, this.ascending, searchParameter);
    }
}
