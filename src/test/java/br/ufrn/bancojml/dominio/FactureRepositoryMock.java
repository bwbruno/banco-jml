package br.ufrn.bancojml.dominio;

import br.ufrn.bancojml.dominio.fatura.Fatura;
import br.ufrn.bancojml.dominio.fatura.RepositorioFatura;
import br.ufrn.bancojml.dominio.fatura.IdFatura;
import br.ufrn.bancojml.dominio.fatura.LinhaFatura;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class FactureRepositoryMock {

    static {
        LinhaFatura ligne = new LinhaFatura();
        Set<LinhaFatura> lignes = new LinkedHashSet<LinhaFatura>();
        lignes.add(ligne);
        FATURA = new Fatura(new IdFatura(Long.valueOf("001")), lignes);
    }
    private static final Fatura FATURA;

    public static final RepositorioFatura getInstance() {
        RepositorioFatura repo = mock(RepositorioFatura.class);

        when(repo.findBy(any(IdFatura.class))).thenReturn(FATURA);

        return repo;
    }

}
