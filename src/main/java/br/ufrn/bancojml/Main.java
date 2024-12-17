package br.ufrn.bancojml;

import br.ufrn.bancojml.dominio.Titular;

public class Main {
    public static void main(String[] args) {

        String nome = "Bruno", sobrenome = "Wagner", cpf = "12345678958";
        // String numeroCartao  = "1234 5678 9012 3456";
        // String moedaCartao = "EUR";
        // String numeroConta = "300003035940005152434412";
        // Integer saldo = 1000;
        // String nomeBanco = "Société Générale";

        Titular titular = new Titular(nome, sobrenome, cpf);
        // Conta conta = new Conta(new Montante(saldo),numeroConta);
        // long doisAnosDepois = System.currentTimeMillis()+(1000*60*60*24*365*2);
        // Cartao cartao = new Cartao( new NumeroCartao(numeroCartao), new Date(doisAnosDepois),nome + " " + sobrenome, conta);
        // Banco banco = new Banco(nomeBanco);

    }
}