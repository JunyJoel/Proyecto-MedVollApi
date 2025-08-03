package med.voll.api.controller;


import jakarta.validation.Valid;
import med.voll.api.domain.consulta.*;
import med.voll.api.domain.paciente.DatosDetallePaciente;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {
    @Autowired
    private AgendamentoDeConsultas agenda;
    @Autowired
    private ConsultaRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity agendar(@RequestBody @Valid DatosAgendarConsulta datos) {
        var detalleConsulta = agenda.agendarConsulta(datos);
        return ResponseEntity.ok(detalleConsulta);
    }

    @PutMapping
    @Transactional
    public ResponseEntity cancelar(@RequestBody @Valid DatosCancelacionConsulta datos) {
        agenda.cancelarConsulta(datos);
        System.out.println("Cancelando consulta...\n");
        return ResponseEntity.ok(datos);


    }


}
