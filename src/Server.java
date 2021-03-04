import java.io.BufferedReader;
import java.io.FileReader;
import java.rmi.Naming;

public class Server {

    public Server() {
        try {
            Election e = new ElectionServant();
            Naming.rebind("rmi://localhost/ElectionService", e);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public static void main(String[] args) {

        try{

            // Candidate list Title
            System.out.println("NR_CANDIDATO;NM_URNA_CANDIDATO;SG_PARTIDO");

            // Print senadores so Client can read and vote
            final String csvPath = "../files/senadores.csv";
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row = null;
            while ((row = csvReader.readLine()) != null) {
                System.out.println(row);
            }
            csvReader.close();

            // Start the server
            new Server();
            System.out.println("Election server running...");
        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(System.lineSeparator() + "Closing the server...");
        }
    }
}