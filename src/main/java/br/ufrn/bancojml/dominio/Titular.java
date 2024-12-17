package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski, brunowagner
 */
//@ non_null_by_default
public class Titular {

    //@ public invariant !nome.isEmpty();
    //@ public invariant !sobrenome.isEmpty();
    //@ public invariant !cpf.isEmpty() && cpf.length() == 11; // Supondo que o CPF deve ter 11 dígitos

    //@ spec_public
    private String nome;
    //@ spec_public
    private String sobrenome;
    //@ spec_public
    private String cpf;

    //@ requires !nome.isEmpty();
    //@ requires !sobrenome.isEmpty();
    //@ requires !cpf.isEmpty();
    //@ requires cpf.length() == 11;
    //@ ensures this.nome.equals(nome);
    //@ ensures this.sobrenome.equals(sobrenome);
    //@ ensures this.cpf.equals(cpf);
    public Titular(String nome, String sobrenome, String cpf) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.cpf = cpf;
    }

    //@ ensures \result != null;
    //@ ensures !\result.isEmpty();
    //@ pure
    public String getNome() {
        return nome;
    }

    //@ ensures \result != null;
    //@ ensures !\result.isEmpty();
    //@ pure
    public String getSobrenome() {
        return sobrenome;
    }

    //@ ensures \result != null;
    //@ ensures !\result.isEmpty();
    //@ ensures \result.length() == 11;
    //@ pure
    public String getCpf() {
        return cpf;
    }


}
