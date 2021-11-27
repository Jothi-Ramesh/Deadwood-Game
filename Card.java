public class Card {
    String name;
    String desc;
    int scnNum;
    int budget;
    int numParts;
    Part[] parts;

    public Card(String nameN, String descN, int scnNumN, int budgetN, int numPartsN, Part partsIn[]) {
        name = nameN;
        desc = descN;
        scnNum = scnNumN;
        budget = budgetN;
        numParts = numPartsN;
        parts = new Part[numParts];
        for(int i = 0; i < numParts; i++){
            parts[i] = partsIn[i];
        }
    }

    public String getName() {
        return name;
    }

    public int getBudget() {
        return budget;
    }

    public int getNumParts() {
        return numParts;
    }

    public int getScnNum() {
        return scnNum;
    }

    public Part[] getParts() {
        return parts;
    }

    public String getDesc() {
        return desc;
    }
}


