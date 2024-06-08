package de.fhws.fiw.fds.exam03.server.database.utils;

import de.fhws.fiw.fds.exam03.server.database.DaoFactory;

public class InitializeDatabase {

    public static void initializeDBWithRelations() {
        DaoFactory.getInstance().getUniversityModuleDao().initializeDatabase();
    }
}
