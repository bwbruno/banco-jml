package br.ufrn.bancojml.transversal.regras;

/**
 * Created by jeremiegrodziski
 */
public interface RegraPermissao {
    public boolean ehAutorizado() throws RegraAutorizacaoException;
}
