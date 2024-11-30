package br.ufrn.bancojml.dominio;

import br.ufrn.bancojml.transversal.RegraCalculo;

import java.math.BigDecimal;

/**
 * calculated_fee = amount in EUR * 1.5%, when calculated_fee < 1 EUR then return 1 EUR
 * taxa_calculada = valor em EUR * 1,5%, quando taxa_calculada < 1 EUR, retorna 1 EUR
 */
public class TaxaAdministrativa implements RegraCalculo<Montante> {
    private MontanteEuro montanteEuro;
    public static final BigDecimal TAXA_ADMINISTRATIVA = new BigDecimal(0.015);

    public TaxaAdministrativa(MontanteEuro montanteEuro) {
        this.montanteEuro = montanteEuro;
    }

    @Override
    public MontanteEuro getResultado() {
        BigDecimal taxaCalculada = montanteEuro.getValor().multiply(TAXA_ADMINISTRATIVA);
        if (taxaCalculada.compareTo(BigDecimal.ONE) < 0) {
            return new MontanteEuro(BigDecimal.ONE);
        }
        return new MontanteEuro(taxaCalculada);
    }
}
