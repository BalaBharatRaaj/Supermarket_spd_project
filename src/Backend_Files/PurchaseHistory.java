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
public class PurchaseHistory {
    public String[][] purchase(String mobile_number, String start_date, String end_date){
        double gn_quantity, user_quantity, cal_price;
        String[] temp;
        try {
            int customer_id;
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt, Prestmt1;
            ResultSet myRs1;
            Prestmt = myConn.prepareStatement("select cust_id from customers where phone_no = ?");
            Prestmt.setString(1, mobile_number);
            ResultSet myRs = Prestmt.executeQuery();
            if(myRs.next()){
                customer_id = myRs.getInt("cust_id");
                Prestmt = myConn.prepareStatement("select count(*) as count_order from product_sales where cust_id = ? and (date_of_purchase between ? and ?)");
                Prestmt.setInt(1, customer_id);
                Prestmt.setDate(2, Date.valueOf(start_date));
                Prestmt.setDate(3, Date.valueOf(end_date));
                myRs = Prestmt.executeQuery();
                myRs.next();
                if(myRs.getInt("count_order") == 0){
                    return new String[][] {{"No records"}};
                }
                String[][] ordered = new String[myRs.getInt("count_order")][4];
                Prestmt = myConn.prepareStatement("select product_id, date_of_purchase, quantity from product_sales where cust_id = ? and (date_of_purchase between ? and ?)");
                Prestmt.setInt(1, customer_id);
                Prestmt.setDate(2, Date.valueOf(start_date));
                Prestmt.setDate(3, Date.valueOf(end_date));
                myRs = Prestmt.executeQuery();
                int index = 0;
                while(myRs.next()){
                    Prestmt1 = myConn.prepareStatement("select product_name, quantity, price from products where product_id = ?");
                    Prestmt1.setInt(1, myRs.getInt("product_id"));
                    myRs1 = Prestmt1.executeQuery();
                    myRs1.next();
                    temp = myRs1.getString("quantity").split(" ");
                    if(null == temp[1]){gn_quantity = Float.valueOf(temp[0]);}
                    else switch (temp[1]) {
                        case "kg":
                        case "kl":
                            gn_quantity = Float.valueOf(temp[0])*1000;
                            break;
                        case "mg":
                        case "ml":
                            gn_quantity = Float.valueOf(temp[0])*0.001;
                            break;
                        default:
                            gn_quantity = Float.valueOf(temp[0]);
                            break;
                    }
                    temp = myRs.getString("quantity").split(" ");
                    if(null == temp[1]){user_quantity = Float.valueOf(temp[0]);}
                    else switch (temp[1]) {
                        case "kg":
                        case "kl":
                            user_quantity = Float.valueOf(temp[0])*1000;
                            break;
                        case "mg":
                        case "ml":
                            user_quantity = Float.valueOf(temp[0])*0.001;
                            break;
                        default:
                            user_quantity = Float.valueOf(temp[0]);
                            break;
                    }
                    cal_price = (user_quantity/gn_quantity)*myRs1.getFloat("price");
                    ordered[index][0] = myRs1.getString("product_name");
                    ordered[index][1] = String.valueOf(myRs.getString("quantity"));
                    ordered[index][2] = myRs.getDate("date_of_purchase").toString();
                    ordered[index][3] = String.valueOf(cal_price);
                    index++;
                }
                return ordered;
            }
            else{
                System.out.println("Customer not found");
                return new String[][] {{"Customer not found"}};
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            return new String[][] {{"Error"}};
        }
    }
}
