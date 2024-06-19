package de.fhws.fiw.fds.exam03.server.api.service.universities;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModels.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.*;

public class TestCreateUniversityIT {

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

    @Test void test_create_university_but_outBoundStudents_is_less_than_zero() throws IOException {
        client.start();

        var university = getUniversityClientModel();
        university.setOutboundStudents(-1);

        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(500, client.getLastStatusCode());
    }

    @Test void test_create_university_but_inBoundStudents_is_less_than_zero() throws IOException {
        client.start();

        var university = getUniversityClientModel();
        university.setInboundStudents(-1);

        assertTrue(client.isCreateUniversityAllowed());
        client.createUniversity(university);
        assertEquals(500, client.getLastStatusCode());
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
