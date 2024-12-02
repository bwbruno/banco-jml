package br.ufrn.bancojml.dominio.fatura;

/**
 * Created by jeremiegrodziski
 */
public class Prestacao {
    private ReferenciaPrestacao reference;
    private TipoPrestacao tipoPrestacao;


    public Prestacao(String typePresta, String ref) {
        this.reference = new ReferenciaPrestacao(ref);
        this.tipoPrestacao = new TipoPrestacao(typePresta);
    }

    public ReferenciaPrestacao getReference() {
        return reference;
    }

    public TipoPrestacao getTipoPrestacao() {
        return tipoPrestacao;
    }
}
