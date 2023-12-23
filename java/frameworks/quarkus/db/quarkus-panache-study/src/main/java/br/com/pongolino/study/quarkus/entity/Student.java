package br.com.pongolino.study.quarkus.entity;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "student")
public class Student {
    @Id @GeneratedValue
    private Long id;
    private String name;
    private LocalDate enroll;
    @ManyToMany
    private Set<Discipline> discipline = new HashSet<>();
    @OneToMany
    private Set<Grade> grade = new HashSet<>();

    Student() {}

    public Student(String name, LocalDate enroll, Set<Discipline> discipline) {
        this.name = name;
        this.enroll = enroll;
        this.discipline = discipline;
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

    public LocalDate getEnroll() {
        return enroll;
    }

    public void setEnroll(LocalDate enroll) {
        this.enroll = enroll;
    }

    public Set<Discipline> getDiscipline() {
        return discipline;
    }

    public void addDiscipline(Discipline discipline) {
        this.discipline.add(discipline);
    }

    public void removeDiscipline(Discipline discipline) {
        this.discipline.remove(discipline);
    }

    public Set<Grade> getGrade() {
        return grade;
    }

    public void addGrade(Grade grade) {
        this.grade.add(grade);
    }

    public void removeGrade(Grade grade) {
        this.grade.remove(grade);
    }
}
