package br.com.cwi.crescer.redeSocial.service;

import br.com.cwi.crescer.redeSocial.controller.response.PedidoAmizadeResponse;
import br.com.cwi.crescer.redeSocial.domain.Amizade;
import br.com.cwi.crescer.redeSocial.domain.StatusAmizade;
import br.com.cwi.crescer.redeSocial.mapper.PedidoAmizadeMapper;
import br.com.cwi.crescer.redeSocial.repository.AmizadeRepository;
import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import br.com.cwi.crescer.redeSocial.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PedidosAmizadeService {
    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private AmizadeRepository amizadeRepository;

    public List<PedidoAmizadeResponse> buscar(Long usuarioId) {

        return amizadeRepository.findByAmigoIdAndStatusAmizade(usuarioId, StatusAmizade.PENDENTE)
                .stream().map(amizade -> PedidoAmizadeMapper.toResponse(amizade.getUsuario(), amizade.getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void pedir(Long usuarioLogadoId, Long possivel_amigoId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioLogadoId);
        Usuario amigo = buscarUsuarioService.porId(possivel_amigoId);
        List<Amizade> amizades = amizadeRepository
                .findByUsuarioIdAndAmigoIdOrUsuarioIdAndAmigoId(usuarioLogadoId, possivel_amigoId, usuarioLogadoId, possivel_amigoId);

        if (!amizades.isEmpty() || usuarioLogadoId.equals(possivel_amigoId))
            return;

        Amizade amizade = new Amizade();
        amizade.setUsuario(usuario);
        amizade.setAmigo(amigo);
        amizade.setStatusAmizade(StatusAmizade.PENDENTE);

        amizadeRepository.save(amizade);
    }
}
