package de.fhws.fiw.fds.exam03.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryRelationStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.exam03.server.api.models.Module;
import de.fhws.fiw.fds.exam03.server.database.ModuleDao;
import de.fhws.fiw.fds.exam03.server.database.UniversityModuleDao;

import java.util.List;
import java.util.stream.Collectors;

public class UniversityModuleStorage extends AbstractInMemoryRelationStorage<Module> implements UniversityModuleDao {
    final private ModuleDao moduleStorage;

    public UniversityModuleStorage(ModuleDao moduleStorage) {
        this.moduleStorage = moduleStorage;
    }

    @Override
    protected IDatabaseAccessObject<Module> getSecondaryStorage() {
        return this.moduleStorage;
    }

    @Override
    public CollectionModelResult<Module> readByModuleName(long primaryId, String moduleName, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> moduleName.isEmpty() || p.getName().equals(moduleName)),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public List<Module> readByUniversityId(long universityId) {
        return this.readAllLinkedByPredicate(universityId, (p) -> true).getResult().stream().toList();
    }

    @Override
    public CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter) {
        return InMemoryPaging.page(
                this.readAllLinkedByPredicate(primaryId, (p) -> true),
                searchParameter.getOffset(), searchParameter.getSize()
        );
    }

    @Override
    public void resetDatabase() {

    }

    @Override
    public void initializeDatabase() {

    }
}
