package br.com.fatec.DarkSkull.model.dominio.produto.camisetas;

import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.*;

import javax.persistence.*;

@Getter
@Setter

@AllArgsConstructor

@Entity
@Table(name = "blusa")
public class Blusa extends Produto {

}
