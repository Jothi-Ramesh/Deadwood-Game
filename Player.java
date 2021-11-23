public class Player {
    public int num;
    public String name;
    public String role = "no";
    String location;
    private int credits;
    private int rank;
    private int money;
    private int rehearseLvl;
    public Part part;
    private int level = 0;


    Player(int startMon, int startCred, String nameS, int numS, int rankS){
        money = startMon;
        credits = startCred;
        name = nameS;
        num = numS;
        rank = rankS;
        location = "trailer";
    }

    public int getNum() {
        return num;
    }

    public int getCredits() {
        return credits;
    }
    public int getRank(){
        return rank;
    }
    public int getMoney(){
        return money;
    }
    public String getLocation(){
        return location;
    }
    public void setCredits(int newCredits) {
        credits = newCredits;
    }
    public void setRank(int newRank){
        rank = newRank;
    }
    public void setMoney(int newMoney){
        money = newMoney;
    }
    public void setLocation(String newLocation) {
        location = newLocation;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void work(){

    }
    public void move(String location){

    }
    public void rehearse(String scene, int rehearseLvl){}
    public void upgrade(int rank, int credits, int money, String location){}
    public void setRehearseLvl(int rehearseLvl2){
        rehearseLvl = rehearseLvl2;
    }
    public int getRehearseLvl(){
        return rehearseLvl;
    }
}