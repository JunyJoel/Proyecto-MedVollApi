package med.voll.api.domain.paciente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroPaciente(
        @NotBlank(message = "El nombre es obligatorio") String nombre,
        @NotBlank(message = "El email es obligatorio") String email,
        @NotBlank(message = "El documento es obligatorio") String documento,
        @NotBlank(message = "El telefono es obligatorio") String telefono,
        @NotNull(message = "La direccion es obligatoria") DatosDireccion direccion
) {
}
