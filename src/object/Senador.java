package object;

public class Senador {
    private int id;
    private String name;
    private String party;
    private int votes;

    public Senador(int id, String name, String party, int votes){
        this.id = id;
        this.name = name;
        this.party = party;
        this.votes = votes;
    }

    public void addVote() {
        this.votes++;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParty() {
        return party;
    }

    public void setParty(String party) {
        this.party = party;
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        this.votes = votes;
    }

    @Override
    public String toString(){
        return this.id + ";" + this.name + ";" + this.party;
    }

    public String toStringWithVotes(){
        return this.id + ";" + this.name + ";" + this.party + ";" + this.votes;
    }
}
