package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.response.UsuarioResponse;
import br.com.cwi.crescer.redeSocial.domain.Amizade;
import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import br.com.cwi.crescer.redeSocial.mapper.UsuarioMapper;
import br.com.cwi.crescer.redeSocial.repository.AmizadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AmigosUsuarioService {

    @Autowired
    private AmizadeRepository amizadeRepository;

    public List<UsuarioResponse> buscar(Long usuarioId) {

        List amigos = amizadeRepository.findByAmigoIdAndStatusAmizadeOrUsuarioIdAndStatusAmizade(usuarioId, StatusAmizade.AMIGO, usuarioId, StatusAmizade.AMIGO)
                .stream().map(amizade -> UsuarioMapper.toResponse(amizade.getUsuario().getId().equals(usuarioId)
                        ? amizade.getAmigo() : amizade.getUsuario()))
                .collect(Collectors.toList());

        return amigos;
    }

    @Transactional
    public void alterarAmizade(Long usuarioLogadoId, Long amizadeId, boolean aceitar) {
        Optional<Amizade> amizadeOptional = amizadeRepository.findById(amizadeId);

        if (amizadeOptional.isEmpty())
            return;

        Amizade amizade = amizadeOptional.get();

        if (amizade.getAmigo().getId().equals(usuarioLogadoId)) {
            if (aceitar) {
                amizade.setStatusAmizade(StatusAmizade.AMIGO);
                amizadeRepository.save(amizade);
            } else {
                amizadeRepository.delete(amizade);
            }
        }
    }
}
