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
public class ItemsToBeOrdered {
    public Vector<Vector<String>> demand_items(){
        String[] temp;
        Vector<Vector<String>> items = new Vector<Vector<String>>();
        Vector<String> item = new Vector<String>();
        try {
            int i = 0;
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            ResultSet myRs, myRs1;
            Prestmt = myConn.prepareStatement("select product_id, min_limit from stock_limit");
            myRs = Prestmt.executeQuery();
            while(myRs.next()){
                Prestmt = myConn.prepareStatement("select stock from product_stock where product_id = ?");
                Prestmt.setInt(1, myRs.getInt("product_id"));
                myRs1 = Prestmt.executeQuery();
                myRs1.next();
                double min_quantity, stock_quantity;
                temp = myRs.getString("min_limit").split(" ");
                if(null == temp[1]){min_quantity = Float.valueOf(temp[0]);}
                else switch (temp[1]) {
                    case "kg":
                    case "kl":
                        min_quantity = Float.valueOf(temp[0])*1000;
                        break;
                    case "mg":
                    case "ml":
                        min_quantity = Float.valueOf(temp[0])*0.001;
                        break;
                    default:
                        min_quantity = Float.valueOf(temp[0]);
                        break;
                }
                temp = myRs1.getString("stock").split(" ");
                if(null == temp[1]){stock_quantity = Float.valueOf(temp[0]);}
                else switch (temp[1]) {
                    case "kg":
                    case "kl":
                        stock_quantity = Float.valueOf(temp[0])*1000;
                        break;
                    case "mg":
                    case "ml":
                        stock_quantity = Float.valueOf(temp[0])*0.001;
                        break;
                    default:
                        stock_quantity = Float.valueOf(temp[0]);
                        break;
                }
                if (stock_quantity <= min_quantity){
                    Prestmt = myConn.prepareStatement("select product_name, price, quantity from products where product_id = ?");
                    Prestmt.setInt(1, myRs.getInt("product_id"));
                    myRs1 = Prestmt.executeQuery();
                    myRs1.next();
                    //System.out.println(myRs.getInt("product_id"));
                    item.addElement(String.valueOf(myRs.getInt("product_id")));
                    item.addElement(myRs1.getString("product_name"));
                    item.addElement(String.valueOf(myRs1.getFloat("price")+"/"+myRs1.getString("quantity")));
                    item.addElement(temp[0]+" "+temp[1]);
                    items.addElement((Vector<String>)item.clone());
                    item.clear();
                }
            }
            //System.out.println(items);
            return items;
        }
        catch(SQLException e){
            System.out.println(e);
            item.clear();
            items.clear();
            item.addElement("Error");
            items.addElement(item);
            return items;
        }
    }
}
