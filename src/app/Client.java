package app;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Client {

    public static void main(String[] args) {

        try {
            String nomeEleitor = "Arthur Gramiscelli Branco";
            String vote = "135";

            if(args.length > 1){
                nomeEleitor = args[0];
                vote = args[1];
            }else{
                System.out.println("Args missing, using default values...");
                System.out.println("Default eleitor: " + nomeEleitor);
                System.out.println("Default vote: " + vote);
            }

            // Gera o hash MD5 baseado no nome do eleitor
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(nomeEleitor.getBytes());
            byte[] md5 = md.digest();

            // Formata em Hexadecimal minusculo para exibir na tela
            BigInteger numMd5 = new BigInteger(1, md5);
            String hashMd5 = String.format("%022x", numMd5);

            System.out.println("Nome: " + nomeEleitor + System.lineSeparator() + "MD5: " + hashMd5);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

    }
}
