package br.com.cwi.crescer.redeSocial.domain;

import br.com.cwi.crescer.redeSocial.security.domain.Usuario;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
@ToString(of = "id")
public class Comentario {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "postagem_id")
    private Postagem postagem;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private String comentario;
}
