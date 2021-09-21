package br.com.fatec.DarkSkull.model.dominio.produto.acessorios;

import br.com.fatec.DarkSkull.model.dominio.produto.Produto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@Entity
@Table(name = "bone")
public class Bone extends Produto {

}
