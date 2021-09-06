package br.com.fatec.DarkSkull.model.registros;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.sql.Timestamp;

@Getter
@Setter

@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String cpf;
    private Timestamp dataNascimento;
    private String email;
    private String genero;
    private String telefone;


    public Cliente(String nome, Timestamp dataNascimento, String email) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Cliente(Long id, String nome, Timestamp timestamp, String email) {
        this.id = id;
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.email = email;
    }

    public Cliente(Long id, String nome, String email) {
        this.id = id;
        this.nome = nome;
        this.email = email;
    }
}
