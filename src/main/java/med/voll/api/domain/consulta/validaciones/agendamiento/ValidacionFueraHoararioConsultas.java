package med.voll.api.domain.consulta.validaciones.agendamiento;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import org.springframework.stereotype.Component;
import java.time.DayOfWeek;

@Component
public class ValidacionFueraHoararioConsultas implements ValidacionDeConsultas {
    public void validar(DatosAgendarConsulta datos) {
        var fechaConsulta = datos.fecha();
        var domingo = fechaConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var horarioAntesDeApertura = fechaConsulta.getHour() < 7;
        var horarioDespuesDeCierre = fechaConsulta.getHour() > 18;
        if (domingo || horarioAntesDeApertura || horarioDespuesDeCierre) {
            throw new ValidacionException("Consulta fuera de horario de atencion");
        }
    }
}
