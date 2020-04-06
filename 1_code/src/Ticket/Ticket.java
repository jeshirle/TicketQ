package Ticket;
/*
 * Created by: Jeff Shirley
 */

import java.util.ArrayList;
import java.util.Date;

//create Ticket class
public class Ticket {
    
    private static int tn = 0;
    private final int  ticketNumber;
    private final String creatorName;
    private java.util.Date date = new java.util.Date();
    private final String department;
    private final String status;
    private final String notes;
    
    //method to create new ticket
    public Ticket(String creatorName, String department, String status, String notes){
        
        //increment ticket number, insuring first ticket is 1 and so forth
        tn++;
        this.ticketNumber = tn;
        this.creatorName = creatorName;
        this.date = date;
        this.department = department;
        this.status = status;
        this.notes = notes;
    }
    
    public int getTicketNumber(){
        return ticketNumber;
    }
    
    public String getCName(){
        return creatorName;
    }
    
    public Date getDate(){
        return date;
    }
    
    public String getDepartment(){
        return department;
    }
    
    public String getStatus(){
        return status;
    }
    
    public String getNotes(){
        return notes;
    }
    
    //override print function to force TicketQ to print a string
    @Override
    public String toString(){
        return "Ticket Number: " + this.getTicketNumber() + ", Created by: " + this.getCName() + ", Date Created: " + this.getDate() + ", Department: " + this.getDepartment() + ", Status: " + this.getStatus() + ", Notes: " + this.getNotes();
    }
}
