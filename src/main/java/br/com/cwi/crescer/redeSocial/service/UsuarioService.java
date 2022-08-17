package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.response.UsuarioResponse;
import br.com.cwi.crescer.redeSocial.mapper.UsuarioMapper;
import br.com.cwi.crescer.redeSocial.security.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UsuarioResponse> buscarUsuarios(String filtro) {
        return usuarioRepository.findByNomeContainsIgnoreCaseOrEmailContainsIgnoreCase(filtro, filtro)
                .stream().map(usuario -> UsuarioMapper.toResponse(usuario)).collect(Collectors.toList());
    }
}
