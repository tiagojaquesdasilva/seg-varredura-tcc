package br.com.cwi.crescer.redeSocial.mapper;

import br.com.cwi.crescer.redeSocial.controller.response.UsuarioResponse;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;

public class UsuarioMapper {
    public static UsuarioResponse toResponse(Usuario entity) {
        return UsuarioResponse.builder()
                .id(entity.getId())
                .nome(entity.getNome())
                .imagemPerfil(entity.getImagemPerfil())
                .ativo(entity.isAtivo())
                .build();
    }
}
