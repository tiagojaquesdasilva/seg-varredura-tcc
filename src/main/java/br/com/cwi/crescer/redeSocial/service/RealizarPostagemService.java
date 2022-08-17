package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.request.PostagemRequest;
import br.com.cwi.crescer.redeSocial.domain.Postagem;
import br.com.cwi.crescer.redeSocial.mapper.PostagemMapper;
import br.com.cwi.crescer.redeSocial.repository.PostagemRepository;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import br.com.cwi.crescer.redeSocial.service.core.BuscarUsuarioService;
import br.com.cwi.crescer.redeSocial.service.core.NowService;
import br.com.cwi.crescer.redeSocial.service.core.ValidaRequestPostagem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RealizarPostagemService {

    @Autowired
    private NowService dataAtual;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private ValidaRequestPostagem validaRequestPostagem;

    @Autowired
    private PostagemRepository repository;


    @Transactional
    public void postar(Long usuarioLogadoId, PostagemRequest request) {

        Usuario usuarioPostagem = buscarUsuarioService.porId(usuarioLogadoId);

        Postagem postagem = PostagemMapper.toEntity(usuarioPostagem, request);

        String imagemPostagem = validaRequestPostagem.validarImagem(request);
        String textoPostagem = validaRequestPostagem.validarTexto(request);

        postagem.setImagemPostagem(imagemPostagem);
        postagem.setTextoPostagem(textoPostagem);
        postagem.setDataPostagem(dataAtual.getDate());

        repository.save(postagem);
    }
}
