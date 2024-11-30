package br.ufrn.bancojml.domain;

import br.ufrn.bancojml.domain.facture.Facture;
import br.ufrn.bancojml.domain.facture.FactureRepository;
import br.ufrn.bancojml.domain.facture.IdFacture;
import br.ufrn.bancojml.domain.facture.LigneFacture;

import java.util.*;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 *
 */
public class FactureRepositoryMock {

    static {
        LigneFacture ligne = new LigneFacture();
        Set<LigneFacture> lignes = new LinkedHashSet<LigneFacture>();
        lignes.add(ligne);
        FACTURE = new Facture(new IdFacture(Long.valueOf("001")), lignes);
    }
    private static final Facture FACTURE;

    public static final FactureRepository getInstance() {
        FactureRepository repo = mock(FactureRepository.class);

        when(repo.findBy(any(IdFacture.class))).thenReturn(FACTURE);

        return repo;
    }

}
