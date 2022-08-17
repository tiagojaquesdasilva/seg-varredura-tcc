package br.com.cwi.crescer.redeSocial.mapper;

import br.com.cwi.crescer.redeSocial.controller.response.PedidoAmizadeResponse;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;

public class PedidoAmizadeMapper {
    public static PedidoAmizadeResponse toResponse(Usuario usuario, Long pedidoId) {
        return PedidoAmizadeResponse.builder()
                .pedidoId(pedidoId)
                .id(usuario.getId())
                .nome(usuario.getNome())
                .imagemPerfil(usuario.getImagemPerfil())
                .build();
    }
}
