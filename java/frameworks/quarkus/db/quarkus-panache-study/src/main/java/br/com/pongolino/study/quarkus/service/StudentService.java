package br.com.pongolino.study.quarkus.service;

import br.com.pongolino.study.quarkus.entity.Student;
import br.com.pongolino.study.quarkus.repository.StudentRepository;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Set;

@ApplicationScoped
public class StudentService {
    private final StudentRepository studentRepository;

    @Inject
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public List<Student> findById(Set<Long> ids) {
        return studentRepository.find("id", Sort.by("id", Sort.Direction.Ascending), ids).list();
    }
}
