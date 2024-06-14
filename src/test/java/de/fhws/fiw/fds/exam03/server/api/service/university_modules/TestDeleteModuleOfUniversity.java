package de.fhws.fiw.fds.exam03.server.api.service.university_modules;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getModuleClientModel;
import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDeleteModuleOfUniversity {

    private DemoRestClient client;
    private final String UNIVERSITY_NAME = "Test University";
    private final String MODULE_NAME = "Test Module";

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test void test_delete_module_of_university() throws IOException {
        client.start();
        var university = getUniversityClientModel();
        university.setName(UNIVERSITY_NAME);
        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        client.getAllModules();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateModuleAllowed(), "Creating module is not allowed.");

        var module = getModuleClientModel();
        module.setName(MODULE_NAME);
        client.createModule(module);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();

        assertTrue(client.isDeleteModuleAllowed());
        client.deleteSingleModule();
        assertEquals(204, client.getLastStatusCode());
    }
}
