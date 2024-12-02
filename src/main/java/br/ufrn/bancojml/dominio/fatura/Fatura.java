package br.ufrn.bancojml.dominio.fatura;

import br.ufrn.bancojml.dominio.Pagamento;

import java.util.Set;

/**
 * Created by jeremiegrodziski
 *
 */
public class Fatura {
    public ESTADO getEstado() {
        return ESTADO;
    }

    public static enum ESTADO {
        RECEBIDA, PAGA, RECONCILIADA, CONFORME
    }

    private ESTADO ESTADO = null;
    private IdFatura id;
    Set<LinhaFatura> linhas = null;


    public Fatura(IdFatura id, Set<LinhaFatura> linhas) {
        this.id = id;
        this.linhas = linhas;
        this.ESTADO = ESTADO.RECEBIDA;
    }

    public void pagar(Pagamento pagamento){

    }

    public void reconciliar(Prestacao prestacao){
        for (LinhaFatura linha : linhas) {
            if (linha != null){
                if (linha.getReferencePrestation() != null){
                    if (linha.getReferencePrestation().equals(prestacao.getReference())){
                        linha.reconcilier();
                    }
                }
            }
        }
    }


}
