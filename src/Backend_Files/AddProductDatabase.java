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
public class AddProductDatabase {
    public int add_product_database(String product_name, String quantity, double price, String stock){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            Prestmt = myConn.prepareStatement("select max(product_id) as max_id from products");
            ResultSet myRs1, myRs = Prestmt.executeQuery();
            myRs.next();
            Prestmt = myConn.prepareStatement("select * from products where product_name = ? and quantity = ?");
            Prestmt.setString(1, product_name);
            Prestmt.setString(2, quantity);
            myRs1 = Prestmt.executeQuery();
            if(myRs1.next()){
                System.out.println("Product name and quantity exists");
                return 0;
            }
            else{
                Prestmt = myConn.prepareStatement("insert into products(product_id,product_name,quantity,price) values(?,?,?,?)");
                Prestmt.setInt(1, myRs.getInt("max_id")+1);
                Prestmt.setString(2, product_name);
                Prestmt.setString(3, quantity);
                Prestmt.setDouble(4, price);
                Prestmt.executeUpdate();
                Prestmt = myConn.prepareStatement("insert into product_stock(product_id,stock) values(?,?)");
                Prestmt.setInt(1, myRs.getInt("max_id")+1);
                Prestmt.setString(2, stock);
                Prestmt.executeUpdate();
                //resultset
                myConn.close();
                return 1;
            }
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return -1;
        }
    }
}
