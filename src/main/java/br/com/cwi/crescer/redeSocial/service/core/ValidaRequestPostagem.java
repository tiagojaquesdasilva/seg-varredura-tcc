package br.com.cwi.crescer.redeSocial.service.core;

import br.com.cwi.crescer.redeSocial.controller.request.PostagemRequest;
import org.springframework.stereotype.Service;

@Service
public class ValidaRequestPostagem {
    public String validarImagem(PostagemRequest request) {

        if (request.getImagemPostagem() != null && (!request.getImagemPostagem().trim().isEmpty())) {
            return request.getImagemPostagem().trim();
        }
        return null;
    }

    public String validarTexto(PostagemRequest request) {

        if (request.getTextoPostagem() != null && (!request.getTextoPostagem().trim().isEmpty())) {
            return request.getTextoPostagem().trim();
        }
        return null;
    }
}
