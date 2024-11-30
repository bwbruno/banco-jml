package br.ufrn.bancojml.dominio;


import br.ufrn.bancojml.transversal.regras.RegraPermissao;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by jeremiegrodziski
 */
public class Cartao implements Entidade {
    private NumeroCartao numeroCartao;
    private Date dataExpiracao;
    private String nome;
    private Conta conta;
    private CodigoSecreto codigoSecreto;
    private EstadoCartao estadoCartao;

    public Cartao(NumeroCartao numeroCartao, Date dataExpiracao, String nomePortador, Conta conta) {
        this.numeroCartao = numeroCartao;
        this.dataExpiracao = dataExpiracao;
        this.nome = nomePortador;
        this.conta = conta;
        this.estadoCartao = EstadoCartao.VALIDO;
    }

    public void expirar(Date dataDoDia){
        Assert.state(estadoCartao == EstadoCartao.VALIDO);
        if (dataDoDia.after(dataExpiracao))
            estadoCartao = EstadoCartao.EXPIRADO;
    }

    public void bloquear(){
        Assert.state(estadoCartao == EstadoCartao.VALIDO);
        this.estadoCartao = EstadoCartao.BLOQUEADO;
    }

    public void pagar(Montante montante, Comerciante comerciante){
        Assert.state(estadoCartao == EstadoCartao.VALIDO);
        conta.debitar(montante);
        comerciante.creditar(montante);
    }

    public void debitarImediatamente(Montante montante){
        Assert.state(estadoCartao == EstadoCartao.VALIDO);
        new RegraSaldoInsuficiente(conta.getSaldo(), montante).ehAutorizado();
        conta.debitar(montante);
    }

    class RegraSaldoInsuficiente implements RegraPermissao {
        private Montante saldoConta;
        private Montante montanteRetirada;

        RegraSaldoInsuficiente(Montante saldoConta, Montante montanteRetirada) {
            this.saldoConta = saldoConta;
            this.montanteRetirada = montanteRetirada;
        }

        @Override
        public boolean ehAutorizado() {
            boolean ehAutorizado = montanteRetirada.getValor().compareTo(saldoConta.getValor()) < 0;
            if (!ehAutorizado) throw new RuntimeException("O montante da retirada "
                    + montanteRetirada.getValor()
                    +" deve ser inferior ao saldo da conta "
                    + saldoConta.getValor());
            return ehAutorizado;
        }
    }

    public NumeroCartao getNumeroCartao() {
        return numeroCartao;
    }

    public Date getDataExpiracao() {
        return dataExpiracao;
    }

    public String getNome() {
        return nome;
    }

    public Conta getConta() {
        return conta;
    }
}
