package br.com.pongolino.study.quarkus.repository;

import br.com.pongolino.study.quarkus.entity.Discipline;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class DisciplineRepository implements PanacheRepository<Discipline> {
}

