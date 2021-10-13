package br.com.fatec.DarkSkull.model.dominio.pedido;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter

@NoArgsConstructor

@Entity
@Table(name = "carrinho")
public class Carrinho extends EntidadeDominio {
}
