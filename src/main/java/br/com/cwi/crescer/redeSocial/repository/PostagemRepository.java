package br.com.cwi.crescer.redeSocial.repository;

import br.com.cwi.crescer.redeSocial.domain.Postagem;
import br.com.cwi.crescer.redeSocial.domain.Restricao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostagemRepository extends JpaRepository<Postagem, Long> {
    Page<Postagem> findByUsuarioIdInOrderByDataPostagemDesc(List usuariosId, Pageable pageable);

    Page<Postagem> findByUsuarioIdAndRestricaoPostagemIn(Long usuarioId, List<Restricao> restricoes, Pageable pageable);
}

