package de.fhws.fiw.fds.exam03.client.web;

import de.fhws.fiw.fds.exam03.client.models.ModuleClientModel;
import de.fhws.fiw.fds.sutton.client.web.GenericWebClient;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import java.io.IOException;

public class ModuleWebClient {

    private GenericWebClient<ModuleClientModel> client;

    public ModuleWebClient() {
        this.client = new GenericWebClient<>();
    }

    public ModuleWebResponse getCollectionOfModules(String url) throws IOException {
        return createResponse(this.client.sendGetCollectionRequest(url, ModuleClientModel.class));
    }

    public ModuleWebResponse getSingleModule(String url) throws IOException {
        return createResponse(this.client.sendGetSingleRequest(url, ModuleClientModel.class));
    }

    public ModuleWebResponse postNewModule(String url, ModuleClientModel module) throws IOException {
        return createResponse(this.client.sendPostRequest(url, module));
    }

    public ModuleWebResponse putModule(String url, ModuleClientModel module) throws IOException {
        return createResponse(this.client.sendPutRequest(url, module));
    }

    public ModuleWebResponse deleteModule(String url) throws IOException {
        return createResponse(this.client.sendDeleteRequest(url));
    }

    private ModuleWebResponse createResponse(WebApiResponse<ModuleClientModel> response) {
        return new ModuleWebResponse(response.getResponseData(), response.getResponseHeaders(),
                response.getLastStatusCode());
    }
}
