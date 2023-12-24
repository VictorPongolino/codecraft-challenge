package br.com.pongolino.study.quarkus.service;

import br.com.pongolino.study.quarkus.entity.Discipline;
import br.com.pongolino.study.quarkus.repository.DisciplineRepository;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class DisciplineService {
    @Inject
    private DisciplineRepository disciplineRepository;

    @Transactional
    public void save(Discipline discipline) {
        disciplineRepository.persist(discipline);
    }
}
