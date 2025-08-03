package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionPacienteActivo implements ValidacionDeConsultas {

    @Autowired
    private PacienteRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        var pacienteActivo = repository.findStatusById(datos.idPaciente());
        if (!pacienteActivo) {
            throw new ValidacionException("Paciente inactivo");
        }
    }
}
