public class Part{
    String name;
    String line;
    int level;
    boolean taken;
    String[] area;

    public Part(String nName, String nLine, int nLevel, String[] nArea){
        name = nName;
        line = nLine;
        level = nLevel;
        taken = false;
        area = nArea;
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
}