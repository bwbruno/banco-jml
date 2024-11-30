package br.ufrn.bancojml.dominio;

import br.ufrn.bancojml.dominio.fatura.Fatura;
import br.ufrn.bancojml.dominio.fatura.RepositorioFatura;
import br.ufrn.bancojml.dominio.fatura.IdFatura;
import br.ufrn.bancojml.dominio.fatura.Prestacao;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import org.springframework.util.Assert;

/**
 *
 */
public class FactureSteps {
    RepositorioFatura repositorioFatura = FactureRepositoryMock.getInstance();
    private Fatura fatura;

    @Given("la facture $idFacture existe dans le système")
    public void givenLaFactureExisteDansLeSystème(String idFacture) {
        fatura = repositorioFatura.findBy(new IdFatura(Long.valueOf(idFacture)));
    }

    @When("je reconcilie la prestation type $typePresta ref $ref avec la facture")
    public void whenJeReconcilieLaPrestationtypeDépannageAutoRefABCAvecLaFacture001(String typePresta, String ref) {
        fatura.reconciliar(new Prestacao(typePresta, ref));
    }

    @Then("l'état de la facture est RECONCILIADA")
    public void thenLétatDeLaFactureEstRECONCILIEE() {
        Assert.state(fatura.getEstado().equals(Fatura.ESTADO.RECONCILIADA));
    }

}
