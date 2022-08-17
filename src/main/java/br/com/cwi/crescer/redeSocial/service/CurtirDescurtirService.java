package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.domain.Postagem;
import br.com.cwi.crescer.redeSocial.repository.PostagemRepository;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import br.com.cwi.crescer.redeSocial.service.core.BuscarPostagemService;
import br.com.cwi.crescer.redeSocial.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CurtirDescurtirService {
    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private BuscarPostagemService buscarPostagemService;

    @Transactional
    public void setarCurtida(Long usuarioLogadoId, Long postagemId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioLogadoId);
        Postagem postagem = buscarPostagemService.porId(postagemId);

        boolean estadoCurtida = postagem.getCurtidores().stream().noneMatch(curtidores -> curtidores.getId().equals(usuarioLogadoId));

        if (estadoCurtida) {
            postagem.adicionarCurtida(usuario);
            postagemRepository.save(postagem);
        } else {
            List<Usuario> tirandoCurtida = postagem.getCurtidores()
                    .stream().filter(user -> user.getId() != usuarioLogadoId).collect(Collectors.toList());
            postagem.setCurtidores(tirandoCurtida);
            postagemRepository.save(postagem);
        }
    }
}
