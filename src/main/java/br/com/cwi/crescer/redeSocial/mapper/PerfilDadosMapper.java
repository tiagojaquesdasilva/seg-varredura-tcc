package br.com.cwi.crescer.redeSocial.mapper;

import br.com.cwi.crescer.redeSocial.controller.response.PerfilDadosResponse;
import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;

public class PerfilDadosMapper {

    public static PerfilDadosResponse toResponse(Usuario usuario, StatusAmizade status) {
        return PerfilDadosResponse.builder()
                .id(usuario.getId())
                .nome(usuario.getNome())
                .email(usuario.getEmail())
                .dataNascimento(usuario.getDataNascimento())
                .apelido(usuario.getApelido())
                .imagemPerfil(usuario.getImagemPerfil())
                .ativo(usuario.isAtivo())
                .statusAmizade(status)
                .build();
    }
}
