package br.com.cwi.crescer.redeSocial.repository;

import br.com.cwi.crescer.redeSocial.domain.Comentario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComentarioRepository extends JpaRepository<Comentario, Long> {
}
