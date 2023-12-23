package br.com.pongolino.study.quarkus.entity;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "discipline")
public class Discipline {
    @Id @GeneratedValue
    private Long id;
    private String name;
    @ManyToMany
    private Set<Student> students = new HashSet<>();

    Discipline() {}

    public Discipline(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addStudent(Student student) {
        student.addDiscipline(this);
        this.students.add(student);
    }

    public void addStudent(List<Student> students) {
        students.forEach(this::addStudent);
    }

    public void removeStudent(Student student) {
        student.removeDiscipline(this);
        this.students.remove(student);
    }

    public void removeStudent(List<Student> students) {
        students.forEach(this::removeStudent);
    }
}
