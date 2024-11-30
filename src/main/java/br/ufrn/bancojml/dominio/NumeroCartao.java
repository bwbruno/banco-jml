package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski
 */
public class NumeroCartao {
    private String valor;
    public NumeroCartao(String numeroCartao) {
        valor = numeroCartao;
    }

    @Override
    public String toString() {
        return valor;
    }
}
