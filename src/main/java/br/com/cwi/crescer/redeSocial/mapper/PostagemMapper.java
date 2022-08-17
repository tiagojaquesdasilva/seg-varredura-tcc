package br.com.cwi.crescer.redeSocial.mapper;

import br.com.cwi.crescer.redeSocial.controller.request.PostagemRequest;
import br.com.cwi.crescer.redeSocial.controller.response.ListarPostagemResponse;
import br.com.cwi.crescer.redeSocial.domain.Postagem;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;

import java.util.stream.Collectors;

public class PostagemMapper {
    public static Postagem toEntity(Usuario usuarioId, PostagemRequest request) {
        return Postagem.builder()
                .usuario(usuarioId)
                .restricaoPostagem(request.getRestricaoPostagem())
                .build();
    }

    public static ListarPostagemResponse toResponse(Postagem postagem) {
        return ListarPostagemResponse.builder()
                .postagemId(postagem.getId())
                .usuarioId(postagem.getUsuario().getId())
                .dataPostagem(postagem.getDataPostagem())
                .imagemPostagem(postagem.getImagemPostagem())
                .textoPostagem(postagem.getTextoPostagem())
                .restricaoPostagem(postagem.getRestricaoPostagem().toString())
                .curtidas(postagem.getCurtidores().stream().map(curtiu -> UsuarioMapper.toResponse(curtiu)).collect(Collectors.toList()))
                .comentarios(postagem.getComentarios().stream().map(comentario -> ComentarioMapper.toResponse(comentario)).collect(Collectors.toList()))
                .build();
    }
}
