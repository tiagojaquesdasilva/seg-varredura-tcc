package br.com.cwi.crescer.redeSocial.controller.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class UsuarioResponse {

    private Long id;
    private String nome;
    private String imagemPerfil;
    private boolean ativo;
}
