package br.ufrn.bancojml.dominio.fatura;

/**
 *
 */
public interface RepositorioFatura {

    public Fatura findBy(IdFatura id);
    public Fatura findBy(ReferenciaPrestacao ref);

    public void delete(IdFatura id);
    public void put(Fatura fatura);
}
