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
    private static final String BASE_URL = "http://localhost:8080/exam/api";
    private static final String GET_ALL_UNIVERSITIES = "getAllUniversities";
    private static final String CREATE_UNIVERSITY = "createUniversity";
    private static final String UPDATE_SINGLE_UNIVERSITY = "updateUniversity";
    private static final String DELETE_SINGLE_UNIVERSITY = "deleteUniversity";
    private static final String GET_ALL_LINKED_MODULES = "getAllModulesOfUniversity";
    private static final String CREATE_MODULE = "createModuleOfUniversity";
    private static final String UPDATE_SINGLE_MODULE = "updateModuleOfUniversity";
    private static final String DELETE_MODULE_FROM_UNIVERSITY = "deleteModuleFromUniversity";
    private static final String GET_ALL_UNIVERSITIES_QUERY_BY_NAME = "getAllUniversitiesQueryByName";
    private static final String GET_ALL_UNIVERSITIES_QUERY_BY_SORT = "getAllUniversitiesQueryBySort";
    private static final String GET_ALL_MODULES_QUERY_BY_NAME = "getAllModulesQueryByName";
    private static final String GET_ALL_MODULES_QUERY_BY_SORT = "getAllModulesQueryBySort";


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
            throw new IllegalStateException("Create university not allowed");
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
            throw new IllegalStateException("Get collection of universities not allowed");
        }
    }

    public boolean isGetAllUniversitiesQueryByNameAllowed() {
        return isLinkAvailable(GET_ALL_UNIVERSITIES_QUERY_BY_NAME);
    }

    public void getAllUniversitiesQueryByName(String universityName) throws IOException {
        if (isGetAllUniversitiesQueryByNameAllowed()) {
            String url = getUrl(GET_ALL_UNIVERSITIES_QUERY_BY_NAME)
                    .replace("name", universityName);
            processResponse(this.client.getCollectionOfUniversities(url), (response) -> {
                this.currentUniversityData = new LinkedList(response.getResponseData());
                this.cursorUniversityData = 0;
            });
        } else {
            throw new IllegalStateException("Get collection of universities by query name not allowed");
        }
    }

    public boolean isGetAllUniversitiesQueryBySortAllowed() {
        return isLinkAvailable(GET_ALL_UNIVERSITIES_QUERY_BY_SORT);
    }

    public void getAllUniversitiesQueryBySort(String attribute, String ascending) throws IOException {
        if (isGetAllUniversitiesQueryBySortAllowed()) {
            String url = getUrl(GET_ALL_UNIVERSITIES_QUERY_BY_SORT)
                    .replace("attribute", attribute)
                    .replace("true", ascending);
            processResponse(this.client.getCollectionOfUniversities(url), (response) -> {
                this.currentUniversityData = new LinkedList(response.getResponseData());
                this.cursorUniversityData = 0;
            });
        } else {
            throw new IllegalStateException("Get collection of universities by query sort not allowed");
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
            throw new IllegalStateException("Get single university not allowed");
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
        return isLinkAvailable(DELETE_SINGLE_UNIVERSITY);
    }

    public void deleteSingleUniversity() throws IOException {
        if (isDeleteUniversityAllowed()) {
            processResponse(this.client.deleteUniversity(getUrl(DELETE_SINGLE_UNIVERSITY)), (response) -> {
            });
        }
        else {
            throw new IllegalStateException("Delete university not allowed");
        }
    }

    public boolean isUpdateUniversityAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_UNIVERSITY);
    }

    public void updateSingleUniversity(UniversityClientModel university) throws IOException {
        if (isUpdateUniversityAllowed()) {
            university.setId(currentUniversityData.getFirst().getId());
            processResponse(this.client.putUniversity(getUrl(UPDATE_SINGLE_UNIVERSITY), university), (response -> {
            }));
        } else {
            throw new IllegalStateException("Update university not allowed");
        }
    }

    //MODULES

    public boolean isCreateModuleAllowed() {
        return isLinkAvailable(CREATE_MODULE);
    }

    public void createModule(ModuleClientModel module) throws IOException {
        if (isCreateModuleAllowed()) {
            processResponse(this.webClient.postNewModule(getUrl(CREATE_MODULE), module), (response) -> {
                this.currentModuleData = Collections.EMPTY_LIST;
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException("Create module not allowed");
        }
    }

    public boolean isGetAllModulesAllowed() {
        return isLinkAvailable(GET_ALL_LINKED_MODULES);
    }

    public void getAllModules() throws IOException {
        if (isGetAllModulesAllowed()) {
            processResponse(this.webClient.getCollectionOfModules(getUrl(GET_ALL_LINKED_MODULES)), (response) -> {
                this.currentModuleData = new LinkedList(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException("Get collection of modules not allowed");
        }
    }

    public boolean isGetAllModulesQueryByNameAllowed() {
        return isLinkAvailable(GET_ALL_MODULES_QUERY_BY_NAME);
    }

    public void getAllModulesQueryByName(String moduleName) throws IOException {
        if (isGetAllModulesQueryByNameAllowed()) {
            String url = getUrl(GET_ALL_MODULES_QUERY_BY_NAME)
                    .replace("name", moduleName);
            processResponse(this.webClient.getCollectionOfModules(url), (response) -> {
                this.currentModuleData = new LinkedList(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException("Get collection of modules by query name not allowed");
        }
    }

    public boolean isGetAllModulesQueryBySortAllowed() {
        return isLinkAvailable(GET_ALL_MODULES_QUERY_BY_SORT);
    }

    public void getAllModulesQueryBySort(String attribute, String ascending) throws IOException {
        if (isGetAllModulesQueryBySortAllowed()) {
            String url = getUrl(GET_ALL_MODULES_QUERY_BY_SORT)
                    .replace("attribute", attribute)
                    .replace("true", ascending);
            processResponse(this.webClient.getCollectionOfModules(url), (response) -> {
                this.currentModuleData = new LinkedList(response.getResponseData());
                this.cursorModuleData = 0;
            });
        } else {
            throw new IllegalStateException("Get collection of modules by query sort not allowed");
        }
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

    public boolean isGetSingleModuleAllowed() {
        return !currentModuleData.isEmpty() || isLocationHeaderAvailable();
    }

    public void getSingleModule() throws IOException {
        if (isLocationHeaderAvailable()) {
            getSingleModule(getLocationHeaderURL());
        } else if (!this.currentModuleData.isEmpty()) {
            getSingleModule(this.cursorModuleData);
        } else {
            throw new IllegalStateException("Get single module not allowed");
        }
    }

    public void getSingleModule(int index) throws IOException {
        getSingleModule(this.currentModuleData.get(index).getSelfLink().getUrl());
    }

    private void getSingleModule(String url) throws IOException {
        processResponse(this.webClient.getSingleModule(url), (response) -> {
            this.currentModuleData = new LinkedList(response.getResponseData());
            this.cursorModuleData = 0;
        });
    }

    public boolean isDeleteModuleAllowed() {
        return isLinkAvailable(DELETE_MODULE_FROM_UNIVERSITY);
    }

    public void deleteSingleModule() throws IOException {
        if (isDeleteModuleAllowed()){
            processResponse(this.webClient.deleteModule(getUrl(DELETE_MODULE_FROM_UNIVERSITY)), (response) -> {
            });
        } else {
            throw new IllegalStateException("Delete module not allowed");
        }
    }

    public boolean isUpdateModuleAllowed() {
        return isLinkAvailable(UPDATE_SINGLE_MODULE);
    }

    public void updateSingleModule(ModuleClientModel module) throws IOException {
        if (isUpdateModuleAllowed()) {
            module.setId(currentModuleData.getFirst().getId());
            processResponse(this.webClient.putModule(getUrl(UPDATE_SINGLE_MODULE), module), (response -> {
            }));
        } else {
            throw new IllegalStateException("Update module not allowed");
        }
    }
}
