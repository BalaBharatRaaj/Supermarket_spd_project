/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend_Files;
import java.sql.*;
import java.io.*;
import java.text.DateFormat;  
import java.text.SimpleDateFormat;  
import java.util.Date;  
import java.util.Calendar;  
import java.util.Vector;
/**
 *
 * @author Bala
 */
public class PrintFinalBill {
    public int final_bill(Vector<Vector<String>> items, float tot_price, int cust_id){ 
        try {
            double db_quantity, user_quantity;
            String[] temp;
            String unit, data;
            Date date = Calendar.getInstance().getTime();
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss"); 
            String strDate = "Cust"+String.valueOf(cust_id)+"_";
            strDate += dateFormat.format(date);
            java.sql.Date sqlDate = new java.sql.Date(Calendar.getInstance().getTime().getTime());
            strDate += ".txt";  //If needed, fetch the customer name from the database
            //File myObj = new File("filename.txt");
            //if(!myObj.createNewFile()){
            //    return 2;
            //}
            FileWriter fw = new FileWriter(strDate, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter out = new PrintWriter(bw);
            for(Vector<String> item:items){
                System.out.println(item);
                for(int i=0;i<4;i++){
                    data = String.valueOf(item.elementAt(i));
                    out.print(data + " ");
                }
                out.println();
            }
            out.println("Total Price: " + tot_price);
            out.close();
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt, Prestmt1;
            ResultSet myRs;
            for(Vector<String> item:items){
                Prestmt = myConn.prepareStatement("insert into product_sales(product_id, date_of_purchase, quantity, cust_id) values(?,?,?,?)");
                Prestmt.setInt(1, Integer.valueOf(item.elementAt(0)));
                Prestmt.setDate(2, sqlDate);
                Prestmt.setString(3, item.elementAt(2));
                Prestmt.setInt(4, cust_id);
                Prestmt.executeUpdate();
                
                //Make sure that the quantity is greater than zero when fetching the record from the database and adding it in the front end
                //Make a quantity check at the front end if possible
                Prestmt = myConn.prepareStatement("select stock from product_stock where product_id = ?");
                Prestmt.setInt(1, Integer.valueOf(item.elementAt(0)));
                myRs = Prestmt.executeQuery();
                if(myRs.next()){
                    temp = myRs.getString("stock").split(" ");
                    unit = temp[1];
                    db_quantity = Double.parseDouble(temp[0]);
                    temp = item.elementAt(2).split(" ");
                    switch (unit) {
                        case "kg":
                        case "kl":
                            switch(temp[1]){
                                case "kg":
                                case "kl":
                                    user_quantity = Float.valueOf(temp[0]);
                                    break;
                                case "mg":
                                case "ml":
                                    user_quantity = Float.valueOf(temp[0])*(1/1000000);
                                    break;
                                default:
                                    user_quantity = Float.valueOf(temp[0])*0.001;
                                    break;
                            }
                            break;
                        case "mg":
                        case "ml":
                            switch(temp[1]){
                                case "kg":
                                case "kl":
                                    user_quantity = Float.valueOf(temp[0])*1000000;
                                    break;
                                case "mg":
                                case "ml":
                                    user_quantity = Float.valueOf(temp[0]);
                                    break;
                                default:
                                    user_quantity = Float.valueOf(temp[0])*1000;
                                    break;
                            }
                            break;
                        default:
                            switch(temp[1]){
                                case "kg":
                                case "kl":
                                    user_quantity = Float.valueOf(temp[0])*1000;
                                    break;
                                case "mg":
                                case "ml":
                                    user_quantity = Float.valueOf(temp[0])*(1/1000);
                                    break;
                                default:
                                    user_quantity = Float.valueOf(temp[0]);
                                    break;
                            }
                            break;
                    }
                    Prestmt1 = myConn.prepareStatement("update product_stock set stock = ? where product_id = ?");
                    Prestmt1.setString(1, (db_quantity-user_quantity)+" "+unit);
                    Prestmt1.setInt(2, Integer.valueOf(item.elementAt(0)));
                    Prestmt1.executeUpdate();
                }
                else{
                    return 0;
                }
            }
            myConn.close();
            return 1;
        }
        catch(SQLException ex){
            System.out.println(ex);
            return -1;
        }
        catch(IOException ex) {
            System.out.println(ex);
            return -1;
        }
    }
}
