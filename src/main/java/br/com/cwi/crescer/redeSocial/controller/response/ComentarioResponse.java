package br.com.cwi.crescer.redeSocial.controller.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ComentarioResponse {

    private Long comentarioId;
    private String comentario;
    private UsuarioResponse usuario;
}
