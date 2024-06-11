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

    @Test void test_create_university() throws IOException
    {
        client.start();

        var university = getUniversityClientModel();

        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_5_universities_and_get_all() throws IOException
    {
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
