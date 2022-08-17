package br.com.cwi.crescer.redeSocial.service.core;

import br.com.cwi.crescer.redeSocial.repository.AmizadeRepository;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import br.com.cwi.crescer.redeSocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@Service
public class BuscarUsuarioService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private UsuarioRepository repository;

    public Usuario porId(Long id) {
        return repository
                .findById(id)
                .orElseThrow(() -> new ResponseStatusException(UNPROCESSABLE_ENTITY, "usuario não encontrado"));
    }
}
