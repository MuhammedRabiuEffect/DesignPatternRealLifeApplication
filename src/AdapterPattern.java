interface Devices
{
    void on();
    void off();
    void makeCall(String contact);
    void addContact(String new_contact);
}

class Phone implements Devices
{
    String[] contacts;
    int counter;
    final int storage_size = 5;
    public Phone()
    {
        this.contacts = new String[this.storage_size];
        this.counter = -1;
    }

    @Override
    public void on()
    {
        System.out.println("[+] Device Is On");
    }

    @Override
    public void off()
    {
        System.out.println("[+] Device Is Off");
    }

    @Override
    public void makeCall(String contact)
    {
        int find_contact = this.findContact(contact);
        if(find_contact == -1)
        {
            this.addContact(contact);
            this.makeCall(contact);
        }

        System.out.println("[+] Calling: " + contact + "...........");
        return;
    }

    private int findContact(String contact)
    {
        if(this.slotIsEmpty())
        {
            System.out.println("[+] Slot Is Empty. No Contact");
            return - 1;
        }
        for(int i=0; i<=this.counter; i++)
        {
            if(this.contacts[i].equalsIgnoreCase(contact))
            {
                return i;
            }
        }
        return -1;
    }

    private boolean slotIsEmpty()
    {
        if(this.counter == -1)
        {
            return true;
        }
        return false;
    }

    private boolean slotIsFull()
    {
        if(this.counter == (this.storage_size - 1))
        {
            return true;
        }
        return false;
    }

    @Override
    public void addContact(String new_contact)
    {
        if(this.slotIsFull())
        {
            System.out.println("[-] Slot Is Full");
            return;
        }

        this.counter += 1;
        this.contacts[this.counter] = new_contact;
        System.out.println("[+] Contact Added");
    }
}

public class AdapterPattern {
}
