package br.ufrn.bancojml.transversal.regras;

/**
 *
 */
public interface RegraPermissao {
    public boolean ehAutorizado() throws RegraAutorizacaoException;
}
