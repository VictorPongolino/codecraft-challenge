package br.com.pongolino.study.quarkus.resources;

import br.com.pongolino.study.quarkus.entity.Discipline;
import br.com.pongolino.study.quarkus.entity.dto.resources.DisciplineCreationRequest;
import br.com.pongolino.study.quarkus.entity.dto.resources.converters.DisciplineCreationToDisciplineConverter;
import br.com.pongolino.study.quarkus.service.DisciplineService;
import jakarta.inject.Inject;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.UriBuilder;

import java.net.URI;

@Path("/v1/discipline")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class DisciplineResource {

    private final DisciplineCreationToDisciplineConverter disciplineCreationConverter;
    private final DisciplineService disciplineService;

    @Inject
    public DisciplineResource(DisciplineCreationToDisciplineConverter disciplineCreationConverter, DisciplineService disciplineService) {
        this.disciplineCreationConverter = disciplineCreationConverter;
        this.disciplineService = disciplineService;
    }

    @POST
    public Response createDiscipline(DisciplineCreationRequest request) {
        Discipline convertedDiscipline = disciplineCreationConverter.convert(request);
        disciplineService.save(convertedDiscipline);

        URI createdDisciplineURI = UriBuilder.fromResource(DisciplineResource.class)
                .path("/{id}")
                .build(convertedDiscipline.getId());

        return Response.created(createdDisciplineURI).build();
    }
}

