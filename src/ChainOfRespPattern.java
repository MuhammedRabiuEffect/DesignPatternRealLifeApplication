
interface iLetter
{
    void sendFeedback(LetterStage feedback);
    String getSender();
    int getId();
    int getPriority();
    void sendResponse(String response);
    void changeDestination(String newDestination);
    String getDestination();
    void changePriority(int newPriority);
    void cancelLetter();

}
enum LetterStage
{
    // (received, pending, processed, respond: letter)
    received,
    pending,
    processed,
    responded

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
            LetterStage feedback;
            String response;

    Letter(String content, String sender, String destination)
    {
        this.content = content;
        this.sender = sender;
        this.destination = destination;
    }

    @Override
    public void sendFeedback(LetterStage feedback) {
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

    @Override
    public String getDestination() {
        return this.destination;
    }

    @Override
    public void changePriority(int newPriority) {
        this.letterPriority = newPriority;
    }

    @Override
    public void cancelLetter() {
        
    }
}

interface iHandler
{
    void process(iLetter letter);
    void pass(iLetter letter);
    void changeNextHandler(iHandler newNextHandler);
}

class IT implements iHandler
{
    IT()
    {
        this.letterBox = new LetterLinkedList();
        this.nextHandler = new Marketing();
    }
    LetterLinkedList letterBox;
    @Override
    public void process(iLetter letter)
    {
        if(letter.getDestination().equalsIgnoreCase("it"))
        {
            if(letter.getPriority() < 3)
            {
                letter.sendFeedback(LetterStage.processed);
                this.letterBox.addLetter(letter);

                return;
            }else
            {
                letter.sendFeedback(LetterStage.received);
                this.letterBox.addLetter(letter);

                return;
            }
        }

        this.pass(letter);
    }

    iHandler nextHandler;
    @Override
    public void pass(iLetter letter) {
        this.nextHandler.process(letter);
    }

    @Override
    public void changeNextHandler(iHandler newNextHandler) {
        this.nextHandler = newNextHandler;
    }
}

class Finance implements iHandler
{

    LetterLinkedList letterBox;
    Finance()
    {
        this.letterBox = new LetterLinkedList();
        this.nextHandler = new HR();
    }
    @Override
    public void process(iLetter letter) {
        if(letter.getPriority()  < 2 || letter.getId() < 4)
        {
            letter.sendFeedback(LetterStage.pending);
            this.letterBox.addLetter(letter);
            return;
        }else
        {
            letter.changeDestination("marketing");
            this.pass(letter);

            return;
        }
    }

    iHandler nextHandler;
    @Override
    public void pass(iLetter letter) {
        this.nextHandler.process(letter);
    }

    @Override
    public void changeNextHandler(iHandler newNextHandler) {
        this.nextHandler = newNextHandler;

        System.out.println(" ");
        System.out.println("........................");
        System.out.println("NextHandler Changed");
        System.out.println("........................");
        System.out.println(" ");
    }
}
class Production implements iHandler
{
    Production()
    {
        this.nextHandler = new Finance();
    }
    Getters getters;

    @Override
    public void process(iLetter letter)
    {
        if(letter.getDestination().equalsIgnoreCase("production"))
        {
            this.getters = new Getters();
            String response = this.getters.getString("Enter Production Response");
            letter.sendResponse(response);
            letter.sendFeedback(LetterStage.responded);

            this.getters = null;
            return;
        }
        this.pass(letter);
    }

    iHandler nextHandler;
    @Override
    public void pass(iLetter letter) {
        this.nextHandler.process(letter);
    }

    @Override
    public void changeNextHandler(iHandler newNextHandler) {
        this.nextHandler = newNextHandler;
    }
}
class Marketing implements iHandler
{
    LetterLinkedList letterBox;
    Marketing()
    {
        this.letterBox = new LetterLinkedList();
        this.nextHandler = new Production();

    }
    Getters getters;
    @Override
    public void process(iLetter letter)
    {
        if(letter.getDestination().equalsIgnoreCase("marketing"))
        {
            this.getters = new Getters();
            String response = this.getters.getString("Enter Marketing Response");
            letter.sendResponse(response);
            letter.sendFeedback(LetterStage.responded);

            this.letterBox.addLetter(letter);

            this.getters = null;
            return;
        }

        this.pass(letter);
    }

    iHandler nextHandler;
    @Override
    public void pass(iLetter letter) {
        this.nextHandler.process(letter);
    }

    @Override
    public void changeNextHandler(iHandler newNextHandler) {
        this.nextHandler = newNextHandler;
    }
}
class HR implements iHandler
{
    LetterLinkedList letterBox;
    Getters getters;
    HR()
    {
        this.letterBox = new LetterLinkedList();
        this.nextHandler = new IT();
    }
    @Override
    public void process(iLetter letter)
    {
        if(letter.getDestination().equalsIgnoreCase("hr"))
        {
            switch(letter.getPriority())
            {
                case 1:
                    this.getters = new Getters();
                    String response = this.getters.getString("Enter Response Message: ");
                    letter.sendResponse(response);
                    letter.sendFeedback(LetterStage.responded);
                    this.getters = null;
                    this.letterBox.addLetter(letter);
                    break;

                case 2:
                    letter.changePriority(1);
                    this.process(letter);
                    break;
                case 3:
                    letter.sendFeedback(LetterStage.pending);
                    this.letterBox.addLetter(letter);
                    break;
                default:
                    letter.sendFeedback(LetterStage.received);
                    this.letterBox.addLetter(letter);
                    break;
            }
            return;
        }
        this.pass(letter);
    }

    iHandler nextHandler;
    @Override
    public void pass(iLetter letter)
    {
        this.nextHandler.process(letter);
    }

    @Override
    public void changeNextHandler(iHandler newNextHandler) {
        this.nextHandler = newNextHandler;
    }
}
public class ChainOfRespPattern {
    iHandler handler;
    ChainOfRespPattern(iHandler handler)
    {
        this.handler = handler;
        System.out.println("Handler Set");
    }
    void changeHandler(iHandler newHandler)
    {
        this.handler.changeNextHandler(newHandler);
    }
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
            where a feedback is a letter stage
            the stage could be received, pending, processed, responded

      * the Handler decides to handle the letter or pass to the next handler depending on the destination of the letter
      * where destination could be finance, HR, Marketing, I.T, and Production
        [ FIN ] -> [ HR ] -> [ I.T ] ->[ MARK ] -> [ PROD ] ->
      * where every destination is a handler
      * letter will be stored into a box (LinkedList) when received
      * a handler can later see the content of letters whose feedback or id is x
      * a handler can later respond to all letters whose feedback is "x"
     */

}
