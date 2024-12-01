package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski
 */
public class CaixaEletronico {
    private Localizacao localizacao;
    private static boolean ticketEmitido = true;

    public CaixaEletronico(String localizacao) {
        this.localizacao = new Localizacao(localizacao);
    }

    public static boolean isComprovanteEmitido() {
        return ticketEmitido;
    }

    public Retirada retirar(Cartao cartao, Montante montante){
        cartao.debitarImediatamente(montante);
        return new Retirada(cartao, montante);
    };
}
