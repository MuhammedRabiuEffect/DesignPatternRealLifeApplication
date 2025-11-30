abstract class absPrinter
{
    void print(String what_to_print, boolean topLine, boolean botLine)
    {
        if(topLine)
        {
            System.out.println(".............................");
        }
        System.out.println("[+]: " + what_to_print);
        if(botLine)
        {
            System.out.println(".............................");
        }
    }
}
public class Printer {
}
