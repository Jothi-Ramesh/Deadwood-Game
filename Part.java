public class Part{
    String name;
    String line;
    int level;
    boolean taken;

    public Part(String nName, String nLine, int nLevel){
        name = nName;
        line = nLine;
        level = nLevel;
        taken = false;
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