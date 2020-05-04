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
    static PreparedStatement update = null;
    static PreparedStatement getTN = null;
    static PreparedStatement display = null;
    static PreparedStatement displayCT = null;
    
    public static Connection ConnectDB(){
        try {
            Class.forName("org.sqlite.JDBC");
            conn = DriverManager.getConnection("jdbc:sqlite:TicketDB.db");
            //JOptionPane.showMessageDialog(null,"Connection Success!");
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
                String CreatedBy, DateCreated, Department, Status, Notes, add;
                int TicketNumber = 0;
                ResultSet rsn;
                ResultSet rsct;
                //program accepts user input
                System.out.print("Enter Name: ");
                String n = entry.nextLine();
                System.out.print("Enter Department: ");
                String d = entry.nextLine();
                System.out.print("Enter Status: ");
                String s = entry.nextLine();
                System.out.print("Enter Notes: ");
                String o = entry.nextLine();

                add = "INSERT INTO Ticket (Createdby, DateCreated, Department, Status, Notes) VALUES (?,datetime(),?,?,?)";
                getTN = conn.prepareStatement(add, Statement.RETURN_GENERATED_KEYS);
                getTN.setString(1, n);
                getTN.setString(2, d);
                getTN.setString(3, s);
                getTN.setString(4, o);
                getTN.execute();

                ResultSet rs = getTN.getGeneratedKeys();
                if (rs.next()) {
                    TicketNumber = rs.getInt(1);
                }
                                
                displayCT = conn.prepareStatement("SELECT * FROM Ticket WHERE TicketNumber=?");
                displayCT.setInt(1, TicketNumber);
                rsct = displayCT.executeQuery();
                while (rsct.next()) {
                    TicketNumber = rsct.getInt(1);
                    CreatedBy = rsct.getString(2);
                    DateCreated = rsct.getString(3);
                    Department = rsct.getString(4);
                    Status = rsct.getString(5);
                    Notes = rsct.getString(6);
                    System.out.println("Ticket Number: " + TicketNumber);
                    System.out.println("Created By: " + CreatedBy);
                    System.out.println("Date Created: " + DateCreated);
                    System.out.println("Department: " + Department);
                    System.out.println("Status: " + Status);
                    System.out.println("Notes: " + Notes);
                }
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
                display = conn.prepareStatement("SELECT * FROM Ticket");
                rs = display.executeQuery();
                System.out.println("Ticket Number\t\tCreated By\t\tDate Created\t\t\tDepartment\t\t\tStatus\t\t\tNotes");
                while (rs.next()) {
                    TicketNumber = rs.getInt(1);
                    CreatedBy = rs.getString(2);
                    DateCreated = rs.getString(3);
                    Department = rs.getString(4);
                    Status = rs.getString(5);
                    Notes = rs.getString(6);
                    System.out.print(rs.getInt(1));
                    System.out.print("\t\t\t" + rs.getString(2));
                    System.out.print("\t\t\t" + rs.getString(3));
                    System.out.print("\t\t\t" + rs.getString(4));
                    System.out.print("\t\t\t" + rs.getString(5));
                    System.out.print("\t\t\t" + rs.getString(6));
                    System.out.println();
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
