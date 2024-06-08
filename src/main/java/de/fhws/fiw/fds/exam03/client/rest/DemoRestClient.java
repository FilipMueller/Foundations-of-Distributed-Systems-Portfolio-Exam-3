package de.fhws.fiw.fds.exam03.client.rest;


import de.fhws.fiw.fds.exam03.client.web.ModuleWebClient;
import de.fhws.fiw.fds.sutton.client.rest2.AbstractRestClient;
import de.fhws.fiw.fds.exam03.client.models.ModuleClientModel;
import de.fhws.fiw.fds.exam03.client.models.UniversityClientModel;
import de.fhws.fiw.fds.exam03.client.web.UniversityWebClient;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class DemoRestClient extends AbstractRestClient {
    private static final String BASE_URL = "http://localhost:8080/demo/api";
    private static final String GET_ALL_UNIVERSITIES = "getAllUniversities";
    private static final String CREATE_UNIVERSITY = "createUniversity";
    private static final String CREATE_MODULE = "createModuleOfUniversity";
    private static final String GET_MODULES = "getAllModulesOfUniversity";


    private List<UniversityClientModel> currentUniversityData;
    private int cursorUniversityData = 0;

    private List<ModuleClientModel> currentModuleData;
    private int cursorModuleData = 0;

    final private UniversityWebClient client;
    final private ModuleWebClient webClient;

    public DemoRestClient() {
        super();
        this.webClient = new ModuleWebClient();
        this.client = new UniversityWebClient();
        this.currentUniversityData = Collections.EMPTY_LIST;
    }

    public void resetDatabase() throws IOException {
        processResponse(this.client.resetDatabaseOnServer(BASE_URL), (response) -> {
        });
    }

    public void start() throws IOException {

        processResponse(this.client.getDispatcher(BASE_URL), (response) -> {
        });
    }

    public boolean isCreateUniversityAllowed() {
        return isLinkAvailable(CREATE_UNIVERSITY);
    }

    public void createUniversity(UniversityClientModel university) throws IOException {
        if (isCreateUniversityAllowed()) {
            processResponse(this.client.postNewUniversity(getUrl(CREATE_UNIVERSITY), university), (response) -> {
                this.currentUniversityData = Collections.EMPTY_LIST;
                this.cursorUniversityData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetAllUniversitiesAllowed() {
        return isLinkAvailable(GET_ALL_UNIVERSITIES);
    }

    public void getAllUniversities() throws IOException {
        if (isGetAllUniversitiesAllowed()) {
            processResponse(this.client.getCollectionOfUniversities(getUrl(GET_ALL_UNIVERSITIES)), (response) -> {
                this.currentUniversityData = new LinkedList(response.getResponseData());
                this.cursorUniversityData = 0;
            });
        } else {
            throw new IllegalStateException();
        }
    }

    public boolean isGetSingleUniversityAllowed() {
        return !this.currentUniversityData.isEmpty() || isLocationHeaderAvailable();
    }

    public List<UniversityClientModel> universityData() {
        if (this.currentUniversityData.isEmpty()) {
            throw new IllegalStateException();
        }

        return this.currentUniversityData;
    }

    public void setUniversityCursor(int index) {
        if (0 <= index && index < this.currentUniversityData.size()) {
            this.cursorUniversityData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void getSingleUniversity() throws IOException {
        if ( isLocationHeaderAvailable()) {
            getSingleUniversity(getLocationHeaderURL());
        }
        else if (!this.currentUniversityData.isEmpty()) {
            getSingleUniversity(this.cursorUniversityData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    public void getSingleUniversity(int index) throws IOException {
        getSingleUniversity(this.currentUniversityData.get(index).getSelfLink().getUrl());
    }

    private void getSingleUniversity(String url) throws IOException {
        processResponse(this.client.getSingleUniversity(url), (response) -> {
            this.currentUniversityData = new LinkedList(response.getResponseData());
            this.cursorUniversityData = 0;
        });
    }

    public boolean isDeleteUniversityAllowed() {
        return !this.currentUniversityData.isEmpty() || isLocationHeaderAvailable();
    }

    public void deleteSingleUniversity() throws IOException {
        if ( isLocationHeaderAvailable()) {
            deleteSingleUniversity(getLocationHeaderURL());
        }
        else if (!this.currentUniversityData.isEmpty()) {
            deleteSingleUniversity(this.cursorUniversityData);
        }
        else {
            throw new IllegalStateException();
        }
    }

    public void deleteSingleUniversity(int index) throws IOException {
        deleteSingleUniversity(this.currentUniversityData.get(index).getSelfLink().getUrl());
    }

    private void deleteSingleUniversity(String url) throws IOException {
        processResponse(this.client.deleteUniversity(url), (response) -> {
        });
    }

    public boolean isUpdateUniversityAllowed() {
        return !this.currentUniversityData.isEmpty() || isLocationHeaderAvailable();
    }




    public boolean isCreateModuleAllowed() {
        return isLinkAvailable(CREATE_MODULE);
    }

    public void createModule(long universityId, ModuleClientModel module) throws IOException {
        String createModuleUrl = BASE_URL + "/" + universityId + "/" + CREATE_MODULE;
        processResponse(this.webClient.postNewModule(createModuleUrl, module), (response) -> {
            this.currentModuleData = Collections.emptyList();
            this.cursorModuleData = 0;
        });
    }

    public boolean isGetModulesAllowed() {
        return isLinkAvailable(GET_MODULES);
    }

    public void getModules(long universityId) throws IOException {
        String getModulesUrl = BASE_URL + "/" + universityId + "/" + GET_MODULES;
        processResponse(this.webClient.getCollectionOfModules(getModulesUrl), (response) -> {
            this.currentModuleData = new LinkedList<>(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }

    public List<ModuleClientModel> moduleData() {
        if (this.currentModuleData.isEmpty()) {
            throw new IllegalStateException();
        }

        return this.currentModuleData;
    }

    public void setModuleCursor(int index) {
        if (0 <= index && index < this.currentModuleData.size()) {
            this.cursorModuleData = index;
        } else {
            throw new IllegalArgumentException();
        }
    }

}