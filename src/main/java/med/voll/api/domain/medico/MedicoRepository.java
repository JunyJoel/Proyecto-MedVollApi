package med.voll.api.domain.medico;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface MedicoRepository extends JpaRepository<Medico,Long> {
    Page<Medico> findAllByStatusTrue(Pageable paginacion);

    @Query("""
            SELECT m FROM Medico m
            WHERE m.status = true
            AND m.especialidad = :especialidad
            AND m.id NOT IN (
                SELECT c.medico.id FROM Consulta c
                WHERE c.fecha = :fecha
            )
            ORDER BY rand()
            LIMIT 1
            """)
    Medico elegirMedicoAleatorioDisponibleEnLaFecha(Especialidad especialidad, @NotNull @Future LocalDateTime fecha);

    @Query("""
            SELECT m.status FROM Medico m
            WHERE m.id = :idMedico
            """)
    boolean findStatusById(Long idMedico);
}
