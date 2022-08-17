package br.com.cwi.crescer.redeSocial.mapper;

import br.com.cwi.crescer.redeSocial.controller.response.ComentarioResponse;
import br.com.cwi.crescer.redeSocial.domain.Comentario;

public class ComentarioMapper {

    public static ComentarioResponse toResponse(Comentario comentario) {
        return ComentarioResponse.builder()
                .comentarioId(comentario.getId())
                .comentario(comentario.getComentario())
                .usuario(UsuarioMapper.toResponse(comentario.getUsuario()))
                .build();
    }
}
