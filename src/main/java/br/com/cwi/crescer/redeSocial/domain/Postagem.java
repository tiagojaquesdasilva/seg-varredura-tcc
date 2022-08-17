package br.com.cwi.crescer.redeSocial.domain;

import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Postagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private LocalDate dataPostagem;

    private String imagemPostagem;

    private String textoPostagem;

    @Enumerated(EnumType.STRING)
    private Restricao restricaoPostagem;

    @OneToMany
    @JoinTable(name = "curtida",
            joinColumns = @JoinColumn(name = "postagem_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Usuario> curtidores = new ArrayList<>();

    @OneToMany
    @JoinTable(name = "comentario",
            joinColumns = @JoinColumn(name = "postagem_id"),
            inverseJoinColumns = @JoinColumn(name = "usuario_id"))
    private List<Comentario> comentarios = new ArrayList<>();

    public void adicionarCurtida(Usuario curtida) {
        this.getCurtidores().add(curtida);
    }
}
