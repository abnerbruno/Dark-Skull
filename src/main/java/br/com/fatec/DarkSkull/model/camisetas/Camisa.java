package br.com.fatec.DarkSkull.model.camisetas;

import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.*;

//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

//@Entity
public class Camisa {

//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private long valor;
    private Tamanho tamanho;
    private int quantidade;
    private String descricao;

}
