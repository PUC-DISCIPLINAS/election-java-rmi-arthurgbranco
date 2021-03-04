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
        ++this.votes;
    }

    public int getId() {
        return this.id;
    }

    public void setId(int var1) {
        this.id = var1;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String var1) {
        this.name = var1;
    }

    public String getParty() {
        return this.party;
    }

    public void setParty(String var1) {
        this.party = var1;
    }

    public int getVotes() {
        return this.votes;
    }

    public void setVotes(int var1) {
        this.votes = var1;
    }

    public String toString() {
        return this.id + ";" + this.name + ";" + this.party;
    }

    public String toStringWithVotes() {
        return this.id + ";" + this.name + ";" + this.party + ";" + this.votes;
    }
}
