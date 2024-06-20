package de.fhws.fiw.fds.exam03.server.api.states.university_modules;

public interface UniversityModuleRelTypes {

	String CREATE_MODULE = "createModuleOfUniversity";
	String GET_ALL_LINKED_MODULES = "getAllModulesOfUniversity";
	String UPDATE_SINGLE_MODULE = "updateModuleOfUniversity";
	String DELETE_MODULE_FROM_UNIVERSITY = "deleteModuleFromUniversity";
	String GET_SINGLE_MODULE = "getSingleModuleOfUniversity";
	String GET_ALL_MODULES_QUERY_BY_NAME = "getAllModulesQueryByName";
	String GET_ALL_MODULES_QUERY_BY_SORT = "getAllModulesQueryBySort";
	String GET_ALL_MODULES_BY_PAGE_AND_OFFSET = "getAllModulesByPageAndOffset";
}
