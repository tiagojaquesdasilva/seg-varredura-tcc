package br.com.cwi.crescer.redeSocial.controller;

import br.com.cwi.crescer.redeSocial.controller.request.AmizadeRequest;
import br.com.cwi.crescer.redeSocial.controller.request.ComentarioPostagemRequest;
import br.com.cwi.crescer.redeSocial.controller.request.EditarPerfilRequest;
import br.com.cwi.crescer.redeSocial.controller.request.PostagemRequest;
import br.com.cwi.crescer.redeSocial.controller.response.ListarPostagemResponse;
import br.com.cwi.crescer.redeSocial.controller.response.PedidoAmizadeResponse;
import br.com.cwi.crescer.redeSocial.controller.response.UsuarioResponse;
import br.com.cwi.crescer.redeSocial.controller.response.PerfilDadosResponse;
import br.com.cwi.crescer.redeSocial.security.service.UsuarioAutenticadoService;
import br.com.cwi.crescer.redeSocial.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static br.com.cwi.crescer.redeSocial.security.domain.Funcao.Nomes.USUARIO;

@RestController
@RequestMapping("/usuario")
public class PerfilUsuarioController {
    @Autowired
    private PerfilDadosService perfilDadosService;

    @Autowired
    private AmigosUsuarioService amigosUsuarioService;

    @Autowired
    private PedidosAmizadeService pedidosAmizadeService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private RealizarPostagemService realizarPostagemService;

    @Autowired
    private ListarPostagemService listarPostagemService;

    @Autowired
    private CurtirDescurtirService curtirDescurtirService;

    @Autowired
    private ComentarPostagemService comentarPostagemService;

    @Autowired
    private UsuarioService usuarioService;

    @Secured(USUARIO)
    @GetMapping("/info")
    public PerfilDadosResponse buscarDados() {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        return perfilDadosService.buscar(usuarioLogadoId);
    }

    @Secured(USUARIO)
    @GetMapping("/amigos")
    public List<UsuarioResponse> buscarAmigos() {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        return amigosUsuarioService.buscar(usuarioLogadoId);
    }

    @Secured(USUARIO)
    @GetMapping("/buscar")
    public List<UsuarioResponse> buscarUsuarios(@RequestParam String filtro) {
        return usuarioService.buscarUsuarios(filtro);
    }

    @Secured(USUARIO)
    @GetMapping("/pedidos_amizade")
    public List<PedidoAmizadeResponse> buscarPedidos() {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        return pedidosAmizadeService.buscar(usuarioLogadoId);
    }

    @Secured(USUARIO)
    @PostMapping("/postar")
    public void postar(@Valid @RequestBody PostagemRequest request) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        realizarPostagemService.postar(usuarioLogadoId, request);
    }

    @Secured(USUARIO)
    @GetMapping("/listar_postagem")
    public Page<ListarPostagemResponse> listar(Pageable pageable) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        return listarPostagemService.buscar(usuarioLogadoId, pageable);
    }

    @Secured(USUARIO)
    @PostMapping("/{postagemId}/curtir_descurtir")
    public void curtirDescurtir(@PathVariable Long postagemId) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        curtirDescurtirService.setarCurtida(usuarioLogadoId, postagemId);
    }

    @Secured(USUARIO)
    @PostMapping("/{postagemId}/comentar")
    public void comentarPostagem(@PathVariable Long postagemId, @Valid @RequestBody ComentarioPostagemRequest request) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        comentarPostagemService.comentar(usuarioLogadoId, postagemId, request);
    }

    @Secured(USUARIO)
    @PutMapping("/{amizadeId}/alterar_amizade")
    public void alterarAmizade(@PathVariable Long amizadeId, @Valid @RequestBody AmizadeRequest request) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        amigosUsuarioService.alterarAmizade(usuarioLogadoId, amizadeId, request.isAceitar());
    }

    @Secured(USUARIO)
    @PostMapping("/{possivel_amigoId}/pedido")
    public void perdirAmizade(@PathVariable Long possivel_amigoId) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        pedidosAmizadeService.pedir(usuarioLogadoId, possivel_amigoId);
    }

    @Secured(USUARIO)
    @PutMapping("/editar_perfil")
    public void editarPerfil(@Valid @RequestBody EditarPerfilRequest request) {
        Long usuarioLogadoId = usuarioAutenticadoService.getId();
        perfilDadosService.editar(usuarioLogadoId, request);
    }
}
