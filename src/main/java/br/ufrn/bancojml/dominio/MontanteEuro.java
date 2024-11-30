package br.ufrn.bancojml.dominio;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by jeremiegrodziski
 */
public class MontanteEuro extends Montante {

    public MontanteEuro(BigDecimal valor) {
        super(valor, Currency.getInstance("EUR"));
    }

    public MontanteEuro(Montante montante){
        super(montante.getValor(), montante.getMoeda());
        Assert.state(montante.getMoeda().equals(Currency.getInstance("EUR")));
    }

    public MontanteEuro adicionar(MontanteEuro montantEuro) {
        return (MontanteEuro)super.adicionar((Montante)montantEuro);
    }
}
