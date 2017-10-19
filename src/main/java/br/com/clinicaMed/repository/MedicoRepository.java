package br.com.clinicaMed.repository;

import br.com.clinicaMed.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {

}