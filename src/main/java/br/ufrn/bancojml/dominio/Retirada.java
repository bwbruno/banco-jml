package br.ufrn.bancojml.dominio;

import java.util.Date;

/**
 * Created by jeremiegrodziski
 */
public class Retirada {
    private Cartao cartaoUtilizado;
    private Montante montante;
    private Date data;

    public Retirada(Cartao cartaoUtilizado, Montante montante) {
        this.cartaoUtilizado = cartaoUtilizado;
        this.montante = montante;
        data = new Date();
    }

    public Cartao getCartaoUtilizado() {
        return cartaoUtilizado;
    }

    public Montante getMontante() {
        return montante;
    }

    public Date getData() {
        return data;
    }
}
