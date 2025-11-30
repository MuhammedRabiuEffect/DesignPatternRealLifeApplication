import java.util.Scanner;

public class Getters {

    private Scanner getScanner;
    public String getString()
    {
        getScanner = new Scanner(System.in);
        System.out.println("[+] Enter Message: ");
        return getScanner.next();
    }
}
