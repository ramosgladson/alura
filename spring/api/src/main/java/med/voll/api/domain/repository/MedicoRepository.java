package med.voll.api.domain.repository;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import med.voll.api.domain.entities.Medico;
import med.voll.api.domain.enums.Especialidade;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findByAtivoTrue(Pageable page);

    @Query("""
            SELECT m FROM Medico m
            WHERE
            m.ativo = true
            AND
            m.id not in
                (SELECT c.medico.id FROM Consulta c
                WHERE
                c.schedule = :data)
            AND
            m.especialidade = :especialidade
            ORDER BY rand()
            LIMIT 1
            """)
    Medico getRandomMedico(Especialidade especialidade, LocalDateTime data);

    @Query("""
            SELECT m.ativo
            FROM Medico m
            WHERE m.id = :medicoId
            """)
    boolean isAtivo(Long medicoId);
}
