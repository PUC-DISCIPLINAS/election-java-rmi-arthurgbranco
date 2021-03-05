import java.math.BigInteger;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class Client {

    private static int retryCount = 0;

    // Call remote methods
    private static void vote(Election e, String hashMd5, String candidato) throws RemoteException {
        System.out.println("Sending vote...");
        e.vote(hashMd5, candidato);
        System.out.println("Vote sent!");
        System.out.println("Fetching results...");
        System.out.println("Current votes for " + candidato + ": " + e.result(candidato));
        retryCount = 30;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException, InterruptedException {

        String host = "rmi://localhost/";
        String service = "ElectionService";

        // Reading args
        String eleitor = "Arthur Gramiscelli Branco";
        String candidato = "135";

        if (args.length > 1) {
            eleitor = args[0];
            candidato = args[1];
        } else {
            System.out.println("Args missing, using default values...");
        }

        // Generates a md5 hash based on eleitor's name
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(eleitor.getBytes());
        byte[] md5 = md.digest();

        // Format in lowercase Hexadecimal before printing to output
        BigInteger numMd5 = new BigInteger(1, md5);
        String hashMd5 = String.format("%022x", numMd5);
        System.out.println("Dados do eleitor:");
        System.out.println("Nome: " + eleitor + System.lineSeparator()
                + "MD5: " + hashMd5 + System.lineSeparator() + "Candidato: " + candidato + System.lineSeparator());

        while(retryCount < 30){
            try {
                // Method lookup
                Election e = (Election) Naming.lookup(host + service);
                System.out.println("Remote object \'"+ service + "\' found!" + System.lineSeparator());

                vote(e, hashMd5, candidato);

            } catch (MalformedURLException e) {
                System.out.println("URL \'" + host + service + "\' malformed.");
                System.out.println("Retry count: " + retryCount++ + " (max 30)");
                Thread.sleep(1000);
            } catch (RemoteException e) {
                System.out.println("rmiregistry or server missing, run rmiregistry then server.sh");
                System.out.println("Retry count: " + retryCount++ + " (max 30)");
                Thread.sleep(1000);
            } catch (NotBoundException e) {
                System.out.println("Server missing, run server.sh.");
                System.out.println("Retry count: " + retryCount++ + " (max 30)");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Retry count: " + retryCount++ + " (max 30)");
                Thread.sleep(1000);
            }
        }
    }
}
