package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski
 */
public class Titular {
    private String nome;
    private String sobrenome;

    public Titular(String nome, String sobrenome) {
        this.nome = nome;
        this.sobrenome = sobrenome;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }
}
