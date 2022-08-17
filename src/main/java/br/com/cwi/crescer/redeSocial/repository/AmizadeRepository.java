package br.com.cwi.crescer.redeSocial.repository;

import br.com.cwi.crescer.redeSocial.domain.Amizade;
import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AmizadeRepository extends JpaRepository<Amizade, Long> {
    List<Amizade> findByAmigoIdAndStatusAmizade(Long usuarioId, StatusAmizade status);

    List<Amizade> findByAmigoIdAndStatusAmizadeOrUsuarioIdAndStatusAmizade(Long usuarioA, StatusAmizade statusA, Long usuario, StatusAmizade status);

    List<Amizade> findByUsuarioIdAndAmigoIdOrUsuarioIdAndAmigoId(Long usuarioLogadoId, Long possivel_amigoId, Long usuarioLogadoId1, Long possivel_amigoId1);
}
