package med.voll.api.domain.consulta.validaciones;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionConsultasPacienteMismoDia implements ValidacionDeConsultas {

    @Autowired
    private ConsultaRepository consultaRepository;
    public void validar(DatosAgendarConsulta datos) {
        var horarioApertura = datos.fecha().withHour(7);
        var horarioCierre = datos.fecha().withHour(18);
        var pacienteTieneConsultaEnElMismoDia = consultaRepository.existsByPacienteIdAndFechaBetween(datos.idPaciente(), horarioApertura, horarioCierre);
        if (pacienteTieneConsultaEnElMismoDia) {
            throw new ValidacionException("Paciente no puede agendar 2 consultas en el mismo dia");
        }
    }
}
