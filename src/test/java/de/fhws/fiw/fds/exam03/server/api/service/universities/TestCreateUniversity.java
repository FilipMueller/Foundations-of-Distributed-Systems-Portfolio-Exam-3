package de.fhws.fiw.fds.exam03.server.api.service.universities;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestCreateUniversity {

    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException{
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test
    public void test_create_university_is_create_university_allowed() throws IOException {
        client.start();
        assertTrue(client.isCreateUniversityAllowed());
    }

    @Test void test_create_university() throws IOException
    {
        client.start();

        var university = getUniversityClientModel();

        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
    }
}
