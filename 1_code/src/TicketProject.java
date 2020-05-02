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
import java.util.Date;
import Ticket.Ticket;
import java.util.ArrayList;
import java.awt.HeadlessException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.sql.*;

public class TicketProject {
    
    static Connection conn = null;
    static Scanner entry = new Scanner(System.in);
    
    public static Connection ConnectDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:C:\\Users\\jeshi\\Documents\\NetBeansProjects\\TicketProject\\src\\ticketproject");
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
                PreparedStatement add = conn.prepareStatement("INSERT INTO Ticket VALUES(?,?,?,?,?)");
                add.setString(2, n);
                add.setDate(3, java.sql.Date.valueOf(java.time.LocalDate.now()));
                add.setString(4, d);
                add.setString(5, s);
                add.setString(6, o);
                int executeUpdate = add.executeUpdate();
            } catch (SQLException e ) {
                
            }
            
            System.out.println("Would you like to create another ticket? 1 = yes, 0 = no");
            cont = entry.nextLine();
            contChoice = Integer.parseInt(cont);
        }
    }
    
    public static void editTicket() {
        
    }
    
    public static void displayTicket() {
        
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
                
                
            }
            
            System.out.println("Would you like to continue? 1 = Yes, 2 = No");
            String runLine = entry.nextLine();
            run = Integer.parseInt(runLine);
            
        }
        
        //conn.close();
    }
    
}
