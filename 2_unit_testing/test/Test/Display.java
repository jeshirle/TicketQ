/*
 * Created By: Jeff Shirley
 */
package Test;

import Ticket.Ticket;
import Ticket.TicketList;

public class Display {
    
    public static void main(String[] args) {
        
        TicketList ticket1 = new TicketList();
        
        ticket1.addTicket(new Ticket("Jeff", "5801", "OPEN", "Student"));
        ticket1.addTicket(new Ticket("David", "694", "OPEN", "Employee"));
        ticket1.addTicket(new Ticket("Samantha", "1147", "OPEN", "Manager"));
        
        for(Ticket ticket: ticket1.getTicketList()) {
                        System.out.println(ticket);  // Will invoke overrided `toString()` method
        }
    }
}
