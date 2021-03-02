package app;

import object.Senador;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Server {

    public static void main(String[] args) throws IOException {
        final String votersPath = "./files/voters.csv";
        final String votesPath = "./files/votes.csv";

        BufferedReader votersReader = new BufferedReader(new FileReader(votersPath));
        BufferedWriter votersWriter = new BufferedWriter(new FileWriter(votersPath));

        List<Senador> senadores = new ArrayList<>();

        try{
            BufferedReader votesReader = new BufferedReader(new FileReader(votesPath));

            // Candidate list Title
            System.out.println("NR_CANDIDATO;NM_URNA_CANDIDATO;SG_PARTIDO");

            // Print all the candidates and add to ArrayList
            String row;
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

            // Rewrite updated votes
            BufferedWriter votesWriter = new BufferedWriter(new FileWriter(votesPath));
            while (!senadores.isEmpty()){
                votesWriter.write(senadores.remove(0).toStringWithVotes() + System.lineSeparator());
            }
            votesWriter.close();

        }catch(Exception e){
            e.printStackTrace();
        }finally {
            votersReader.close();
            votersWriter.close();
        }
    }
}