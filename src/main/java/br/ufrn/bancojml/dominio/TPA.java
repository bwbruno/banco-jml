package br.ufrn.bancojml.dominio;

/**
 * Created by jeremiegrodziski
 * TPA abreviação de "Terminal de Pagamento Automático"
 */
public class TPA {
    private Localizacao localizacao;
    private static boolean ticketEmitido = true;

    public TPA(String localizacao) {
        this.localizacao = new Localizacao(localizacao);
    }

    public static boolean isTicketEmitido() {
        return ticketEmitido;
    }

    public Retirada retirar(Cartao cartao, Montante montante){
        cartao.debitarImediatamente(montante);
        return new Retirada(cartao, montante);
    };
}
