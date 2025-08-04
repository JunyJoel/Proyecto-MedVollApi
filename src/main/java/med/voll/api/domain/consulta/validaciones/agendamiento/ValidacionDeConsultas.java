package med.voll.api.domain.consulta.validaciones.agendamiento;

import med.voll.api.domain.consulta.DatosAgendarConsulta;
//Patron Strategy
public interface ValidacionDeConsultas {
    void validar(DatosAgendarConsulta datos); //Patron Strategy
}
