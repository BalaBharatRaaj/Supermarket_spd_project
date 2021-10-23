/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend_Files;
import java.sql.*;
/**
 *
 * @author Bala
 */
public class RegisterCustomer {
    public String[] fetch_customer(String Mobile_number) {
            String[] cust_details = new String[4];
            try {
                //int choice = 1;
                //String name = "balabr2002@gmail.com";
                //String pass = "123@435";
                //connection to database
                Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini-project", "root", "root");
                //create statement 
                //Statement myStmt = myConn.createStatement();
                PreparedStatement Prestmt;
                //prepare SQL statement
                Prestmt = myConn.prepareStatement("select * from customers where phone_no = ?");
                Prestmt.setString(1, Mobile_number);
                ResultSet myRs = Prestmt.executeQuery();
                //resultset
                if(myRs.next()){
                    cust_details[0] = String.valueOf(myRs.getInt("Cust_ID"));
                    cust_details[1] = myRs.getString("Cust_Name");
                    cust_details[2] = myRs.getString("aadhar_id");
                    cust_details[3] = Mobile_number;
                    return cust_details;
                }
                else{
                    return new String[] {"Not available"};
                }
            }
            catch(SQLException ex) {
                 System.out.println(ex);
                return new String[] {"Failure"};
            }
    }
	
    public int customer_add(String Aadhar_number, String cust_name, String mobile_no, String address) {
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt = myConn.prepareStatement("select * from customers where aadhar_id = ? or phone_no = ?");
            Prestmt.setString(1, Aadhar_number);
            Prestmt.setString(2, mobile_no);
            ResultSet myRs = Prestmt.executeQuery();
            if(myRs.next()) {
                    System.out.println("Aadhar number or mobile number already exists");
                    myConn.close();
                    return 0;
            }
            else {
                Prestmt = myConn.prepareStatement("select max(cust_id) as max_id from customers");
                myRs = Prestmt.executeQuery();
                myRs.next();
                Prestmt = myConn.prepareStatement("insert into customers(cust_id,Cust_name,Phone_No,Aadhar_id,address) values(?,?,?,?,?)");
                Prestmt.setInt(1, myRs.getInt("max_id")+1);
                Prestmt.setString(2,cust_name);
                Prestmt.setString(3,mobile_no);
                Prestmt.setString(4,Aadhar_number);
                Prestmt.setString(5, address);
                Prestmt.executeUpdate();
                myConn.close();
                return 1;
            }
        }
        catch(SQLException exc){
            System.out.println(exc);
            return -1;
        }
    }
}
