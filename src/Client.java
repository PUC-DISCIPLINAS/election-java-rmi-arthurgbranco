import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;

public class Client {

    public static void main(String[] args) {

        String host = "rmi://localhost/";
        String service = "ElectionService";

        try {
            // Method lookup
            Election e = (Election) Naming.lookup(host + service);
            System.out.println("Remove object \'"+ service + "\' found.");

            // Reading args
            String eleitor = "Arthur Gramiscelli Branco";
            String candidato = "LUIZ FERNANDO SANTOS";

            if (args.length > 1) {
                eleitor = args[0];
                candidato = args[1];
            } else {
                System.out.println("Args missing, using default values...");
                System.out.println("Default eleitor: " + eleitor);
                System.out.println("Default candidato: " + candidato);
            }

            // Generates a md5 hash based on eleitor's name
            MessageDigest md = MessageDigest.getInstance("MD5");
            md.update(eleitor.getBytes());
            byte[] md5 = md.digest();

            // Format in lowercase Hexadecimal before printing to output
            BigInteger numMd5 = new BigInteger(1, md5);
            String hashMd5 = String.format("%022x", numMd5);

            System.out.println("Nome: " + eleitor + System.lineSeparator()
                    + "MD5: " + hashMd5 + System.lineSeparator() + "Candidato: " + candidato);

            // TODO: Add 30 seconds timeout
            // Call remote methods
            System.out.println("Trying to compute vote...");
            e.vote(eleitor, candidato);
            System.out.println("Vote computed!");
            System.out.println("Fetching results...");
            System.out.println("Current votes for " + candidato + ": " + e.result(candidato));
        } catch (MalformedURLException e) {
            System.out.println("URL \'" + host + service + "\' malformed.");
        } catch (RemoteException e) {
            System.out.println("Error in remote invocation.");
            e.printStackTrace();
        } catch (NotBoundException e) {
            System.out.println("Remote Object \'" + service + "\' not available.");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
