package br.com.fatec.DarkSkull.model.dominio.cliente.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public String nome;
    public String bairro;
    public String descricao;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "estado_id", referencedColumnName = "id")
    public Estado estado;

}
