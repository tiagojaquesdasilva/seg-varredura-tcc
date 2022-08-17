package br.com.cwi.crescer.redeSocial.controller.request;

import br.com.cwi.crescer.redeSocial.domain.Restricao;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;

@Getter
@Setter
public class PostagemRequest {

    private String imagemPostagem;
    private String textoPostagem;

    @NotNull
    @Enumerated(EnumType.STRING)
    private Restricao restricaoPostagem;

}
