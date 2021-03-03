package app;

import object.Election;
import object.Senador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    private static final List<Senador> senadores = new ArrayList<>();
    private static final List<String> voters = new ArrayList<>();
    private static final String votersPath = "./files/voters.csv";
    private static final String votesPath = "./files/votes.csv";

    static void vote(String eleitor, String candidato) throws IOException {

        if(voters.contains(eleitor)){
            System.out.println("This eleitor already voted!");
        }else{
            voters.add(eleitor);
            BufferedWriter votersWriter = new BufferedWriter(new FileWriter(votersPath));
            while(!voters.isEmpty()){
                votersWriter.write(voters.remove(0) + System.lineSeparator());
            }
            votersWriter.close();

            // Increment vote
            findCandidato(candidato).addVote();
        }
    }

    static String result(String candidato){
        return Integer.toString(findCandidato(candidato).getVotes());
    }

    static Senador findCandidato(String candidato){
        return senadores.stream().filter(e -> e.getName().equals(candidato)).findFirst().get();
    }

    public static void main(String[] args) throws IOException {
        BufferedReader votesReader = null;
        BufferedWriter votesWriter = null;
        BufferedReader votersReader = null;
        String row = null;

        try{

            // Candidate list Title
            System.out.println("NR_CANDIDATO;NM_URNA_CANDIDATO;SG_PARTIDO");

            votesReader = new BufferedReader(new FileReader(votesPath));
            // Print all the candidates and add to ArrayList
            while ((row = votesReader.readLine()) != null) {
                String[] data = row.split(";");
                String id = data[0];
                String name = data[1];
                String party = data[2];
                String votes = data[3];
                Senador senador = new Senador(Integer.parseInt(id), name, party, Integer.parseInt(votes));
                senadores.add(senador);

                // Print senadores so Client can read and vote
                System.out.println(senador);
            }
            votesReader.close();


            // Add computed votes to ArrayList
            votersReader = new BufferedReader(new FileReader(votersPath));
            while ((row = votersReader.readLine()) != null) {
                voters.add(row);
            }
            votersReader.close();

            //TODO: test values, delete later
            String eleitor = "4c1a1fb85fbc0bfc581a338a5c0e210a";
            String candidato = "LUIZ FERNANDO SANTOS";
            vote(eleitor, candidato);
            System.out.println("Results for " + candidato + ":");
            System.out.println(result(candidato) + " votes");


            // Rewrite updated votes
            votesWriter = new BufferedWriter(new FileWriter(votesPath));
            while (!senadores.isEmpty()){
                votesWriter.write(senadores.remove(0).toStringWithVotes() + System.lineSeparator());
            }
            votesWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            System.out.println(System.lineSeparator() + "Closing the server...");
        }
    }
}