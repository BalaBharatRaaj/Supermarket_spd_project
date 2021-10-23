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
public class ProductStock {
    public String[][] product_stock(){
        int count; 
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            ResultSet myRes, myRes1;
            Prestmt = myConn.prepareStatement("select count(*) as count_stock from products");
	    myRes = Prestmt.executeQuery();
            myRes.next();
            count = myRes.getInt("count_stock");
            String[][] products = new String[count][4]; 
            Prestmt = myConn.prepareStatement("select product_id, product_name, quantity, price from products");
	    myRes = Prestmt.executeQuery();
            int i = 0;
            while(myRes.next()){
                Prestmt = myConn.prepareStatement("select stock from product_stock where product_id = ?");
                Prestmt.setInt(1, myRes.getInt("product_ID"));
                myRes1 = Prestmt.executeQuery();
                myRes1.next();
                products[i][0] = String.valueOf(myRes.getInt("product_ID"));
                products[i][1] = myRes.getString("product_name");
                products[i][2] = myRes1.getString("stock");
                products[i][3] = String.valueOf(myRes.getFloat("price")+" / "+myRes.getString("quantity"));
                i++;
            }
            return products;
        }
            
        catch(SQLException ex) {
            System.out.println(ex);
            return new String[][] {{"Failure"}};
        }
    }
}