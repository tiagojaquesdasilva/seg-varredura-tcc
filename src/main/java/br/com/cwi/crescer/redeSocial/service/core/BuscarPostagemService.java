package br.com.cwi.crescer.redeSocial.service.core;

import br.com.cwi.crescer.redeSocial.domain.Postagem;
import br.com.cwi.crescer.redeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class BuscarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;

    public Postagem porId(Long postagemId) {
        return postagemRepository.findById(postagemId)
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "postagem não encontrada"));
    }
}
