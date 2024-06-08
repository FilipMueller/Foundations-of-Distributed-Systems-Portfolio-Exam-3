package de.fhws.fiw.fds.exam03.client.web;

import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.exam03.client.models.UniversityClientModel;

import java.io.IOException;

public class UniversityWebClient {

    private GenericWebClient<UniversityClientModel> client;

    public UniversityWebClient() {
        this.client = new GenericWebClient<>();
    }

    public UniversityWebResponse getDispatcher(String url ) throws IOException
    {
        return createResponse( this.client.sendGetSingleRequest( url ) );
    }


    public UniversityWebResponse getSingleUniversity(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, UniversityClientModel.class));
    }

    public UniversityWebResponse getCollectionOfUniversities(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, UniversityClientModel.class));
    }

    public UniversityWebResponse postNewUniversity(String url, UniversityClientModel person)
            throws IOException {
        return createResponse(this.client.sendPostRequest(url, person));
    }

    public UniversityWebResponse putUniversity(String url, UniversityClientModel person) throws IOException {
        return createResponse(this.client.sendPutRequest(url, person));
    }

    public UniversityWebResponse deleteUniversity(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    public UniversityWebResponse resetDatabaseOnServer(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url + "/resetdatabase"));
    }

    private UniversityWebResponse createResponse(WebApiResponse<UniversityClientModel> response) {
        return new UniversityWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }

}
