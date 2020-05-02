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
import Ticket.Ticket;
import java.util.ArrayList;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class TicketProject {
    
    static Connection conn = null;
    static Scanner entry = new Scanner(System.in);
    static PreparedStatement add;
    
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
        
        String cont = "1";
        int contChoice = Integer.parseInt(cont);
        
        while (contChoice != 0) {

            //program accepts user input
            System.out.print("Enter Name: ");
            String n = entry.nextLine();
            System.out.print("Enter Department: ");
            String d = entry.nextLine();
            System.out.print("Enter Status: ");
            String s = entry.nextLine();
            System.out.print("Enter Notes: ");
            String o = entry.nextLine();
            
            try {
                PreparedStatement add = conn.prepareStatement("INSERT INTO Ticket VALUES(?,?,?,?)");
            } catch (SQLException e ) {
                
            }
        }
    }

    public static void main(String[] args) {
        
        ConnectDB();
        
        String initialOption;
        int run = 1;
        PreparedStatement add;
        
        while (run != 2) {
            
            System.out.println("What would you like to do?");
            System.out.println("1 = Create Ticket, 2 = Edit Ticket, 3 = Display Tickets, 4 = Exit");
            initialOption = entry.nextLine();
            int choice = Integer.parseInt(initialOption);
            
            if (choice == 1) {

                String cont = "1";
                int contChoice = Integer.parseInt(cont);

                while (contChoice != 0) {

                    //program accepts user input
                    System.out.print("Enter Name: ");
                    String n = entry.nextLine();
                    System.out.print("Enter Department: ");
                    String d = entry.nextLine();
                    System.out.print("Enter Status: ");
                    String s = entry.nextLine();
                    System.out.print("Enter Notes: ");
                    String o = entry.nextLine();
                    
                    //PreparedStatement add = conn.prepareStatement("INSERT into Ticket values(?,?,?,?)");

                }
            }
        }
    }
    
}
