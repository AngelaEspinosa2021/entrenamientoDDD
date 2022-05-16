package co.com.sofkau.entrenamento.curso;

import co.com.sofka.business.repository.DomainEventRepository;
import co.com.sofka.domain.generic.DomainEvent;
import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizDeMentoria;
import co.com.sofkau.entrenamiento.curso.events.MentoriaCreada;
import co.com.sofkau.entrenamiento.curso.values.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
class AgregarDirectrizDeMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizDeMentoriaUseCase useCase;

    @Mock
    private DomainEventRepository repository;

    @Test
    void agregarDirectrizAUnaMentoriaHappyPass(){
        //arrange
        CursoId cursoId = CursoId.of("0010");
        MentoriaId mentoriaId = MentoriaId.of("0002");
        Directiz directiz = new Directiz("Nueva Directriz");
        var command = new AgregarDirectrizDeMentoria(cursoId, mentoriaId, directiz);

        when(repository.getEventsBy("0002")).thenReturn(history());

        //act

    }

    private List<DomainEvent> history(){
        CursoId cursoId = CursoId.of("00010");
        MentoriaId mentoriaId = MentoriaId.of("0002");
        Nombre nombre = new Nombre("Aprendiendo de casos de usos");
        Fecha fecha = new Fecha(LocalDateTime.now(), LocalDate.now());
        var event = new MentoriaCreada(
                mentoriaId,
                nombre,
                fecha
        );
        event.setAggregateRootId("00010");
        return List.of(event);
    }

}