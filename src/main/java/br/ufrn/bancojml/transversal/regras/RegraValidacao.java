package br.ufrn.bancojml.transversal.regras;

/**
 * Created by jeremiegrodziski
 */
public interface RegraValidacao {

    /**
     *
     * @return true se a regra for validada
     * @throws RegraValidacaoException
     */
    public boolean validar() throws IllegalStateException;

}
