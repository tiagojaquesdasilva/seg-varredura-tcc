package br.com.cwi.crescer.redeSocial.security.mapper;

import br.com.cwi.crescer.redeSocial.security.controller.request.UsuarioRequest;
import br.com.cwi.crescer.redeSocial.security.controller.response.UsuarioResponse;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setNome(request.getNome());
        entity.setEmail(request.getEmail());
        entity.setDataNascimento(request.getDataNascimento());
        entity.setApelido(request.getApelido());
        entity.setImagemPerfil(request.getImagemPerfil());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario entity) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(entity.getId());
        response.setNome(entity.getNome());
        response.setEmail(entity.getEmail());
        return response;
    }
}
