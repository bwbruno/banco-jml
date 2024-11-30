package br.ufrn.bancojml.dominio;

import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Currency;

/**
 * Created by jeremiegrodziski
 */
public class Montante {
    private BigDecimal valor;
    private Currency moeda;
    public static final Montante ZERO = new Montante(BigDecimal.ZERO);

    public Montante(BigDecimal valor){
        this.valor = new BigDecimal(valor.intValue());
        moeda = Currency.getInstance("EUR");
    }

    public Montante(Integer valor){
        this(new BigDecimal(valor.intValue()));
    }

    public Montante(BigDecimal valor, Currency moeda) {
        this.valor = valor;
        this.moeda = moeda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public Currency getMoeda() {
        return moeda;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Montante montant = (Montante) o;

        if (moeda != null ? !moeda.equals(montant.moeda) : montant.moeda != null) return false;
        if (valor != null ? !valor.equals(montant.valor) : montant.valor != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = valor != null ? valor.hashCode() : 0;
        result = 31 * result + (moeda != null ? moeda.hashCode() : 0);
        return result;
    }

    public Montante subtrair(Montante montante) {
        return new Montante(this.valor.subtract(montante.getValor()), this.getMoeda());
    }

    public Montante adicionar(Montante montante) {
        Assert.state(montante.getMoeda().equals(this.getMoeda()),"A moeda do montante adicionado ("
                +montante.getMoeda()
                +") deve ser idêntica à moeda do objeto fonte "
                +this.getMoeda());
        return new Montante(this.getValor().add(montante.getValor()), this.getMoeda());
    }

    @Override
    public String toString() {
        return "{"+ valor + " " + moeda + '}';
    }
}
