package br.ufrn.bancojml.dominio.cambio;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;

/**
 * Created by jeremiegrodziski
 *
 */
public interface ServicoCambio {
    Currency MOEDA_REF = Currency.getInstance("EUR");
    BigDecimal TAXA = BigDecimal.ONE;

    List<CotacaoDTO> buscarTaxa(Currency moeda);
    CotacaoDTO buscarTaxaAtual(Currency moeda);
}