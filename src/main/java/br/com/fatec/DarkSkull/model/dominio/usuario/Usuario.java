package br.com.fatec.DarkSkull.model.dominio.usuario;

import br.com.fatec.DarkSkull.model.EntidadeDominio;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "usuario")
public class Usuario extends EntidadeDominio {


    protected String email;
    protected String senha;
    protected String status;


}
