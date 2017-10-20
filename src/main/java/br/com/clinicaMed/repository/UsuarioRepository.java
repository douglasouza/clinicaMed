package br.com.clinicaMed.repository;

import br.com.clinicaMed.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

}