package br.com.cwi.crescer.redeSocial.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class EditarPerfilRequest {

    @NotBlank
    private String nome;
    private String apelido;
    private String imagemPerfil;
}
