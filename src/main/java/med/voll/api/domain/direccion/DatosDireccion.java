package med.voll.api.domain.direccion;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record DatosDireccion(@NotBlank(message = "El campo calle es obligatorio") String calle,
                             String numero,
                             String complemento,
                             @NotBlank(message = "El campo barrio es obligatorio") String barrio,
                             @NotBlank(message = "El campo código postal es obligatorio") @Pattern(regexp = "\\d{5}") String codigo_postal,
                             @NotBlank(message = "El campo ciudad es obligatorio") String ciudad,
                             @NotBlank(message = "El campo estado es obligatorio") String estado) {
}
