
interface iLetter
{
    void sendFeedback(String feedback);
    String getSender();
    int getId();
    int getPriority();
    void sendResponse(String response);
    void changeDestination(String newDestination);

}
class Letter implements iLetter
{
    public
        final String content;
        final String sender;
        String destination;

    private
            int letterPriority;
            int letterId;
            String feedback;
            String response;

    Letter(String content, String sender, String destination)
    {
        this.content = content;
        this.sender = sender;
        this.destination = destination;
    }
    @Override
    public void sendFeedback(String feedback) {
        this.feedback = feedback;


        System.out.println(" ");
        System.out.println("............................");
        System.out.println("[+] Response Sent");
        System.out.println(" ");
        System.out.println("............................");
    }

    @Override
    public String getSender() {
        return this.sender;
    }

    @Override
    public int getId() {
        return this.letterId;
    }

    @Override
    public int getPriority() {
        return this.letterPriority;
    }

    @Override
    public void sendResponse(String response) {
        this.response = response;
        System.out.println(" ");
        System.out.println("............................");
        System.out.println("[+] Response Sent");
        System.out.println(" ");
        System.out.println("............................");
    }

    @Override
    public void changeDestination(String newDestination) {
        this.destination = newDestination;


        System.out.println(" ");
        System.out.println("............................");
        System.out.println("[+] Destination Changed");
        System.out.println(" ");
        System.out.println("............................");
    }
}

interface iHandler
{
    void process(iLetter letter);
    void pass(iLetter letter);
}

public class ChainOfRespPattern {
    /*


     TASK:
        1. none



     this Design Pattern aims to idealize the concept of Chain Of Responsibility Design Pattern
     this Pattern idealizes layers of Handlers, where each handler decides whether to process a request or
     pass to the next handler depend on the request's parameters

     the application include:
     * the concept of letter submission, processing, and passing to the next handler depending on the letter heading,
     destination, and sender
     * this application will be made modular and scalable in case of upgrade, security, changes, and restriction
     * every letter will have the following heading ( letter is referred to as a Request):
        1. sender
        2. destination
        3. content
        4. feedback (received, pending, processed, respond: letter)

      * the Handler decides to handle the letter or pass to the next handler depending on the destination of the letter
      * where destination could be finance, HR, Marketing, I.T, and Production
        [ FIN ] -> [ HR ] -> [ I.T ] ->[ MARK ] -> [ PROD ] ->
      * where every destination is a handler
      * letter will be stored into a box (LinkedList) when received
      * a handler can later see the content of letters whose feedback or id is x
      * a handler can later respond to all letters whose feedback is "x"
     */
}
