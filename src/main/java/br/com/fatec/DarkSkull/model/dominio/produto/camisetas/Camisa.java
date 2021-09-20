package br.com.fatec.DarkSkull.model.dominio.produto.camisetas;

import br.com.fatec.DarkSkull.dao.ProdutoRepositorio;
import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import br.com.fatec.DarkSkull.util.Tamanho;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@AllArgsConstructor

@Entity
@Table(name = "camisa")
public class Camisa extends Produto {



}
