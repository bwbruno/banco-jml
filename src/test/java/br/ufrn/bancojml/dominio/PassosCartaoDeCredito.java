package br.ufrn.bancojml.dominio;

import br.ufrn.bancojml.dominio.cambio.CotacaoDTO;
import br.ufrn.bancojml.dominio.cambio.ServicoCambioMock;
import br.ufrn.bancojml.dominio.cambio.ServicoCambio;
import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 * Created by jeremiegrodziski
 */
public class PassosCartaoDeCredito {
    Titular titular;
    Cartao cartao;
    Banco banco;
    Conta conta;
    CaixaEletronico caixaEletronico;
    Retirada retirada;
    RuntimeException re;
    ServicoCambio servicoCambio;
    private Pagamento pagamento;

    @Given("o titular $nome $sobrenome possui o cartão nº $numeroCartao em $moedaCartao e um débito de $debito $moedaCartao associado à conta bancária $numeroConta com um saldo de $saldo € no banco $nomeBanco")
    public void givenOtitularPossuiOCartao(@Named("nome") String nome,
                                           @Named("sobrenome") String sobrenome,
                                           @Named("numeroCartao") String numeroCartao,
                                           @Named("moedaCartao") String moedaCartao,
                                           @Named("debito") Integer debito,
                                           @Named("numeroConta") String numeroConta,
                                           @Named("saldo") Integer saldo,
                                           @Named("nomeBanco") String nomeBanco) {
        titular = new Titular(nome, sobrenome);
        conta = new Conta(new Montante(saldo),numeroConta);
        long doisAnosDepois = System.currentTimeMillis()+(1000*60*60*24*365*2);
        cartao = new Cartao( new NumeroCartao(numeroCartao), new Date(doisAnosDepois),nome + " " + sobrenome, conta);
        banco = new Banco(nomeBanco);
        re = null;
        caixaEletronico = null;
        retirada = null;
    }

    @When("o titular realiza um saque de $retirada EUR no caixa eletrônico de $localisation")
    public void whenOtitularRealizaUmSaque(Integer retirada, String localisation) {
        caixaEletronico = new CaixaEletronico(localisation);
        try {
            this.retirada = caixaEletronico.retirar(cartao, new Montante(retirada));
        } catch (RuntimeException re){
            this.re = re;
        }
    }

    @Then("ele recebe $montant € em espécie")
    public void thenEleRecebeMontanteEmEspecie(Integer montant) {
        Montante valorEsperado = new Montante(montant);
        Assert.assertEquals(valorEsperado, retirada.getMontante());
    }

    @Then("o caixa eletrônico emite o comprovante resumido")
    public void thenOcaixaEletronicoEmiteOTicketDeResumo() {
        Assert.assertTrue(CaixaEletronico.isComprovanteEmitido());
    }

    @Then("ele recebe a mensagem \"saldo insuficiente\"")
    public void thenEleRecebeAMensagemSaldoInsuficiente() {
        Assert.assertTrue(re != null);
//        if (re != null) {
//            System.out.println(re.getMessage());
//        }
    }

    @Then("o saldo da conta é de $saldo €")
    public void thenOSaldoDaContaEDe(Integer saldo) {
        Montante saldoEsperado = new Montante(saldo);
        Assert.assertEquals(saldoEsperado, conta.getSaldo());
    }

    @Given("o servico ExchangeService esta inicializado com a taxa do dia $taux para EUR-CHF")
    @Pending
    public void givenOServicoExchangeServiceEstaInicializado(double taxa) {
        servicoCambio = ServicoCambioMock.getInstance(taxa);
    }

    @When("o titular realiza um pagamento no comerciante $commercant de $montant $devisePaiementString")
    public void whenOtitularRealizaUmPagamento(String commercant, Integer montant, String devisePaiementString) {
        Currency devisePaiement = Currency.getInstance(devisePaiementString);
        Montante montantPaiement = new Montante(new BigDecimal(montant), devisePaiement);

        CotacaoDTO quote = servicoCambio.buscarTaxaAtual(devisePaiement);
        Taxa taxa = new Taxa(quote.taxa, devisePaiement, Currency.getInstance("EUR"));

        MontanteEuro montantPaiementEuro = new MontanteEuro(taxa.converter(montantPaiement));
        TaxaAdministrativa frais = new TaxaAdministrativa(montantPaiementEuro);

        MontanteEuro montantTotal = montantPaiementEuro.adicionar(frais.getResultado());
        cartao.pagar(montantPaiementEuro, new Comerciante(commercant));
    }

    @Then("as taxas calculadas sao de 1.23 EUR")
    public void thenAsTaxasCalculadasSao() {
        // PENDENTE
    }

    @Then("o debito do cartao e de 83.23 EUR")
    @Pending
    public void thenODebitoDoCartaoEDe() {
        // PENDENTE
    }

}
