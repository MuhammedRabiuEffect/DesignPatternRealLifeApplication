class NodeLetter
{
    NodeLetter(iLetter letter)
    {
        this.letter = letter;

    }
    iLetter letter;
    NodeLetter nextLetter;
}

interface iLetterLinkedList
{
    void notification(String note);
    void removeLetter(int letterId);
    void addLetter(iLetter newLetter);
}
public class LetterLinkedList implements iLetterLinkedList{

    NodeLetter headNodeLetter;
    NodeLetter tailNodeLetter;

    @Override
    public void notification(String note)
    {
        System.out.println(" ");
        System.out.println("............................");
        System.out.println(note);
        System.out.println(" ");
        System.out.println("............................");
    }

    @Override
    public void removeLetter(int letterId)
    {
        if(this.headNodeLetter == null)
        {
            this.notification("[-] List Is Null");
            return;
        }

        NodeLetter tempHead = this.headNodeLetter;
        NodeLetter tempNext = tempHead.nextLetter;

        while(tempHead.nextLetter != null)
        {
            if(tempHead.letter.getId() == letterId)
            {
                this.headNodeLetter = tempNext;
                this.notification("[+] Letter Removed");
                return;
            }

            tempHead = tempNext;
            tempNext = tempHead.nextLetter;
        }

        this.notification("[+] Letter Not Found");
    }
    @Override
    public void addLetter(iLetter newLetter)
    {
        NodeLetter newLetterNode = new NodeLetter(newLetter);
        if(this.headNodeLetter == null)
        {
            this.headNodeLetter = newLetterNode;
            this.tailNodeLetter = this.headNodeLetter;

            this.notification("[+] New Letter Node Added");
            return;
        }

        NodeLetter tempNode = this.headNodeLetter;

        while(tempNode.nextLetter != null)
        {
            tempNode = tempNode.nextLetter;
        }

        tempNode.nextLetter = newLetterNode;
        this.tailNodeLetter = tempNode;

        this.notification("[+] New Letter Node Added");
        return;
    }

}
