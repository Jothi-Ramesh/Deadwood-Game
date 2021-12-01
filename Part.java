public class Part{
    String name;
    String line;
    int level;
    boolean taken;
    int[] area;
    int onCard;

    public Part(String nName, String nLine, int nLevel, int[] nArea, int onCardIn){
        name = nName;
        line = nLine;
        level = nLevel;
        taken = false;
        area = nArea;
        onCard = onCardIn;
    }
    public String getName() {
        return name;
    }

    public String getLine() {
        return line;
    }

    public int getLevel() {
        return level;
    }

    public int[] getArea() {
        return area;
    }

    public void setArea(int[] area) {
        this.area = area;
    }
}