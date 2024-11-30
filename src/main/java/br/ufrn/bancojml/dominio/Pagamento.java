package br.ufrn.bancojml.dominio;

import java.util.Date;

/**
 * Created by jeremiegrodziski
 */
public class Pagamento {
    private Montante montante;
    private Cartao cartao;
    private Date data;

    public Pagamento(Montante montante, Cartao cartao, Date data) {
        this.montante = montante;
        this.cartao = cartao;
        this.data = data;
    }

    public Montante getMontante() {
        return montante;
    }

    public Cartao getCartao() {
        return cartao;
    }

    public Date getData() {
        return data;
    }
}
