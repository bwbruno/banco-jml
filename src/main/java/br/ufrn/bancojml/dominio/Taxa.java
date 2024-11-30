package br.ufrn.bancojml.dominio;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by jeremiegrodziski
 */
public class Taxa {
    private BigDecimal taxa;
    private Currency fonte;
    private Currency destino;

    public Taxa(BigDecimal taxa, Currency fonte, Currency destino) {
        this.taxa = taxa;
        this.fonte = fonte;
        this.destino = destino;
    }

    public Montante converter(Montante montante){
        Assert.state(montante.getMoeda().equals(fonte));
        return new Montante(montante.getValor().multiply(taxa), destino);
    }

    public BigDecimal getTaxa() {
        return taxa;
    }

    public Currency getFonte() {
        return fonte;
    }

    public Currency getDestino() {
        return destino;
    }
}


