package de.fhws.fiw.fds.exam03.server.api.service.universitiesIT;

import static de.fhws.fiw.fds.exam03.server.api.service.CreateModelsIT.getUniversityClientModel;
import static org.junit.jupiter.api.Assertions.*;

import de.fhws.fiw.fds.exam03.client.rest.DemoRestClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;

public class TestGetAllUniversitiesIT {

    private DemoRestClient client;

    @BeforeEach
    public void setUp() throws IOException {
        this.client = new DemoRestClient();
        this.client.resetDatabase();
    }

    @Test
    public void test_get_all_universities() throws IOException {

        for (int i = 0; i < 5; i++) {
            client.start();

            var university = getUniversityClientModel();

            assertTrue(client.isCreateUniversityAllowed());

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());

        client.getAllUniversities();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.universityData().size());
    }

    @Test
    public void test_get_all_universities_with_query_by_name() throws IOException {
        for (int i = 0; i < 5; i++) {
            client.start();

            var university = getUniversityClientModel();
            university.setName(String.valueOf((char) (65 + i)));

            assertTrue(client.isCreateUniversityAllowed());

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());

        client.getAllUniversities();
        assertTrue(client.isGetAllUniversitiesQueryByNameAllowed());

        client.getAllUniversitiesQueryByName("A");
        assertEquals(200, client.getLastStatusCode());

        assertEquals("A", client.universityData().getFirst().getName());
    }

    @Test
    public void test_get_all_universities_with_query_by_sort() throws IOException {
        for (int i = 0; i < 5; i++) {
            client.start();

            var university = getUniversityClientModel();
            university.setName(String.valueOf((char) (69 - i)));

            assertTrue(client.isCreateUniversityAllowed());

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());

        client.getAllUniversities();
        assertTrue(client.isGetAllUniversitiesQueryBySortAllowed());

        client.getAllUniversitiesQueryBySort("name", "true");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("A", client.universityData().getFirst().getName());

        client.getAllUniversitiesQueryBySort("name", "false");
        assertEquals(200, client.getLastStatusCode());
        assertEquals("E", client.universityData().getFirst().getName());
    }

    @Test
    public void test_get_all_universities_with_paging() throws IOException {
        for (int i = 0; i < 11; i++) {
            client.start();

            var university = getUniversityClientModel();

            assertTrue(client.isCreateUniversityAllowed());

            client.createUniversity(university);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue(client.isGetAllUniversitiesAllowed());

        client.getAllUniversities();
        assertTrue(client.isGetAllUniversitiesWithPagingAllowed());

        client.getAllUniversitiesWithPaging("0", "5");
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.universityData().size());

        client.getAllUniversitiesWithPaging("0", "10");
        assertEquals(200, client.getLastStatusCode());
        assertEquals(10, client.universityData().size());

        assertTrue(client.isGetAllUniversitiesNextPageAllowed());
        client.getAllUniversitiesNextPage();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(1, client.universityData().size());

        assertTrue(client.isGetAllUniversitiesPreviousPageAllowed());
        client.getAllUniversitiesPreviousPage();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(10, client.universityData().size());
    }
}
