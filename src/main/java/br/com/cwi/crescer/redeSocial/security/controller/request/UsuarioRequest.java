package br.com.cwi.crescer.redeSocial.security.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    private String nome;

    @Email
    @NotNull
    private String email;

    @NotBlank
    private String senha;

    @NotBlank
    private LocalDate dataNascimento;

    private String apelido;
    private String imagemPerfil;
}
