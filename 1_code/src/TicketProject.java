/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticketproject;
/**
 *Jeff Shirley
 */

import java.util.Scanner;
import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import java.sql.*;

public class TicketProject {
    
    static Connection conn = null;
    static Scanner entry = new Scanner(System.in);
    static PreparedStatement add = null;
    static PreparedStatement update = null;
    static PreparedStatement display = null;
    static PreparedStatement displayFull = null;
    
    public static Connection ConnectDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:TicketDB.db");
            JOptionPane.showMessageDialog(null,"Connection Success!");
            return conn;
        } catch (ClassNotFoundException | SQLException | HeadlessException e) {
            JOptionPane.showMessageDialog(null, "Connection failed ! " + e);
            return null;
        }
    }
    
    public static void createTicket() {
        
        String contCT = "1";
        int contCTChoice = Integer.parseInt(contCT);
        
        while (contCTChoice != 0) {
            
            try {
                //program accepts user input
                System.out.print("Enter Name: ");
                String n = entry.nextLine();
                System.out.print("Enter Department: ");
                String d = entry.nextLine();
                System.out.print("Enter Status: ");
                String s = entry.nextLine();
                System.out.print("Enter Notes: ");
                String o = entry.nextLine();

                add = conn.prepareStatement("INSERT INTO Ticket (Createdby, DateCreated, Department, Status, Notes) VALUES (?,datetime(),?,?,?)");
                add.setString(1, n);
                add.setString(2, d);
                add.setString(3, s);
                add.setString(4, o);
                int executeAdd = add.executeUpdate();               
            } catch (SQLException e ) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }
            
            System.out.println("Would you like to create another ticket? 1 = yes, 0 = no");
            contCT = entry.nextLine();
            contCTChoice = Integer.parseInt(contCT);
        }
    }
    
    public static void editTicket() {
        
        String contET = "1";
        int contETChoice = Integer.parseInt(contET);
        
        while (contETChoice != 0) {

            try {

                System.out.print("Enter Ticket Number of ticket you wish to update:");
                String ticketNum = entry.nextLine();
                int updateTN = Integer.parseInt(ticketNum);
                System.out.print("Update Department: ");
                String updateD = entry.nextLine();
                System.out.print("Update Status: ");
                String updateS = entry.nextLine();
                System.out.print("Update Notes: ");
                String updateO = entry.nextLine();

                update = conn.prepareStatement("UPDATE Ticket SET Department=?, Status=?, Notes=? WHERE TicketNumber=?");
                update.setString(1, updateD);
                update.setString(2, updateS);
                update.setString(3, updateO);
                update.setInt(4, updateTN);
                int executeUpdate = update.executeUpdate();
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("Would you like to edit another ticket? 1 = yes, 0 = no");
            contET = entry.nextLine();
            contETChoice = Integer.parseInt(contET);
        }
    }
    
    public static void displayTicket() {
        
        String contDT = "1";
        int contDTChoice = Integer.parseInt(contDT);
        String oneAll, CreatedBy, DateCreated, Department, Status, Notes;
        int TicketNumber;
        ResultSet rs;
        
        while (contDTChoice != 0) {

            try {
                System.out.println("Please enter ticket number: ");
                String ticketN = entry.nextLine();
                int displayTN = Integer.parseInt(ticketN);
                
                display = conn.prepareStatement("SELECT * FROM Ticket WHERE TicketNumber=?");
                display.setInt(1, displayTN);
                rs = display.executeQuery();
                while (rs.next()) {
                    TicketNumber = rs.getInt(1);
                    CreatedBy = rs.getString(2);
                    DateCreated = rs.getString(3);
                    Department = rs.getString(4);
                    Status = rs.getString(5);
                    Notes = rs.getString(6);
                    System.out.println("Ticket Number: " + TicketNumber + " Created By: " + CreatedBy + " Date Created: " + DateCreated + " Department: " + Department + " Status: " + Status + " Notes: " + Notes);
                }
            } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
            }

            System.out.println("Would you like to display another ticket? 1 = yes, 0 = no");
            contDT = entry.nextLine();
            contDTChoice = Integer.parseInt(contDT);
        }
    }
    
    public static void close() throws SQLException {
        conn.close();
    }

    public static void main(String[] args) {
        
        ConnectDB();
        
        String initialOption;
        int run = 1;
        
        while (run != 2) {
            
            System.out.println("What would you like to do?");
            System.out.println("1 = Create Ticket, 2 = Edit Ticket, 3 = Display Tickets, 4 = Exit");
            initialOption = entry.nextLine();
            int choice = Integer.parseInt(initialOption);
            
            if (choice == 1) {

                createTicket();
                
            } else if (choice == 2) {
                
                editTicket();
                
            } else if (choice == 3) {
                
                displayTicket();
                
            } else if (choice == 4) {
                
                System.exit(0);
                
            } else {
                
                System.out.println("Not a valid option. Please choose an option 1-4.");
            }
            
            System.out.println("Would you like to continue? 1 = Yes, 2 = No");
            String runLine = entry.nextLine();
            run = Integer.parseInt(runLine);
            
        }
        
        try {
            conn.close();
        } catch (SQLException e) {
                System.err.println(e.getClass().getName() + ": " + e.getMessage());
                System.exit(0);
        }
    }
    
}
