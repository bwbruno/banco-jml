package br.ufrn.bancojml.transverse.regles;

/**
 *
 */
public interface RegleValidation {

    /**
     *
     * @return true if the rule is validated
     * @throws ValidationRegleException
     */
    public boolean valider() throws IllegalStateException;

}
