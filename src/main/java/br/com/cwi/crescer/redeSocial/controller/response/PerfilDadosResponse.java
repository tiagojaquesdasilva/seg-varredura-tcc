package br.com.cwi.crescer.redeSocial.controller.response;

import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import lombok.*;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class PerfilDadosResponse {

    private long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private String apelido;
    private String imagemPerfil;
    private boolean ativo;
    private StatusAmizade statusAmizade;
}
