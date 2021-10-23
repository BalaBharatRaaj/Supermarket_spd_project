/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend_Files;
import java.sql.*;
import java.util.Vector;
/**
 *
 * @author Bala
 */
public class EditCustomerDetails {
    public int edit_customer_details(String cust_name, String Aadhar_no, String address, String mobile_number){
        try {
            //Cust_name,Phone_No,Aadhar_no,address
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select * from customers where Aadhar_id = ?");
            Prestmt.setString(1, Aadhar_no);
            ResultSet myRs = Prestmt.executeQuery();
            if(myRs.next()){
                Prestmt = myConn.prepareStatement("update customers set Cust_name = ?, Phone_No = ?, address = ? where Aadhar_id = ?");
                Prestmt.setString(1, cust_name);
                Prestmt.setString(2, mobile_number);
                Prestmt.setString(3, address);
                Prestmt.setString(4, Aadhar_no);
                Prestmt.executeUpdate();
                return 1;
            }
            else{
                System.out.println("Customer Not in the database");
                return 0;
            }
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public String[] fetch_customer(String aadhar_id){
        String[] product = new String[3]; 
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select * from customers where aadhar_id = ?");
            Prestmt.setString(1, aadhar_id);
            ResultSet myRs = Prestmt.executeQuery();
            if(myRs.next()){
                product[0] = myRs.getString("cust_name");
                product[1] = myRs.getString("address");
                product[2] = myRs.getString("phone_no");
                return product;
            }
            else{
                System.out.println("Both the values are empty");
                return new String[] {"Failure"};
            }
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return new String[] {"Error"};
        }
    }
    
    public Vector<String> similar_customer_name(String text){
        Vector<String> product_list = new Vector<String>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select cust_name,phone_no from customers where cust_name like ?");
            text += "%";
            Prestmt.setString(1, text);
            ResultSet myRs = Prestmt.executeQuery();
            while(myRs.next()){
                product_list.addElement(myRs.getString("cust_name")  + " " + myRs.getString("phone_no"));
            }
            return product_list;
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            product_list.addElement("Error");
            return product_list;
        }
    }
    
    public Vector<String> similar_aadhar_id(String text){
        Vector<String> product_list = new Vector<String>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select aadhar_id from customers where aadhar_id like ?");
            text += "%";
            Prestmt.setString(1, text);
            ResultSet myRs = Prestmt.executeQuery();
            while(myRs.next()){
                product_list.addElement(myRs.getString("aadhar_id"));
            }
            return product_list;
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            product_list.addElement("-1");
            return product_list;
        }
    }
    
    public String complete_customer_name(String product_name){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            String[] app = product_name.split(" ");
            Prestmt = myConn.prepareStatement("select aadhar_id from customers where cust_name = ? and phone_no = ?");
            Prestmt.setString(1, app[0]);
            Prestmt.setString(2, app[1]);
            ResultSet myRs = Prestmt.executeQuery();
            myRs.next();
            return myRs.getString("aadhar_id");
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return "-1";
        }
    }
    
    public String complete_aadhar_id(String aadhar_id){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select cust_name, phone_no from customers where aadhar_id = ?");
            Prestmt.setString(1, aadhar_id);
            ResultSet myRs = Prestmt.executeQuery();
            myRs.next();
            return myRs.getString("cust_name")+" "+myRs.getString("phone_no");
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return "Error";
        }
    }
    
    public int delete_customer(String aadhar_id){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            ResultSet myRs;
            Prestmt = myConn.prepareStatement("select * from customers where aadhar_id = ?");
            Prestmt.setString(1, aadhar_id);
            myRs = Prestmt.executeQuery();
            if(myRs.next()){
                Prestmt = myConn.prepareStatement("delete from customers where aadhar_id = ?");
                Prestmt.setString(1, aadhar_id);
                Prestmt.executeUpdate();
                myConn.close();
                return 1;
            }
            else{
                myConn.close();
                return 0;
            }
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
}
