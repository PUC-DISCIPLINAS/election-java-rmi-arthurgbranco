import java.io.*;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class ElectionServant extends java.rmi.server.UnicastRemoteObject implements Election {

    private static final long serialVersionUID = 1L;

    private List<Senador> senadores;
    private List<String> voters;
    private final String votersPath = "../files/voters.csv";
    private final String votesPath = "../files/votes.csv";

    protected ElectionServant() throws RemoteException {
        this.senadores = new ArrayList<>();
        this.voters = new ArrayList<>();
    }

    @Override
    public void vote(String eleitor, String candidato) throws java.rmi.RemoteException {
        try {

            if (voters.contains(eleitor)) {
                System.out.println("This eleitor already voted!");
            } else {

                // Read files and gather data
                readVoters();
                readVotes();

                voters.add(eleitor);

                // Update computed voters
                writeVoters();

                // Increment vote
                findCandidato(candidato).addVote(); // FIXME: Handle candidato not found

                // Update computed votes
                writeVotes();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String result(String candidato) throws java.rmi.RemoteException {
        return Integer.toString(findCandidato(candidato).getVotes()); // FIXME: Handle candidato not found
    }

    // FIXME: Handle candidato not found
    private Senador findCandidato(String candidato) throws NoSuchElementException {
        return senadores.stream().filter(e -> e.getName().equalsIgnoreCase(candidato)).findFirst().get();
    }

    // Populate votes Arraylist with Senadores and their vote count
    private void readVotes() throws IOException {

        BufferedReader votesReader = null;

        try {

            votesReader = new BufferedReader(new FileReader(votesPath));
            String row = null;

            // Print all the candidates and add to ArrayList
            while ((row = votesReader.readLine()) != null) {
                String[] data = row.split(";");
                String id = data[0];
                String name = data[1];
                String party = data[2];
                String votes = data[3];
                Senador senador = new Senador(Integer.parseInt(id), name, party, Integer.parseInt(votes));
                senadores.add(senador);
            }
            votesReader.close();

        } catch (IOException e) {
            throw new IOException(e);
        }
    }

    // Populate voters Arraylist with md5 hash of who already voted
    private void readVoters() throws IOException {

        BufferedReader votersReader = null;
        String row = null;

        try{
            // Add computed votes to ArrayList
            votersReader = new BufferedReader(new FileReader(votersPath));
            while ((row = votersReader.readLine()) != null) {
                voters.add(row);
            }
            votersReader.close();
        }catch (IOException e){
            throw new IOException(e);
        }
    }

    // Should be called when finishing the vote process
    private void writeVotes() throws IOException {

        BufferedWriter votesWriter = null;

        try{
            // Update computed votes on file
            votesWriter = new BufferedWriter(new FileWriter(votesPath));
            while (!senadores.isEmpty()){
                votesWriter.write(senadores.remove(0).toStringWithVotes() + System.lineSeparator());
            }
            votesWriter.close();

        }catch (IOException e){
            throw new IOException(e);
        }
    }

    private void writeVoters() throws IOException {

        BufferedWriter votersWriter = null;

        try{
            // Update computed voters on file
            votersWriter = new BufferedWriter(new FileWriter(votersPath));
            while (!voters.isEmpty()) {
                votersWriter.write(voters.remove(0) + System.lineSeparator());
            }
            votersWriter.close();

        }catch (IOException e){
            throw new IOException(e);
        }
    }
}


