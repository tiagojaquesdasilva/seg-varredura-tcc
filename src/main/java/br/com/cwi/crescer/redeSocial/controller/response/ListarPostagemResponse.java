package br.com.cwi.crescer.redeSocial.controller.response;

import lombok.*;

import java.time.LocalDate;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class ListarPostagemResponse {
    private Long postagemId;
    private Long usuarioId;
    private LocalDate dataPostagem;
    private String imagemPostagem;
    private String textoPostagem;
    private String restricaoPostagem;
    private List<UsuarioResponse> curtidas;
    private List<ComentarioResponse> comentarios;
}
