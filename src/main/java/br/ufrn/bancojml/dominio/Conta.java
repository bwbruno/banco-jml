package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski
 */
public class Conta {
    private Montante saldo;
    private String numero;

    public Conta(Montante saldo, String numero) {
        this.saldo = saldo;
        this.numero = numero;
    }

    public Montante getSaldo() {
        return saldo;
    }

    public String getNumero() {
        return numero;
    }

    public Montante debitar(Montante montant) {
        this.saldo = saldo.subtrair(montant);
        return saldo;
    }
}
