package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.University;
import de.fhws.fiw.fds.suttondemo.server.database.UniversityDao;

import java.util.function.Predicate;

public class UniversityStorage extends AbstractInMemoryStorage<University> implements UniversityDao {
    @Override
    public CollectionModelResult<University> readByFirstNameAndLastName(String firstName, String lastName, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(
                byFirstAndLastName(firstName, lastName),
                searchParameter
        ), searchParameter.getOffset(), searchParameter.getSize());
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<University> byFirstAndLastName(String firstName, String lastName) {
        return p -> (firstName.isEmpty() || p.getName().equals(firstName) ) && ( lastName.isEmpty() || p.getCountry().equals(lastName));
    }

}