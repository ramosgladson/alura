package med.voll.api.domain.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.entities.Paciente;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    Page<Paciente> findByAtivoTrue(Pageable page);

    @Query("""
            SELECT p.ativo
            FROM Paciente p
            WHERE p.id = :id
            """)
    boolean isAtivo(Long id);
}
