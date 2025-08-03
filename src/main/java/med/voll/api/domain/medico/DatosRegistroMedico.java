package med.voll.api.domain.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import med.voll.api.domain.direccion.DatosDireccion;

public record DatosRegistroMedico(@NotBlank(message = "El nombre es obligatorio") String nombre,
                                  @NotBlank(message = "El email es obligatorio") @Email String email,
                                  @NotBlank(message = "El telefono es obligatorio") String telefono,
                                  @NotBlank(message = "El documento es obligatorio") @Pattern(regexp = "\\d{7,10}") String documento,
                                  @NotNull(message = "La especialidad es obligatoria") Especialidad especialidad,
                                  @NotNull(message = "La direccion es obligatoria") @Valid DatosDireccion direccion) {
}
