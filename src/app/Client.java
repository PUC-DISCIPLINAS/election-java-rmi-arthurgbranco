package app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Client {

    public static void main(String[] args) {

        try {
            String eleitor = "Arthur Gramiscelli Branco";
            String candidato = "LUIZ FERNANDO SANTOS";

            if(args.length > 1){
                eleitor = args[0];
                candidato = args[1];
            }else{
                System.out.println("Args missing, using default values...");
                System.out.println("Default eleitor: " + eleitor);
                System.out.println("Default candidato: " + candidato);
            }

            // Gera o hash MD5 baseado no nome do eleitor
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(eleitor.getBytes());
            byte[] md5 = md.digest();

            // Formata em Hexadecimal minusculo para exibir na tela
            BigInteger numMd5 = new BigInteger(1, md5);
            String hashMd5 = String.format("%022x", numMd5);

            System.out.println("Nome: " + eleitor + System.lineSeparator()
                    + "MD5: " + hashMd5 + System.lineSeparator() +"Candidato: " + candidato);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
