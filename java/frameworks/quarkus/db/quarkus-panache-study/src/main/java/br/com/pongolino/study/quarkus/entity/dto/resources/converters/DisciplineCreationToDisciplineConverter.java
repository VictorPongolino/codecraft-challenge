package br.com.pongolino.study.quarkus.entity.dto.resources.converters;

import br.com.pongolino.study.quarkus.entity.Discipline;
import br.com.pongolino.study.quarkus.entity.dto.resources.DisciplineCreationRequest;
import br.com.pongolino.study.quarkus.service.StudentService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.stream.Collectors;

@ApplicationScoped
public class DisciplineCreationToDisciplineConverter {

    private final StudentService studentService;

    @Inject
    public DisciplineCreationToDisciplineConverter(StudentService studentService) {
        this.studentService = studentService;
    }

    public Discipline convert(DisciplineCreationRequest request) {
        Discipline discipline = new Discipline(request.getName());
        discipline.addStudent(studentService.findById(request.getStudents().stream().map(Integer::longValue).collect(Collectors.toSet())));
        return discipline;
    }
}
