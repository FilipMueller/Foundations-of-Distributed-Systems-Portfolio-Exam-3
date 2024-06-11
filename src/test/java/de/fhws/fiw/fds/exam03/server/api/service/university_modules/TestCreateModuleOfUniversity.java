package de.fhws.fiw.fds.exam03.server.api.service.university_modules;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getModuleClientModel;
import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.*;

public class TestCreateModuleOfUniversity {

    private DemoRestClient client;
    private final String UNIVERSITY_NAME = "Test University";
    private final String MODULE_NAME = "Test Module";

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test void test_create_module_of_university() throws IOException {
        client.start();
        var university = getUniversityClientModel();
        university.setName(UNIVERSITY_NAME);
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        client.getAllModules(university.getId());
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());

        var module = getModuleClientModel();
        module.setName(MODULE_NAME);
        client.createModule(university.getId(), module);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules(university.getId());
        assertEquals(200, client.getLastStatusCode());

        var createdModule = client.moduleData().getFirst();
        assertEquals(MODULE_NAME, createdModule.getName());
    }

    @Test void test_create_5_modules_and_get_all() throws IOException {

        client.start();
        var university = getUniversityClientModel();
        university.setName(UNIVERSITY_NAME);
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        client.getAllModules(university.getId());
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed());

        for( int i=0; i<5; i++ ) {
            client.start();

            var module = getModuleClientModel();
            client.createModule(university.getId(), module);
            assertEquals(201, client.getLastStatusCode());
        }

        assertTrue(client.isGetAllModulesAllowed());
        client.getAllModules(university.getId());
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.moduleData().size());
    }
}
