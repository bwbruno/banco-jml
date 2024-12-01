package br.ufrn.bancojml.dominio;

import br.ufrn.bancojml.dominio.cambio.ExchangeServiceMock;
import br.ufrn.bancojml.dominio.cambio.ServicoCambio;
import br.ufrn.bancojml.dominio.cambio.CotacaoDTO;
import junit.framework.Assert;
import org.jbehave.core.annotations.*;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.Date;

/**
 *
 */
public class CreditCardSteps {
    Titular titular;
    Cartao carte;
    Banco banque;
    Conta compte;
    CaixaEletronico dab;
    Retirada retrait;
    RuntimeException re;

    ServicoCambio exchange;
    private Pagamento paiement;

    @Given("le porteur $prenom $nom possède la carte no $noCarte en $deviseCarte et un débit de $debit $deviseCarte associé au compte bancaire $noCompte avec un solde de $solde € à la banque $nomBanque")
    public void givenLePorteurPossedeLaCarte(@Named("prenom") String prenom,
                                             @Named("nom") String nom,
                                             @Named("noCarte") String noCarte,
                                             @Named("deviseCarte") String deviseCarte,
                                             @Named("debit") Integer debit,
                                             @Named("noCompte") String noCompte,
                                             @Named("solde") Integer solde,
                                             @Named("nomBanque") String nomBanque) {
        titular = new Titular(prenom, nom);
        compte = new Conta(new Montante(solde),noCompte);
        long dansDeuxAns = System.currentTimeMillis()+(1000*60*60*24*365*2);
        carte = new Cartao( new NumeroCartao(noCarte), new Date(dansDeuxAns),prenom + " " + nom, compte);
        banque = new Banco(nomBanque);
        re = null;
        dab = null;
        retrait = null;
    }

    @Given("le service ExchangeService est initialis\u00E9 avec le taux du jour $taux pour EUR-CHF")
    @Pending
    public void givenLeServiceExchangeServiceEstInitialise(double taux) {
        exchange = ExchangeServiceMock.getInstance(taux);
    }


    @When("le porteur effectue un retrait de $retrait EUR au DAB de $localisation")
    public void whenLePorteurEffectueUnRetrait(Integer retrait, String localisation) {
        dab = new CaixaEletronico(localisation);
        try {
            this.retrait = dab.retirar(carte, new Montante(retrait));
        } catch (RuntimeException re){
            this.re = re;
        }
    }

    @Then("il obtient $montant € en espèces")
    public void thenIlObtientMontantEnEspeces(Integer montant) {
        Montante valeurAttendu = new Montante(montant);
        Assert.assertEquals(valeurAttendu, retrait.getMontante());
    }

    @Then("le solde du compte est de $solde €")
    public void thenLeSoldeDuCompteEstDe(Integer solde) {
        Montante soldeAttendu = new Montante(solde);
        Assert.assertEquals(soldeAttendu, compte.getSaldo());
    }

    @Then("il obtient le message \"solde insuffisant\"")
    public void thenIlObtientLeMessagesoldeInsuffisant() {
        //Assert.assertTrue(re != null);
        System.out.println(re.getMessage());
    }

    @When("le porteur effectue un paiement chez le commerçant $commercant de $montant $devisePaiementString")

    public void whenLePorteurEffectueUnPaiementDe(String commercant, Integer montant, String devisePaiementString) {
        Currency devisePaiement = Currency.getInstance(devisePaiementString);
        Montante montantPaiement = new Montante(new BigDecimal(montant), devisePaiement);

        CotacaoDTO quote = exchange.buscarTaxaAtual(devisePaiement);
        Taxa taxa = new Taxa(quote.taxa, devisePaiement, Currency.getInstance("EUR"));

        MontanteEuro montantPaiementEuro = new MontanteEuro(taxa.converter(montantPaiement));
        TaxaAdministrativa frais = new TaxaAdministrativa(montantPaiementEuro);

        MontanteEuro montantTotal = montantPaiementEuro.adicionar(frais.getResultado());
        carte.pagar(montantPaiementEuro, new Comerciante(commercant));
    }

    @Then("les frais calculés sont de 1.23 EUR")
    public void thenLesFraisCalculeSont() {
        // EN SUSPENS
    }

    @Then("le débit de la carte est de 83.23 EUR")
    @Pending
    public void thenLeDebitDeLaCarteEstDe() {
        // EN SUSPENS
    }

    @Then("le DAB émet le ticket récapitulatif")
    public void thenLeDABEmetLeTicketRecapitulatif() {
        Assert.assertTrue(CaixaEletronico.isComprovanteEmitido());
    }



}
