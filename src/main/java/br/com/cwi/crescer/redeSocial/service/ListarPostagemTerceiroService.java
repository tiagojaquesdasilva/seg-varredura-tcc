package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.response.ListarPostagemResponse;
import br.com.cwi.crescer.redeSocial.domain.Restricao;
import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import br.com.cwi.crescer.redeSocial.mapper.PostagemMapper;
import br.com.cwi.crescer.redeSocial.repository.AmizadeRepository;
import br.com.cwi.crescer.redeSocial.repository.PostagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarPostagemTerceiroService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    @Autowired
    private PostagemRepository postagemRepository;

    public Page<ListarPostagemResponse> buscar(Long usuarioLogadoId, Long usuarioTerceiroId, Pageable pageable) {

        List amigosId = amizadeRepository.findByAmigoIdAndStatusAmizadeOrUsuarioIdAndStatusAmizade(usuarioLogadoId, StatusAmizade.AMIGO, usuarioLogadoId, StatusAmizade.AMIGO)
                .stream().map(amizade -> amizade.getUsuario().getId().equals(usuarioLogadoId) ? amizade.getAmigo().getId() : amizade.getUsuario().getId())
                .collect(Collectors.toList());

        List<Restricao> restricoes = new ArrayList<>();
        restricoes.add(Restricao.PUBLICA);

        if (amigosId.contains(usuarioTerceiroId))
            restricoes.add(Restricao.PRIVADA);

        return postagemRepository.findByUsuarioIdAndRestricaoPostagemIn(usuarioTerceiroId, restricoes, pageable)
                .map(postagem -> PostagemMapper.toResponse(postagem));

    }
}
