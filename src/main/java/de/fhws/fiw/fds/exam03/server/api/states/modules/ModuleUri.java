package de.fhws.fiw.fds.exam03.server.api.states.modules;

import de.fhws.fiw.fds.exam03.Start;

public interface ModuleUri
{
	String PATH_ELEMENT = "modules";
	String REL_PATH = Start.CONTEXT_PATH + "/api/" + PATH_ELEMENT;
	String REL_PATH_ID = REL_PATH + "/{id}";
}
