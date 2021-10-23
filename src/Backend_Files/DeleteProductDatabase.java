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
public class DeleteProductDatabase {
    public int delete_product(int product_id){
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            ResultSet myRs;
            Prestmt = myConn.prepareStatement("select * from products where product_id = ?");
            Prestmt.setInt(1, product_id);
            myRs = Prestmt.executeQuery();
            if(myRs.next()){
                System.out.println("Product ID exists");
                Prestmt = myConn.prepareStatement("delete from product_stock where product_id = ?");
                Prestmt.setInt(1, product_id);
                Prestmt.executeUpdate();
                Prestmt = myConn.prepareStatement("delete from products where product_id = ?");
                Prestmt.setInt(1, product_id);
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
