package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.generic.UseCaseHandler;
import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.business.support.RequestCommand;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.Curso;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizDeMentoria;
import co.com.sofkau.entrenamiento.curso.commands.AgregarMentoria;
import co.com.sofkau.entrenamiento.curso.events.CursoCreado;
import co.com.sofkau.entrenamiento.curso.events.DirectrizAgregadaAMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class AgregarDirectrizDeMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizDeMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void agregarDirectrizAMentoriaHappyPass(){
        //arrange
        CursoId cursoId = CursoId.of("0010");
        MentoriaId mentoriaId = MentoriaId.of("00002");
        Directiz directiz = new Directiz("Nueva Directriz para insertar.");
        var command = new AgregarDirectrizDeMentoria(cursoId,mentoriaId,directiz);

        when(repository.getEventsBy("0010")).thenReturn(history());
        useCase.addRepository(repository);
        //act
        var events = UseCaseHandler.getInstance()
                .setIdentifyExecutor(command.getCursoId().value())
                .syncExecutor(useCase, new RequestCommand<>(command))
                .orElseThrow()
                .getDomainEvents();

        //assert
        var event = (DirectrizAgregadaAMentoria)events.get(0);
        Assertions.assertEquals("Nueva Directriz para insertar.", event.getDirectiz().value());

    }

    private List<DomainEvent> history(){
        Nombre nombreCurso = new Nombre("0010");
        Descripcion descripcion = new Descripcion("Nuevo Curso implementado");
        var eventCurso = new CursoCreado(
                nombreCurso,
                descripcion
        );
        MentoriaId mentoriaId = MentoriaId.of("00002") ;
        Nombre nombreMentoria = new Nombre("Nueva Mentoria Creada.");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var eventMentoria = new MentoriaCreada(
                mentoriaId,
                nombreMentoria,
                fecha
        );
        eventMentoria.setAggregateRootId("0010");
        return List.of(eventCurso, eventMentoria);
    }

}