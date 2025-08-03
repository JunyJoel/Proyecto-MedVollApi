package med.voll.api.domain.medico;

public record DatosListaMedico(Long id,
                               String nombre,
                               String email,
                               String documento,
                               Especialidad especialidad) {
    //CONSTRUCTOR
    public DatosListaMedico(Medico medico) {
        /*this.nombre = medico.getNombre();
        this.email = medico.getEmail();
        this.documento = medico.getDocumento();
        this.especialidad = medico.getEspecialidad();*/

        this(medico.getId(),
                medico.getNombre(),
                medico.getEmail(),
                medico.getDocumento(),
                medico.getEspecialidad());
    }
}
