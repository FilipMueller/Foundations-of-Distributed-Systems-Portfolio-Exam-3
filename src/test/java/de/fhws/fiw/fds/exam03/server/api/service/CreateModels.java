package de.fhws.fiw.fds.exam03.server.api.service;

import de.fhws.fiw.fds.exam03.client.models.UniversityClientModel;
import de.fhws.fiw.fds.exam03.client.models.ModuleClientModel;
import org.jetbrains.annotations.NotNull;

import java.time.LocalDate;

public class CreateModels {
    public static @NotNull UniversityClientModel getUniversityClientModel()
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

    public static @NotNull ModuleClientModel getModuleClientModel() {
        var module = new ModuleClientModel();
        module.setName("Introduction to Quantum Computing");
        module.setSemester(1); // 1 for spring, 2 for autumn
        module.setCreditPoints(5); // Assuming credit points as 5
        return module;
    }
}
