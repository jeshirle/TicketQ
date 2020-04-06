
package Ticket;

import java.util.ArrayList;
/**
 * Created by Jeff Shirley
 */

//create TicketList class
public class TicketList {
    
    private ArrayList<Ticket> newTicket;

    //creates new ArrayList
    public TicketList(){
        this.newTicket = new ArrayList<>();
    }
    
    //takes created ticket and adds it to ArrayList
    public void addTicket(Ticket ticket){
        this.newTicket.add(ticket);
    }
    
    //gathers the ArrayList
    public ArrayList<Ticket> getTicketList(){
        return this.newTicket;
    }
}
