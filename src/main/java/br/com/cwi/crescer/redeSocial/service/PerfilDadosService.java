package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.request.EditarPerfilRequest;
import br.com.cwi.crescer.redeSocial.controller.response.PerfilDadosResponse;
import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import br.com.cwi.crescer.redeSocial.mapper.PerfilDadosMapper;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import br.com.cwi.crescer.redeSocial.security.repository.UsuarioRepository;
import br.com.cwi.crescer.redeSocial.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
public class PerfilDadosService {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    public PerfilDadosResponse buscar(Long usuarioLogadoId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioLogadoId);

        return PerfilDadosMapper.toResponse(usuario, StatusAmizade.NULL);
    }

    @Transactional
    public void editar(Long usuarioLogadoId, EditarPerfilRequest request) {

        Usuario usuario = buscarUsuarioService.porId(usuarioLogadoId);

        usuario.setNome(request.getNome());
        usuario.setApelido(Objects.isNull(request.getApelido()) ? usuario.getApelido() : request.getApelido());
        usuario.setImagemPerfil(Objects.isNull(request.getImagemPerfil()) ? usuario.getImagemPerfil() : request.getImagemPerfil());

        usuarioRepository.save(usuario);
    }
}
