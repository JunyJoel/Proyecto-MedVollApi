package med.voll.api.domain.consulta.validaciones.cancelacion;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.ConsultaRepository;
import med.voll.api.domain.consulta.DatosCancelacionConsulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidacionCancelacionConAnticipacion implements ValidacionCancelacionConsulta {
    @Autowired
    ConsultaRepository consultaRepository;

    public void validar(DatosCancelacionConsulta datos) {
        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var fechaActual = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(fechaActual, consulta.getFecha()).toHours();
        if (diferenciaEnHoras < 24) throw new ValidacionException("No se puede cancelar una consulta con menos de 24 horas de anticipacion" + diferenciaEnHoras);
    }
}
