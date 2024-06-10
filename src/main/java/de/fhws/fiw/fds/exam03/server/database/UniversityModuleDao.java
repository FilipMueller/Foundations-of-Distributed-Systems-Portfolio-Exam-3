package de.fhws.fiw.fds.exam03.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.exam03.server.api.models.Module;

import java.util.List;

public interface UniversityModuleDao extends IDatabaseRelationAccessObject<Module> {

    CollectionModelResult<Module> readByModuleName(long primaryId, String cityName, SearchParameter searchParameter);

    List<Module> readByUniversityId(long universityId);

    void initializeDatabase();

    void resetDatabase();

}
