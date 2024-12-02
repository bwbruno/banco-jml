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
 * Created by jeremiegrodziski
 */
public class PassosFatura {
    RepositorioFatura repositorioFatura = RepositorioFaturaMock.getInstance();
    private Fatura fatura;

    @Given("a fatura $idFatura existe no sistema")
    public void dadoAFaturaExisteNoSistema(String idFatura) {
        fatura = repositorioFatura.findBy(new IdFatura(Long.valueOf(idFatura)));
    }

    @When("eu reconciliar a prestação do tipo $tipoPresta ref $ref com a fatura")
    public void quandoEuReconcilioAPrestacaoTipoDanoAutoRefABCAcomAFatura001(String tipoPresta, String ref) {
        fatura.reconciliar(new Prestacao(tipoPresta, ref));
    }

    @Then("o estado da fatura é RECONCILIADA")
    public void entaoOEstadoDaFaturaEstáRECONCILIADA() {
        Assert.state(fatura.getEstado().equals(Fatura.ESTADO.RECONCILIADA));
    }
}
