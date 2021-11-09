public class Part{
    String name;
    String line;
    int level;
    boolean taken;

    public Part(String name, String line, int level){
        name = name;
        line = line;
        level = level;
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