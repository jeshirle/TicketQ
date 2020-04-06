package TicketQ;
/*
 * Created by: Jeff Shirley
 */

import java.util.Scanner;
import Ticket.Ticket;
import Ticket.TicketList;

public class TicketQ {

    /**
     * @param args the command line arguments
     */
    
    public static void main(String[] args) {
        // Create new arraylist
        TicketList ticket1 = new TicketList();
        int opt;
        int run = 1;
        Scanner entry = new Scanner(System.in);
        
        //start while loop to ensure user is always taken to begining of program
        while (run != 2) {
        
            System.out.println("What would you like to do?");
            System.out.println("1 = create ticket, 2 = Edit Ticket, 3 = Display Tickets, 4 = exit");
            opt = entry.nextInt();
            
            // if user choses option 1
            if (opt < 2 && opt > 0) {
                int cont = 1;
                
                //while loop that allows user to continuously add tickets
                while (cont != 0){
                    
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
                    for (Ticket ticket: ticket1.getTicketList()){
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
            // if user chooses option 2    
            } else if (opt < 3 && opt > 1) {
                int cont1 = 1;
                
                //while loop to allow user to edit multiple tickets (work in progress)
                while (cont1 != 0) {
                    int stn = 0;
                    int sn = 0;
                    String ns;
                    //int sll = ticket1.length;

                    System.out.println("Which ticket would you like to edit? (enter ticket number)");
                    stn = entry.nextInt();
                    System.out.println("Do you want to update Status or Notes? 1 = Status, 2 = Notes");
                    sn = entry.nextInt();
                    
//                    if (sn > 0 && sn < 2) {
//                        
//                        System.out.println("Enter new status: ");
//                        ns = entry.nextLine();
//                        ticket1.get(stn-1).setstatus(ns);
//                    }
                    
                    //ask user for input
                    System.out.println("Would you like to edit another ticket? 1 = yes, 0 = no");
                    cont1 = entry.nextInt();
                }
            //if user chooses option 3
            } else if (opt < 4 && opt > 2) {
                int cont2 = 1;
                
                // while loop if user wants to display ticket list multiple times
                while (cont2 != 0){
                    
                    for(Ticket ticket: ticket1.getTicketList()) {
                        System.out.println(ticket);  // Will invoke overrided `toString()` method
                    }
                    
                    //ask user for input
                    System.out.println("Would you like to display the ticket list again? 1 = yes, 0 = no");
                    cont2 = entry.nextInt();
                }  
            //if user chooses option 4, exit program
            } else {
                System.exit(0);
            }
            //ask user for input and loop back to begining
            System.out.println("Would you like to continue? 1 = Yes, 2 = No");
            run = entry.nextInt();
        }
    }
}

