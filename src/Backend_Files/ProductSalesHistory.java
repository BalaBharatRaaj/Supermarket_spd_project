/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Backend_Files;
import java.sql.*;
import java.util.HashMap;
/**
 *
 * @author Bala
 */
public class ProductSalesHistory {
    private int[] array1;
    private String[] array2;

    public ProductSalesHistory(int[] ordered_count, String[] ordered_dates){
        this.array1 = ordered_count;
        this.array2 = ordered_dates;
    }
    
    public int[] getarray1(){
        return array1;
    }
    
    public String[] getarray2(){
        return array2;
    }
    public ProductSalesHistory() {
         //To change body of generated methods, choose Tools | Templates.
    }
    
    public HashMap<Integer,Double> product_sales_month(int product_id, int month){
        String[] temp;
        double gn_quantity;
        HashMap<Integer, Double> map = new HashMap<>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                Prestmt = myConn.prepareStatement("select year(date_of_purchase) as year, quantity from product_sales where product_id = ? and month(date_of_purchase) = ?");
                Prestmt.setInt(1, product_id);
                Prestmt.setInt(2, month);
                ResultSet myRs = Prestmt.executeQuery();
                while(myRs.next()){
                    temp = myRs.getString("quantity").split(" ");
                    if("kg".equals(temp[1]) || "kl".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*1000;}
                    else if("mg".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else if("ml".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else{gn_quantity = Float.valueOf(temp[0]);}
                    if(map.containsKey(myRs.getInt("year"))){
                        map.replace(myRs.getInt("year"), map.get(myRs.getInt("year")) + gn_quantity);
                    }
                    else{
                        map.put(myRs.getInt("year"), gn_quantity);
                    }
                }
                return (HashMap<Integer,Double>)map.clone();
            }
            else{
                System.out.println("Both the values are empty");
                map.clear();
                map.put(-1,-1.00);
                return (HashMap<Integer, Double>)map.clone();
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            map.clear();
            map.put(-1,-1.00);
            return (HashMap<Integer, Double>)map.clone();
        }
    }
    
    public HashMap<Integer,Double> product_sales_year(int product_id, int year){
        String[] temp;
        double gn_quantity;
        HashMap<Integer, Double> map = new HashMap<>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                Prestmt = myConn.prepareStatement("select quantity, month(date_of_purchase) as month from product_sales where product_id = ? and year(date_of_purchase) = ?");
                Prestmt.setInt(1, product_id);
                Prestmt.setInt(2, year);
                ResultSet myRs = Prestmt.executeQuery();
                while(myRs.next()){
                    temp = myRs.getString("quantity").split(" ");
                    if("kg".equals(temp[1]) || "kl".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*1000;}
                    else if("mg".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else if("ml".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else{gn_quantity = Float.valueOf(temp[0]);}
                    if(map.containsKey(myRs.getInt("month"))){
                        map.replace(myRs.getInt("month"), map.get(myRs.getInt("month")) + gn_quantity);
                    }
                    else{
                        map.put(myRs.getInt("month"), gn_quantity);
                    }
                }
                return map;
            }
            else{
                System.out.println("Both the values are empty");
                map.clear();
                map.put(-1,-1.00);
                return (HashMap<Integer, Double>)map.clone();
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            map.clear();
            map.put(-1,-1.00);
            return (HashMap<Integer, Double>)map.clone();
        }
    }
    
    public HashMap<String, Double> product_sales_dates(int product_id, String start_date, String end_date){
        double gn_quantity;
        String[] temp;
        HashMap<String, Double> map = new HashMap<>();
        try {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/spd_mini_project", "root", "root");
            PreparedStatement Prestmt;
            if(product_id != -1){
                ResultSet myRs;
                Prestmt = myConn.prepareStatement("select quantity, date_of_purchase from product_sales where product_id = ? and (date_of_purchase between ? and ?)");
                Prestmt.setInt(1, product_id);
                Prestmt.setDate(2, Date.valueOf(start_date)); //yyyy-mm-dd format
                Prestmt.setDate(3, Date.valueOf(end_date));
                myRs = Prestmt.executeQuery();
                
                while(myRs.next()){
                    temp = myRs.getString("quantity").split(" ");
                    if("kg".equals(temp[1]) || "kl".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*1000;}
                    else if("mg".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else if("ml".equals(temp[1])){gn_quantity = Float.valueOf(temp[0])*0.001;}
                    else{gn_quantity = Float.valueOf(temp[0]);}
                    if(map.containsKey(myRs.getString("date_of_purchase"))){
                        map.replace(myRs.getString("date_of_purchase"), map.get(myRs.getString("date_of_purchase")) + gn_quantity);
                    }
                    else{
                        map.put(myRs.getString("date_of_purchase"), gn_quantity);
                    }
                }
                return (HashMap<String, Double>)map.clone();
            }
            else{
                System.out.println("Both the values are empty");
                map.clear();
                map.put("Product ID Error",-1.00);
                return (HashMap<String, Double>)map.clone();
            }
        } 
        catch(SQLException ex) {
            System.out.println(ex);
            map.clear();
            map.put("Error",-1.00);
            return (HashMap<String, Double>)map.clone();
        }
    }
}
