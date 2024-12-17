package br.ufrn.bancojml.dominio;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by jeremiegrodziski, brunowagner
 */
public class Banco {
    private String nome;
    private String codigo;
    private Set<Conta> contas;

    public Banco(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
        this.contas = new HashSet<>();
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Set<Conta> getContas() {
        return contas;
    }

    public void adicionarConta(Conta conta) {
        contas.add(conta);
    }

    public void removerConta(Conta conta) {
        contas.remove(conta);
    }
}
