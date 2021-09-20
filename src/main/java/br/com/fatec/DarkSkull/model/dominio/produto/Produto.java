package br.com.fatec.DarkSkull.model.dominio.produto;

import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter

@NoArgsConstructor
@AllArgsConstructor

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Produto {

    @Id
    private long id;
    private String nome;
    private long valor;
    private Tamanho tamanho;
    private int quantidade;
    private String descricao;


}
