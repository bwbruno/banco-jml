package br.ufrn.bancojml.dominio.cambio;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 */
public class CotacaoDTO {
    public Currency moeda;
    public BigDecimal taxa; // moeda de referência relativa
    public Date validoDesde;
    public Date validoAte;

    public CotacaoDTO(Date diaDeValidade, BigDecimal taxa) {
        this.validoDesde = diaDeValidade;
        this.validoAte = new Date(diaDeValidade.getTime()+86400);//add one day
        this.taxa = taxa;
    }

}

