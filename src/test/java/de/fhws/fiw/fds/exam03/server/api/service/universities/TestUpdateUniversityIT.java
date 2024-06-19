package de.fhws.fiw.fds.exam03.server.api.service.universities;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestUpdateUniversityIT {

    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test
    void test_update_university() throws IOException
    {
        client.start();

        var university = getUniversityClientModel();

        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleUniversityAllowed());
        client.getSingleUniversity();

        long id = university.getId();

        var updatedUniversity = getUniversityClientModel();
        updatedUniversity.setName("UPDATED NAME");
        updatedUniversity.setId(id);

        assertTrue(client.isUpdateUniversityAllowed());
        client.updateSingleUniversity(updatedUniversity);
        assertEquals(204, client.getLastStatusCode());
    }
}
