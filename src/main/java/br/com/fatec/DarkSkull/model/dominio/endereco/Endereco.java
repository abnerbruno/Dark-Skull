package br.com.fatec.DarkSkull.model.dominio.endereco;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public String longadouro;
    public String numero;
    public String cep;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cidade_id", referencedColumnName = "id")
    public Cidade cidade;



}
