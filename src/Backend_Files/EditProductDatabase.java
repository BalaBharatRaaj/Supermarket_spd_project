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
public class EditProductDatabase {
    public int edit_product_database(String product_name, int product_id, String quantity, double price, String stock, String stock_limit){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select product_name from products where product_id = ?");
            Prestmt.setInt(1, product_id);
            ResultSet myRs = Prestmt.executeQuery();
            if(myRs.next()){
                Prestmt = myConn.prepareStatement("update products set product_name = ?, price = ?, quantity = ? where product_id = ?");
                Prestmt.setString(1, product_name);
                Prestmt.setDouble(2,price);
                Prestmt.setString(3,quantity);
                Prestmt.setInt(4,product_id);
                Prestmt.executeUpdate();
                Prestmt = myConn.prepareStatement("update product_stock set stock = ? where product_id = ?");
                Prestmt.setString(1, stock);
                Prestmt.setFloat(2, product_id);
                Prestmt.executeUpdate();
                Prestmt = myConn.prepareStatement("update stock_limit set min_limit = ? where product_id = ?");
                Prestmt.setString(1, stock_limit);
                Prestmt.setInt(2, product_id);
                Prestmt.executeUpdate();
                return 1;
            }
            else{
                System.out.println("Product Not in the list");
                return 0;
            }
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
    
    public String[] fetch_product(int product_id){
        String[] product = new String[5]; 
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select * from products where product_id = ?");
            Prestmt.setInt(1, product_id);
            ResultSet myRs1, myRs = Prestmt.executeQuery();
            if(myRs.next()){
                Prestmt = myConn.prepareStatement("select stock from product_stock where product_id = ?");
                Prestmt.setInt(1, product_id);
                myRs1 = Prestmt.executeQuery();
                myRs1.next();
                product[0] = myRs.getString("product_name");
                product[1] = String.valueOf(myRs.getFloat("price"));
                product[2] = myRs.getString("quantity");
                product[3] = myRs1.getString("stock");
                Prestmt = myConn.prepareStatement("select min_limit from stock_limit where product_id = ?");
                Prestmt.setInt(1, product_id);
                myRs1 = Prestmt.executeQuery();
                myRs1.next();
                product[4] = myRs1.getString("min_limit");
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
}
