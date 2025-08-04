package med.voll.api.domain.consulta;

import jakarta.validation.Valid;
import med.voll.api.domain.ValidacionException;
import med.voll.api.domain.consulta.validaciones.agendamiento.ValidacionDeConsultas;
import med.voll.api.domain.consulta.validaciones.cancelacion.ValidacionCancelacionConsulta;
import med.voll.api.domain.medico.Medico;
import med.voll.api.domain.medico.MedicoRepository;
import med.voll.api.domain.paciente.PacienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgendamentoDeConsultas {
    @Autowired
    ConsultaRepository consultaRepository;
    @Autowired
    PacienteRepository pacienteRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    private List<ValidacionDeConsultas> validaciones; //Patron Strategy
    @Autowired
    private List<ValidacionCancelacionConsulta> validadoresCancelacion; //Patron Strategy>

    public DatosDetalleConsulta agendarConsulta(DatosAgendarConsulta datos) {
        if(!pacienteRepository.existsById(datos.idPaciente())) throw new ValidacionException("Id dePaciente no encontrado");
        if(datos.idMedico() != null && !medicoRepository.existsById(datos.idMedico())) throw new ValidacionException("Id de Medico no encontrado");

        //validaciones //Patron Strategy
        validaciones.forEach(v -> v.validar(datos)); //Patron Strategy

        var medico = elegirMedico(datos);
        if(medico == null) throw new ValidacionException("No hay medicos disponibles en la fecha con esa especialidad");
        var paciente = pacienteRepository.findById(datos.idPaciente()).get();
        var consulta = new Consulta(null, medico, paciente, datos.fecha(),null);
        consultaRepository.save(consulta);
        return new DatosDetalleConsulta(consulta);
    }

    private Medico elegirMedico(DatosAgendarConsulta datos) {
        if (datos.idMedico() != null){
            return medicoRepository.getReferenceById(datos.idMedico());
        }
        if(datos.especialidad() == null) {
            throw new ValidacionException("Especialidad no puede ser nula si no se ingresa un Medico");
        }
        return medicoRepository.elegirMedicoAleatorioDisponibleEnLaFecha(datos.especialidad(), datos.fecha());
    }

    public void cancelarConsulta(@Valid DatosCancelacionConsulta datos) {
        /*if(datos.idConsulta() == null) throw new ValidacionException("Id de consulta no puede ser nulo");
        if(!consultaRepository.existsById(datos.idConsulta())) throw new ValidacionException("Consulta no registrada en DB");
        if(datos.motivo() == null) throw new ValidacionException("Debe ingresar un motivo de cancelacion");

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        var fechaActual = LocalDateTime.now();
        var diferenciaEnHoras = Duration.between(fechaActual, consulta.getFecha()).toHours();
        if (diferenciaEnHoras < 24) throw new ValidacionException("No se puede cancelar una consulta con menos de 24 horas de anticipacion" + diferenciaEnHoras);

        consulta.cancelar(datos.motivo());*/

        if(!consultaRepository.existsById(datos.idConsulta())){
            throw new ValidacionException("Consulta no registrada en DB");
        }

        validadoresCancelacion.forEach(v -> v.validar(datos));

        var consulta = consultaRepository.getReferenceById(datos.idConsulta());
        consulta.cancelar(datos.motivo());
    }
}
