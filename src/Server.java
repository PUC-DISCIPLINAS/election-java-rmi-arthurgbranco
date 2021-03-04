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
            // Print senadores so Client can read and vote
            final String csvPath = "../files/senadores.csv";
            BufferedReader csvReader = new BufferedReader(new FileReader(csvPath));
            String row = null;
            while ((row = csvReader.readLine()) != null) {
                System.out.println(row);
            }
            csvReader.close();

            // Start the server
            System.out.println(System.lineSeparator() + "Election server running..." + System.lineSeparator());
            new Server();

        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
