package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski
 */
public class Comerciante {
    private Montante saldo = br.ufrn.bancojml.dominio.Montante.ZERO;
    private String nomeComerciante;

    public Comerciante(String nomeComerciante) {
        this.nomeComerciante = nomeComerciante;
    }

    public void creditar(Montante montante) {
        saldo.adicionar(montante);
    }
}
