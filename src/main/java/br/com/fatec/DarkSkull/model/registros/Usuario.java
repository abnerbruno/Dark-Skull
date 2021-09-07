package br.com.fatec.DarkSkull.model.registros;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    protected String email;
    protected String senha;
    protected String status;

    final protected Timestamp dataCadastro = new Timestamp(System.currentTimeMillis());

}
