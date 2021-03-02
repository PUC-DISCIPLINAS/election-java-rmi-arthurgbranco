package object;

import java.rmi.Remote;

public interface Election extends Remote {

    void vote(String eleitor, String candidato);
    // String eleitor: código hash MD5 gerado a partir do nome completo do eleitor.
    // String candidato: String de 3 caracteres numéricos que identificam um candidato.

    String result(String candidato);
    // Este método possui dois parâmetros com os quais o servidor recebe o número de um candidato
    // e retorna para o cliente o número de votos desse candidato.
}