package de.fhws.fiw.fds.exam03.server.api.service.university_modules;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getModuleClientModel;
import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestGetAllModulesOfUniversityIT {

    private DemoRestClient client;
    private final String UNIVERSITY_NAME = "Test University";
    private final String MODULE_NAME = "Test Module";

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test
    void test_get_all_modules_of_university() throws IOException {
        client.start();
        var university = getUniversityClientModel();
        university.setName(UNIVERSITY_NAME);
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());

        for( int i=0; i<5; i++ ) {
            assertTrue(client.isCreateModuleAllowed());
            var module = getModuleClientModel();

            client.createModule(module);
            module.setName(MODULE_NAME);
            assertEquals(201, client.getLastStatusCode());
            assertTrue(client.isGetAllModulesAllowed());
            client.getAllModules();
        }

        client.start();

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    void test_get_all_modules_of_university_with_query_by_name_and_sort() throws IOException {
        client.start();
        var university = getUniversityClientModel();
        university.setName(UNIVERSITY_NAME);
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());

        for( int i=0; i<5; i++ ) {
            assertTrue(client.isCreateModuleAllowed());
            var module = getModuleClientModel();

            module.setName(String.valueOf((char) (69 - i)));
            client.createModule(module);
            module.setName(MODULE_NAME);
            assertEquals(201, client.getLastStatusCode());
            assertTrue(client.isGetAllModulesAllowed());
            client.getAllModules();
        }

        client.start();

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules();

        assertTrue(client.isGetAllModulesQueryByNameAllowed());
        client.getAllModulesQueryByName("A");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("A", client.moduleData().getFirst().getName());

        assertTrue(client.isGetAllModulesQueryBySortAllowed());
        client.getAllModulesQueryBySort("name", "true");

        assertEquals(200, client.getLastStatusCode());
        assertEquals("A", client.moduleData().getFirst().getName());

        assertTrue(client.isGetAllModulesQueryBySortAllowed());

        client.getAllModulesQueryBySort("name", "false");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("E", client.moduleData().getFirst().getName());
    }
}
