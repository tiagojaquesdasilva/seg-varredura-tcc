package br.com.cwi.crescer.redeSocial.controller.response;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PedidoAmizadeResponse {

    private Long pedidoId;
    private Long id;
    private String nome;
    private String imagemPerfil;
}
