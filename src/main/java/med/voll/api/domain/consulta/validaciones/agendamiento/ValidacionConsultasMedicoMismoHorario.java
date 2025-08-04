package med.voll.api.domain.consulta.validaciones.agendamiento;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionConsultasMedicoMismoHorario implements ValidacionDeConsultas {

    @Autowired
    private ConsultaRepository repository;

    public void validar(DatosAgendarConsulta datos) {
        var medicoConConsultaEnElMismoHorario = repository.existsByMedicoIdAndFechaAndMotivoCancelacionIsNull(datos.idMedico(), datos.fecha());
        if (medicoConConsultaEnElMismoHorario) {
            throw new ValidacionException("Medico ya tiene consulta en el mismo horario");
        }
    }
}
