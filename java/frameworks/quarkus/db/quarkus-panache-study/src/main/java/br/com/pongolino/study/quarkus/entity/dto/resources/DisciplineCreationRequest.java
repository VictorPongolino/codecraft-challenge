package br.com.pongolino.study.quarkus.entity.dto.resources;

import jakarta.json.bind.annotation.JsonbProperty;

import java.util.Set;

public class DisciplineCreationRequest {
    private String name;
    private Set<Integer> students;

    DisciplineCreationRequest() {}

    @JsonbProperty("name")
    private void setName(String name) {
        this.name = name;
    }

    @JsonbProperty("students")
    private void setStudents(Set<Integer> students) {
        this.students = students;
    }

    public String getName() {
        return name;
    }

    public Set<Integer> getStudents() {
        return students;
    }

    @Override
    public String toString() {
        return "DisciplineCreationRequest{" +
                "name='" + name + '\'' +
                ", students=" + students +
                '}';
    }
}

