package med.voll.api.domain.consulta.validaciones.agendamiento;

import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.DatosAgendarConsulta;
import med.voll.api.domain.medico.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidacionMedicoActivo  implements ValidacionDeConsultas{

    @Autowired
    private MedicoRepository repository;
    public void validar(DatosAgendarConsulta datos) {
        //en caso de que el usuario no ingrese un medico. se le asigna uno aleatorio
        if (datos.idMedico() == null) {
            return;
        }
        var medicoActivo = repository.findStatusById(datos.idMedico());
        if (!medicoActivo) {
            throw new ValidacionException("Medico inactivo");
        }

    }
}
