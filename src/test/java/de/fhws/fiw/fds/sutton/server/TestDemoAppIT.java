package de.fhws.fiw.fds.sutton.server;


import de.fhws.fiw.fds.exam03.client.models.UniversityClientModel;
import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.jetbrains.annotations.NotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoAppIT {
    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException{
       this.client = new DemoRestClient();
       this.client.resetDatabase();
    }

    @Test
    public void test_dispatcher_is_available() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    public void test_dispatcher_is_get_all_universities_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());
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

    @Test void test_create_university_and_get_new_university() throws IOException
    {
        client.start();

        var university = getUniversityClientModel();

        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSingleUniversityAllowed() );

        client.getSingleUniversity();
        assertEquals(200, client.getLastStatusCode());

        var universityFromServer = client.universityData().getFirst();
        assertEquals( "India", universityFromServer.getCountry() );
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

        /* Now we call the dispatcher to get the URL to get all persons */
        client.start();
        assertTrue( client.isGetAllUniversitiesAllowed() );

        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.universityData().size());

    }

    @Test void test_create_university_and_delete_university() throws IOException
    {
        client.start();

        var university = getUniversityClientModel();

        client.createUniversity(university);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isDeleteUniversityAllowed() );

        client.deleteSingleUniversity();
        assertEquals(204, client.getLastStatusCode());
    }

    @Test void test_create_5_universities_and_delete_1() throws IOException
    {
        for( int i=0; i<5; i++ ) {
            client.start();

            var university = getUniversityClientModel();

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }


        assertTrue(client.isDeleteUniversityAllowed());

        client.deleteSingleUniversity();
        assertEquals(204, client.getLastStatusCode());
    }

    private static @NotNull UniversityClientModel getUniversityClientModel()
    {
        var university = new UniversityClientModel();
        university.setName("Christ University");
        university.setCountry("India");
        university.setDepartmentName("Department of Information Technology");
        university.setDepartmentUrl("https://christuniversity.in/information-technology");
        university.setContactPerson("Dr. Ravi Kunmar");
        university.setOutboundStudents(20);
        university.setInboundStudents(15);
        university.setNextSpringSemesterStart(LocalDate.of( 2024, 10, 15));
        university.setNextAutumnSemesterStart(LocalDate.of( 2024, 4, 14));
        return university;
    }
}
