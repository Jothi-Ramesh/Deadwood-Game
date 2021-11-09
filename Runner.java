import javax.xml.parsers.ParserConfigurationException;
import java.util.Scanner;

public class Runner {
    public static void main(String[] args) throws ParserConfigurationException {
        Scanner scan = new Scanner(System.in);

        System.out.println("Welcome to Deadwood!");
        System.out.print("Please enter how many people will be playing: ");
        int people = scan.nextInt();
        while (people < 2 || people > 8) {
            System.out.println("Sorry, there can only be 2-8 players!");
            System.out.print("Please enter how many people will be playing: ");
            people = scan.nextInt();
        }
        Gsystem sys = new Gsystem(people);
    }
}
