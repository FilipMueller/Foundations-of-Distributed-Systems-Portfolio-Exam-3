// Copyright 2022 Peter Braun
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package de.fhws.fiw.fds.suttondemo.client.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import de.fhws.fiw.fds.sutton.client.converters.ClientLinkJsonConverter;
import de.fhws.fiw.fds.sutton.client.model.AbstractClientModel;
import de.fhws.fiw.fds.sutton.client.utils.Link;
import jakarta.xml.bind.annotation.XmlRootElement;

import java.time.LocalDate;

@XmlRootElement
public class UniversityClientModel extends AbstractClientModel {

    private String name;
    private String country;
    private String departmentName;
    private String departmentUrl;
    private String contactPerson;

    private int outboundStudents;
    private int inboundStudents;

    private LocalDate nextSpringSemesterStart = LocalDate.of(1970, 1, 1);
    private LocalDate nextAutumnSemesterStart = LocalDate.of(1970, 1, 1);

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link selfLink;

    @JsonDeserialize(using = ClientLinkJsonConverter.class)
    private Link module;

    public UniversityClientModel() {
    }

    public UniversityClientModel(final String name, final String country, final String departmentName, final String departmentUrl,
                      final String contactPerson, final int outboundStudents, final int inboundStudents,
                      final LocalDate nextSpringSemesterStart, final LocalDate nextAutumnSemesterStart) {
        this.name = name;
        this.country = country;
        this.departmentName = departmentName;
        this.departmentUrl = departmentUrl;
        this.contactPerson = contactPerson;
        this.outboundStudents = outboundStudents;
        this.inboundStudents = inboundStudents;
        this.nextSpringSemesterStart = nextSpringSemesterStart;
        this.nextAutumnSemesterStart = nextAutumnSemesterStart;
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

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public String getDepartmentUrl() {
        return departmentUrl;
    }

    public void setDepartmentUrl(String departmentUrl) {
        this.departmentUrl = departmentUrl;
    }

    public int getOutboundStudents() {
        return outboundStudents;
    }

    public void setOutboundStudents(int outboundStudents) {
        this.outboundStudents = outboundStudents;
    }

    public int getInboundStudents() {
        return inboundStudents;
    }

    public void setInboundStudents(int inboundStudents) {
        this.inboundStudents = inboundStudents;
    }

    @JsonIgnore
    public Link getSelfLink() {
        return selfLink;
    }

    public void setSelfLink(Link selfLink) {
        this.selfLink = selfLink;
    }

    @JsonIgnore
    public Link getModule() {
        return module;
    }

    public void setModule(Link location) {
        this.module = location;
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
