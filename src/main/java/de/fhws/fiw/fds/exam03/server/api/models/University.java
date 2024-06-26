/*
 * Copyright 2021 University of Applied Sciences Würzburg-Schweinfurt, Germany
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License
 * is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express
 * or implied. See the License for the specific language governing permissions and limitations under
 * the License.
 */

package de.fhws.fiw.fds.exam03.server.api.models;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonRootName;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.Link;
import de.fhws.fiw.fds.sutton.server.api.hyperlinks.annotations.SuttonLink;
import de.fhws.fiw.fds.sutton.server.models.AbstractModel;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@JsonRootName("university")
@JsonInclude(JsonInclude.Include.NON_NULL)
@XmlRootElement(name = "university")
public class University extends AbstractModel {

    private String name;
    private String country;
    private String departmentName;
    private String departmentUrl;
    private String contactPerson;

    private int outboundStudents;
    private int inboundStudents;

    private LocalDate nextSpringSemesterStart;
    private LocalDate nextAutumnSemesterStart;

    @SuttonLink(
            value = "universities/${id}",
            rel = "self"
    )
    private transient Link selfLink;

    @SuttonLink(
            value = "universities/${id}/modules",
            rel = "getModulesOfUniversity"
    )
    private transient Link module;

    public University() {
    }

    public University(final String name, final String country, final String departmentName, final String departmentUrl,
                      final String contactPerson, final int outboundStudents, final int inboundStudents,
                      final LocalDate nextSpringSemesterStart, final LocalDate nextAutumnSemesterStart) {
        this.name = name;
        this.country = country;
        this.departmentName = departmentName;
        this.departmentUrl = departmentUrl;
        this.contactPerson = contactPerson;
        if (outboundStudents < 0) {
            throw new IllegalArgumentException("Outbound students cannot be negative");
        }
        if (inboundStudents < 0) {
            throw new IllegalArgumentException("Inbound students cannot be negative");
        }
        this.outboundStudents = outboundStudents;
        this.inboundStudents = inboundStudents;
        this.nextSpringSemesterStart = nextSpringSemesterStart;
        this.nextAutumnSemesterStart = nextAutumnSemesterStart;
    }

    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    public Link getModule() {
        return module;
    }

    public void setModule(Link module) {
        this.module = module;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(final String country) {
        this.country = country;
    }

    public LocalDate getNextSpringSemesterStart() {
        return nextSpringSemesterStart;
    }

    public void setNextSpringSemesterStart(final LocalDate nextSpringSemesterStart) {
        this.nextSpringSemesterStart = nextSpringSemesterStart;
    }

    public LocalDate getNextAutumnSemesterStart() {
        return nextAutumnSemesterStart;
    }

    public void setNextAutumnSemesterStart(final LocalDate nextAutumnSemesterStart) {
        this.nextAutumnSemesterStart = nextAutumnSemesterStart;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(final String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public String getDepartmentUrl() {
        return departmentUrl;
    }

    public void setDepartmentUrl(String departmentUrl) {
        this.departmentUrl = departmentUrl;
    }

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public int getOutboundStudents() {
        return outboundStudents;
    }

    public void setOutboundStudents(int outboundStudents) {
        if (outboundStudents < 0) {
            throw new IllegalArgumentException("Outbound students cannot be negative");
        }
        this.outboundStudents = outboundStudents;
    }

    public int getInboundStudents() {
        return inboundStudents;
    }

    public void setInboundStudents(int inboundStudents) {
        if (inboundStudents < 0) {
            throw new IllegalArgumentException("Inbound students cannot be negative");
        }
        this.inboundStudents = inboundStudents;
    }

    @Override
    public String toString() {
        return "University{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                ", departmentName='" + departmentName + '\'' +
                ", departmentUrl='" + departmentUrl + '\'' +
                ", contactPerson='" + contactPerson + '\'' +
                ", outboundStudents=" + outboundStudents +
                ", inboundStudents=" + inboundStudents +
                ", nextSpringSemesterStart=" + nextSpringSemesterStart +
                ", nextAutumnSemesterStart=" + nextAutumnSemesterStart +
                '}';
    }
}