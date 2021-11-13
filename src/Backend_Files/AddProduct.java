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
public class AddProduct {
    /*Whenever the shopkeeper enters a product name, display it as a list with quantity and on pressing enter, 
    dynamically fill the product id column, after which the shopkeeper clicks Enter*/
    
    public String[] add_product(int product_id, String quantity){
        String[] product = new String[4]; 
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                Prestmt = myConn.prepareStatement("select * from products where product_id = ?");
                Prestmt.setInt(1, product_id);
                ResultSet myRs1, myRs = Prestmt.executeQuery();
                if(myRs.next()){
                    Prestmt = myConn.prepareStatement("select stock from product_stock where product_id = ?");
                    Prestmt.setInt(1, product_id);
                    myRs1 = Prestmt.executeQuery();
                    myRs1.next();
                    String[] temp = quantity.split(" ");
                    double user_quantity, gn_quantity;
                    if("kg".equals(temp[1]) || "kl".equals(temp[1])){user_quantity = Float.valueOf(temp[0])*1000;}
                    else if("mg".equals(temp[1])){user_quantity = Float.valueOf(temp[0])*0.001;}
                    else if("ml".equals(temp[1])){user_quantity = Float.valueOf(temp[0])*0.001;}
                    else{user_quantity = Float.valueOf(temp[0]);}
                    temp = myRs1.getString("stock").split(" ");
                    //20 kg or 200 g
                    if("kg".equals(temp[1]) || "kl".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*1000;}
                    else if("mg".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else if("ml".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else{gn_quantity = Float.valueOf(temp[0]);}
                    //System.out.println(gn_quantity + " " + user_quantity);
                    if(user_quantity<=gn_quantity){
                        product[0] = String.valueOf(myRs.getInt("product_id"));
                        product[1] = myRs.getString("product_name");
                        product[2] = String.valueOf(myRs.getFloat("price"));
                        product[3] = myRs.getString("quantity");
                        return product;

                    }
                    else{
                        return new String[] {"Stock not available"};
                    }
                }else{
                    return new String[] {"Failure"};
                }
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
    
    public Vector<String> similar_products_name(String text){
        //Lett
        Vector<String> product_list = new Vector<String>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select product_name,quantity from products where product_name like ?");
            text += "%";
            Prestmt.setString(1, text);
            ResultSet myRs = Prestmt.executeQuery();
            while(myRs.next()){
                product_list.addElement(myRs.getString("product_name")  + " " + myRs.getString("quantity"));
            }
            return product_list;
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            product_list.addElement("Error");
            return product_list;
        }
    }
    
    public Vector<Integer> similar_products_id(String text){
        Vector<Integer> product_list = new Vector<Integer>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select product_id from products where product_id like ?");
            text += "%";
            Prestmt.setString(1, text);
            ResultSet myRs = Prestmt.executeQuery();
            while(myRs.next()){
                product_list.addElement(myRs.getInt("product_id"));
            }
            return product_list;
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            product_list.addElement(-1);
            return product_list;
        }
    }
    
    public int complete_product_name(String product_name){
        //Lettuce 1 kg
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            String[] app = product_name.split(" ");
            Prestmt = myConn.prepareStatement("select * from products where product_name = ? and quantity = ?");
            Prestmt.setString(1, app[0]);
            Prestmt.setString(2, app[1]+" "+app[2]);
            ResultSet myRs = Prestmt.executeQuery();
            myRs.next();
            return myRs.getInt("product_id");
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public String complete_product_id(int product_id){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select product_name, quantity from products where product_id = ?");
            Prestmt.setInt(1, product_id);
            ResultSet myRs = Prestmt.executeQuery();
            myRs.next();
            return myRs.getString("product_name")+" "+myRs.getString("quantity");
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return "Error";
        }
    }
    
    public String quantity_fetch(int product_id){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select quantity from products where product_id = ?");
            Prestmt.setInt(1, product_id);
            ResultSet myRs = Prestmt.executeQuery();
            myRs.next();
            return myRs.getString("quantity");
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return "Error";
        }
    }
    
    public Vector<String> similar_customer_mob(String text){
        Vector<String> mob_list = new Vector<String>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select cust_name, phone_no from customers where phone_no like ?");
            text += "%";
            Prestmt.setString(1, text);
            ResultSet myRs = Prestmt.executeQuery();
            while(myRs.next()){
                mob_list.addElement(myRs.getString("phone_no")  + " " + myRs.getString("cust_name"));
            }
            return mob_list;
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            mob_list.addElement("Error");
            return mob_list;
        }
    }
    
    public int complete_customer_ID(String cust_name){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            String[] app = cust_name.split(" ", 2);
            Prestmt = myConn.prepareStatement("select cust_id from customers where cust_name = ? and phone_no = ?");
            Prestmt.setString(1, app[1]);
            Prestmt.setString(2, app[0]);
            ResultSet myRs = Prestmt.executeQuery();
            myRs.next();
            return myRs.getInt("cust_id");
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
}
