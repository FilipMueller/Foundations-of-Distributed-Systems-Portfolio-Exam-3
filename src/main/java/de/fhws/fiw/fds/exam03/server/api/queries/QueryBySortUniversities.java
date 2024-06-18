package de.fhws.fiw.fds.exam03.server.api.queries;

import de.fhws.fiw.fds.exam03.server.api.models.University;
import de.fhws.fiw.fds.exam03.server.database.DaoFactory;
import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;

public class QueryBySortUniversities<R> extends AbstractQuery<R, University> {

    private String orderByAttribute;
    private boolean ascending;

    public QueryBySortUniversities(String orderByAttribute, boolean ascending, int offset, int size) {
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
    protected CollectionModelResult<University> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getUniversityDao().readUniversitiesByAttributeAndOrder(this.orderByAttribute, this.ascending, searchParameter);
    }
}
