package br.ufrn.bancojml.dominio;

import org.springframework.util.Assert;



/**
 * Created by jeremiegrodziski
 */
public class TaxaComposta {
    private Taxa taxaFonteIntermediaria;
    private Taxa taxaIntermediariaDestino;

    public TaxaComposta(Taxa taxaFonteIntermediaria, Taxa taxaIntermediariaDestino) {
        this.taxaFonteIntermediaria = taxaFonteIntermediaria;
        this.taxaIntermediariaDestino = taxaIntermediariaDestino;
        Assert.state(taxaFonteIntermediaria.getDestino().equals(taxaIntermediariaDestino.getFonte()));
    }

    public Montante converter(Montante montanteFonte){
        Assert.state(montanteFonte.getMoeda().equals(taxaFonteIntermediaria.getFonte()));
        Montante montantIntermediaire = taxaFonteIntermediaria.converter(montanteFonte);
        Montante montantcible = taxaIntermediariaDestino.converter(montantIntermediaire);
        return montantcible;
    }
}
