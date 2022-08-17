package br.com.cwi.crescer.redeSocial.controller;

import br.com.cwi.crescer.redeSocial.controller.response.ListarPostagemResponse;
import br.com.cwi.crescer.redeSocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.redeSocial.service.ListarPostagemTerceiroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import static br.com.cwi.crescer.redeSocial.security.domain.Funcao.Nomes.USUARIO;

@RestController
@RequestMapping("/terceiros")
public class PerfilUsuarioTerceiroController {

    @Autowired
    private ListarPostagemTerceiroService listarPostagemTerceiroService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Secured(USUARIO)
    @GetMapping("/{usuarioTerceiroId}/listar_postagem")
    public Page<ListarPostagemResponse> listar(@PathVariable Long usuarioTerceiroId, Pageable pageable) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        return listarPostagemTerceiroService.buscar(usuarioLogadoId, usuarioTerceiroId, pageable);
    }
}
