/*
 * Created by: Jeff Shirley
 */
package Test;

import java.util.Scanner;
import Ticket.Ticket;
import Ticket.TicketList;

public class Create {

    public static void main(String[] args) {

        Scanner entry = new Scanner(System.in);
        int cont = 1;

        //while loop that allows user to continuously add tickets
        while (cont != 0) {

            TicketList ticket1 = new TicketList();

            //program accepts user input
            System.out.print("Enter Name: ");
            String n = entry.nextLine();
            System.out.print("Enter Department: ");
            String d = entry.nextLine();
            System.out.print("Enter Status: ");
            String s = entry.nextLine();
            System.out.print("Enter Notes: ");
            String o = entry.nextLine();

            //create new ticket using user input and add it to array
            ticket1.addTicket(new Ticket(n, d, s, o));

            //import methods from Ticket.java and display ticket
            for (Ticket ticket : ticket1.getTicketList()) {
                System.out.println("Ticket Number: " + ticket.getTicketNumber());
                System.out.println("Created by: " + ticket.getCName());
                System.out.println("Date Created: " + ticket.getDate());
                System.out.println("Department: " + ticket.getDepartment());
                System.out.println("Status: " + ticket.getStatus());
                System.out.println("Notes: " + ticket.getNotes());
            }

            //ask user for input
            System.out.println("Would you like to create another ticket? 1 = yes, 0 = no");
            cont = entry.nextInt();
        }
    }
}
