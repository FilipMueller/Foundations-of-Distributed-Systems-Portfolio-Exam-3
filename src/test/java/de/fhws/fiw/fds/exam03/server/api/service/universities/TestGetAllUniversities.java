package de.fhws.fiw.fds.exam03.server.api.service.universities;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestGetAllUniversities {

    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test public void test_get_all_universities() throws IOException {

        for( int i=0; i<5; i++ ) {
            client.start();

            var university = getUniversityClientModel();

            assertTrue(client.isCreateUniversityAllowed());

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue( client.isGetAllUniversitiesAllowed() );

        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.universityData().size());
    }
}
