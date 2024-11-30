package br.ufrn.bancojml.transverse.regles;

/**
 *
 */
public interface ReglePermission {
    public boolean estAutorise() throws AutorisationRegleException;
}
