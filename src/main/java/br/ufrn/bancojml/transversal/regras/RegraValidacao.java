package br.ufrn.bancojml.transversal.regras;

/**
 *
 */
public interface RegraValidacao {

    /**
     *
     * @return true se a regra for validada
     * @throws RegraValidacaoException
     */
    public boolean validar() throws IllegalStateException;

}
