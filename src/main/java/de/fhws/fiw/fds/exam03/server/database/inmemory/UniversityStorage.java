package de.fhws.fiw.fds.exam03.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.exam03.server.api.models.University;
import de.fhws.fiw.fds.exam03.server.database.UniversityDao;

import java.util.Comparator;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UniversityStorage extends AbstractInMemoryStorage<University> implements UniversityDao {
    @Override
    public CollectionModelResult<University> readByUniversityName(String universityName, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(
                byUniversityName(universityName),
                searchParameter
        ), searchParameter.getOffset(), searchParameter.getSize());
    }

    @Override
    public CollectionModelResult<University> readUniversitiesByAttributeAndOrder(String orderByAttribute, boolean ascending, SearchParameter searchParameter) {
        Comparator<University> comparator;
        switch (orderByAttribute.toLowerCase()) {
            case "name":
                comparator = Comparator.comparing(University::getName, String.CASE_INSENSITIVE_ORDER);
                break;
            case "country":
                comparator = Comparator.comparing(University::getCountry, String.CASE_INSENSITIVE_ORDER);
                break;
            default:
                throw new IllegalArgumentException("Unsupported orderByAttribute: " + orderByAttribute);
        }

        if (!ascending) {
            comparator = comparator.reversed();
        }

        List<University> sortedList = this.storage.values().stream()
                .sorted(comparator)
                .collect(Collectors.toList());

        return InMemoryPaging.page(new CollectionModelResult<>(sortedList), searchParameter.getOffset(), searchParameter.getSize());
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<University> byUniversityName(String universityName) {
        return u -> universityName.isEmpty() || u.getName().contains(universityName);
    }
}
