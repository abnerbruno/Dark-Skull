package br.com.fatec.DarkSkull.model.dominio.pedido;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import br.com.fatec.DarkSkull.model.dominio.usuario.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter

@NoArgsConstructor

@Entity
@Table(name = "pedido")
public class Pedido extends EntidadeDominio {

    @GeneratedValue(strategy = GenerationType.AUTO)
    Long numeroPedido;

    String nomeProduto;

    @JsonFormat(pattern = "dd.MM.YYYY")
    @CreationTimestamp
    Date dataVenda;

    Long ValorTotal;
    int quantidade;
    String tamanho;
    String Status;

    @OneToOne
    Usuario usuario;

}
