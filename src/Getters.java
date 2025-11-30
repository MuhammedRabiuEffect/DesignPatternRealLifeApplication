import java.util.Scanner;

public class Getters {

    private Scanner getScanner;
    public String getString(String prompt)
    {
        getScanner = new Scanner(System.in);
        System.out.println(prompt);
        return getScanner.nextLine();
    }
}
