package br.com.fatec.DarkSkull.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pessoa {

    private final String nome;
    private final int idade;

    public Pessoa(String nome, int idade) {
        this.nome = nome;
        this.idade = idade;
    }

    @Override
    public String toString() {
        return "Pessoa{" +
                "nome='" + nome + '\'' +
                ", idade=" + idade +
                '}';
    }
}
