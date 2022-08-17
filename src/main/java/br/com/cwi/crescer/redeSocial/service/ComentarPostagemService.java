package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.request.ComentarioPostagemRequest;
import br.com.cwi.crescer.redeSocial.domain.Comentario;
import br.com.cwi.crescer.redeSocial.domain.Postagem;
import br.com.cwi.crescer.redeSocial.repository.ComentarioRepository;
import br.com.cwi.crescer.redeSocial.repository.PostagemRepository;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import br.com.cwi.crescer.redeSocial.service.core.BuscarPostagemService;
import br.com.cwi.crescer.redeSocial.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComentarPostagemService {

    @Autowired
    private PostagemRepository postagemRepository;
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;
    @Autowired
    private BuscarPostagemService buscarPostagemService;
    @Autowired
    private ComentarioRepository comentarioRepository;

    @Transactional
    public void comentar(Long usuarioLogadoId, Long postagemId, ComentarioPostagemRequest request) {

        Usuario usuario = buscarUsuarioService.porId(usuarioLogadoId);
        Postagem postagem = buscarPostagemService.porId(postagemId);

        Comentario comentario = new Comentario();
        comentario.setComentario(request.getComentario());
        comentario.setUsuario(usuario);
        comentario.setPostagem(postagem);

        comentarioRepository.save(comentario);
    }
}
