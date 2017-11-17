/**
 * A class to model a simple email client. The client is run by a
 * particular user, and sends and retrieves mail via a particular server.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 2011.07.31
 */
public class MailClient
{
    // The server used for sending and receiving.
    private MailServer server;
    // The user running this client.
    private String user;

    /**
     * Create a mail client run by user and attached to the given server.
     */
    public MailClient(MailServer server, String user)
    {
        this.server = server;
        this.user = user;
    }

    /**
     * Return the next mail item (if any) for this user.
     */
    public MailItem getNextMailItem()
    {
        return server.getNextMailItem(user);
    }

    /**
     * Print the next mail item (if any) for this user to the text 
     * terminal.
     */
    public void printNextMailItem()
    {
        MailItem item = server.getNextMailItem(user);
        if(item == null) {
            System.out.println("No new mail.");
        }
        else {
            item.print();

        }
    }

    /**
     * Send the given message to the given recipient via
     * the attached mail server.
     * @param to The intended recipient.
     * @param message The text of the message to be sent.
     */
    public void sendMailItem(String to, String subject, String message)
    {
        MailItem item = new MailItem(false,user, to, subject, message);
        server.post(item);
    }

    public void sendMailItemEncriptado(String to, String subject, String message)
    {                
        message = message.replace("a","$\\");
        message = message.replace("A","\\");
        message = message.replace("e","%\\");
        message = message.replace("E","\\%");
        message = message.replace("i","*\\");
        message = message.replace("I","\\*");
        message = message.replace("o","#\\");
        message = message.replace("O","\\#");
        message = message.replace("u","@\\");
        message = message.replace("U","\\@");
        System.out.println("Message: " + message);          

        MailItem item = new MailItem(true,user, to, subject, message); 
        server.post(item);
    }
}
