package co.com.sofkau.entrenamento.curso;

import co.com.sofkau.entrenamiento.curso.commands.AgregarDirectrizDeMentoria;
import co.com.sofkau.entrenamiento.curso.values.CursoId;
import co.com.sofkau.entrenamiento.curso.values.Directiz;
import co.com.sofkau.entrenamiento.curso.values.MentoriaId;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class AgregarDirectrizDeMentoriaUseCaseTest {

    @InjectMocks
    private AgregarDirectrizDeMentoriaUseCase useCase;

    @Test
    void agregarDirectrizAUnaMentoriaHappyPass(){
        //arrange
        CursoId cursoId = CursoId.of("0010");
        MentoriaId mentoriaId = MentoriaId.of("0002");
        Directiz directiz = new Directiz("Nueva Directriz");
        var command = new AgregarDirectrizDeMentoria( cursoId, mentoriaId, directiz);
    }

}