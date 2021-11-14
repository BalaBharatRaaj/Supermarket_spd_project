/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;

import Backend_Files.AddProduct;
import Backend_Files.ProductSalesHistory;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.TreeMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;

/**
 *
 * @author Bala
 */
public class SalesHistoryFrame extends javax.swing.JFrame {

    /**
     * Creates new form SalesHistory
     */
    static int choice = -1;
    static String name = "";
    public SalesHistoryFrame(int choice, String name) {
        initComponents();
        start.setVisible(false);
        end.setVisible(false);
        year.setVisible(false);
        jLabel2.setVisible(false);
        jLabel3.setVisible(false);
        month.setVisible(false);
        start.setFormats("yyyy-MM-dd");
        end.setFormats("yyyy-MM-dd");
        loadProductsName("None");
        autoSuggestName();
        loadProductsID("-1");
        autoSuggestID();
        this.choice = choice;
        this.name = name;
    }
    
    private Vector<String> v1 = new Vector<String>();
    private Vector<Integer> v2 = new Vector<Integer>();
    private JTextField tf, tf1;
    private boolean hide_flag = false, hide_flag1 = false;
    AddProduct add_handler = new AddProduct();
    ProductSalesHistory history_handler = new ProductSalesHistory();
    HashMap<Integer,String> articleMapOne = new HashMap<Integer,String>(){{
        put(1, "Jan");put(2, "Feb");put(3, "Mar");put(4, "Apr");
        put(5, "May");put(6, "Jun");put(7, "Jul");put(8, "Aug");
        put(9, "Sep");put(10, "Oct");put(11, "Nov");put(12, "Dec");
    }};
    
    
    void loadProductsName(String text){
       v1 = add_handler.similar_products_name(text);
       if(v1.isEmpty()){}
       else if("Error".equals(v1.elementAt(0))){
           javax.swing.JOptionPane.showMessageDialog(this,"Error in fetching Product Names from database");
       }
    }
    private void setModelName(DefaultComboBoxModel mdl, String str){
        combo1.setModel(mdl);
        combo1.setSelectedIndex(-1);
        tf.setText(str);
    }
    private static DefaultComboBoxModel getSuggestedModelName(List<String> list){
        DefaultComboBoxModel m  = new DefaultComboBoxModel();
        for (String s: list){
            m.addElement((s));
        }
        
        return m;
    }
    void autoSuggestName(){
        combo1.setEditable(true);
        tf = (JTextField) combo1.getEditor().getEditorComponent();
        tf.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable(){
                    public void run() {
                        String text = tf.getText();
                        //Change here to fetch from database
                        if(text.length() == 0){
                            loadProductsName("None");
                            combo1.hidePopup();
                            setModelName(new DefaultComboBoxModel(v1), text);
                        }
                        else{
                            loadProductsName(text);
                            DefaultComboBoxModel m = getSuggestedModelName(v1);
                            
                            if(m.getSize() == 0 || hide_flag){
                                combo1.hidePopup();
                                hide_flag = false;
                            }
                            else{
                                setModelName(m,text);
                                combo1.showPopup();
                            }
                        }
                    }
                });
            }
            
            @Override
            public void keyPressed(KeyEvent e){
                String text = tf.getText();
                int code = e.getKeyCode();
                
                if(code == KeyEvent.VK_ENTER){
                    if(v1.contains(text)){
                        v1.addElement(text);
                        Collections.sort(v1);
                        setModelName(getSuggestedModelName(v1),text);
                        tf1.setText(String.valueOf(add_handler.complete_product_name(text)));
                    }
                    hide_flag = true;
                }
                else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag = true;
                }
                else if(code == KeyEvent.VK_RIGHT){
                    for(int i=0; i < v1.size(); i++){
                        String str = v1.elementAt(i);
                        if(str.startsWith(text)){
                            combo1.setSelectedIndex(-1);
                            tf.setText(str);
                            return;
                        }
                    }
                }
            }
            
            public void keyReleased(KeyEvent e){
                
            }
        });
    }

    //Functions for the Product ID ComboBox
    void loadProductsID(String text){
        v2 = add_handler.similar_products_id(text);
        if(v2.isEmpty()){}
        else if(v2.elementAt(0) == -1){
           javax.swing.JOptionPane.showMessageDialog(this,"Error in fetching product IDs from database");
       }
    }
    private void setModelID(DefaultComboBoxModel mdl, String str){
        combo2.setModel(mdl);
        combo2.setSelectedIndex(-1);
        tf1.setText(str);
    }
    private static DefaultComboBoxModel getSuggestedModelID(List<Integer> list){
        DefaultComboBoxModel m  = new DefaultComboBoxModel();
        for (Integer s: list){
            m.addElement((s));
        }
        
        return m;
    }
    void autoSuggestID(){
        combo2.setEditable(true);
        tf1 = (JTextField) combo2.getEditor().getEditorComponent();
        tf1.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable(){
                    public void run() {
                        String text = tf1.getText();
                        //Change here to fetch from database
                        if(text.length() == 0){
                            loadProductsID("-1");
                            combo2.hidePopup();
                            setModelID(new DefaultComboBoxModel(v2), text);
                        }
                        else{
                            loadProductsID(text);
                            DefaultComboBoxModel m = getSuggestedModelID(v2);
                            
                            if(m.getSize() == 0 || hide_flag1){
                                combo2.hidePopup();
                                hide_flag1 = false;
                            }
                            else{
                                setModelID(m,text);
                                combo2.showPopup();
                            }
                        }
                    }
                });
            }
            
            @Override
            public void keyPressed(KeyEvent e){
                String text = tf1.getText();
                int code = e.getKeyCode();
                
                if(code == KeyEvent.VK_ENTER){
                    if(v2.contains(Integer.valueOf(text))){
                        v2.addElement(Integer.valueOf(text));
                        Collections.sort(v2);
                        setModelID(getSuggestedModelID(v2),text);
                        tf.setText(String.valueOf(add_handler.complete_product_id(Integer.valueOf(text))));
                    }
                    hide_flag1 = true;
                }
                else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag1 = true;
                }
                else if(code == KeyEvent.VK_RIGHT){
                    for(int i=0; i < v2.size(); i++){
                        String str = String.valueOf(v2.elementAt(i));
                        if(str.startsWith(text)){
                            combo2.setSelectedIndex(-1);
                            tf1.setText(str);
                            return;
                        }
                    }
                }
            }
            
            public void keyReleased(KeyEvent e){
                
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        combo1 = new javax.swing.JComboBox<>();
        combo2 = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        select = new javax.swing.JComboBox<>();
        disp_chart = new javax.swing.JButton();
        back = new javax.swing.JButton();
        start = new org.jdesktop.swingx.JXDatePicker();
        reqlabel = new javax.swing.JLabel();
        year = new javax.swing.JTextField();
        end = new org.jdesktop.swingx.JXDatePicker();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        month = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(500, 250));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 218, 109));
        jLabel1.setText("PRODUCT SALES HISTORY");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        combo1.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        combo1.setForeground(new java.awt.Color(20, 33, 61));
        combo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo1ActionPerformed(evt);
            }
        });
        getContentPane().add(combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 170, 180, 30));

        combo2.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        combo2.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(combo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 170, 100, 30));

        jLabel3.setBackground(new java.awt.Color(20, 33, 61));
        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 109));
        jLabel3.setText("End Date");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 290, 100, 30));

        select.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        select.setForeground(new java.awt.Color(20, 33, 61));
        select.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Month", "Year", "Date Range" }));
        select.setSelectedIndex(-1);
        select.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                selectActionPerformed(evt);
            }
        });
        getContentPane().add(select, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 170, 140, 30));

        disp_chart.setBackground(new java.awt.Color(20, 33, 61));
        disp_chart.setFont(new java.awt.Font("Maiandra GD", 1, 22)); // NOI18N
        disp_chart.setForeground(new java.awt.Color(255, 218, 109));
        disp_chart.setText("Display Bar Chart");
        disp_chart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                disp_chartActionPerformed(evt);
            }
        });
        getContentPane().add(disp_chart, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, 240, -1));

        back.setBackground(new java.awt.Color(20, 33, 61));
        back.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        back.setForeground(new java.awt.Color(255, 218, 109));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 90, -1));

        start.setForeground(new java.awt.Color(20, 33, 61));
        start.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        start.setOpaque(true);
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 290, 159, 30));

        reqlabel.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        reqlabel.setForeground(new java.awt.Color(255, 218, 109));
        reqlabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        reqlabel.setText("Choose an option");
        getContentPane().add(reqlabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 250, 520, -1));

        year.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        year.setForeground(new java.awt.Color(20, 33, 61));
        year.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                yearActionPerformed(evt);
            }
        });
        getContentPane().add(year, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 140, -1));

        end.setForeground(new java.awt.Color(20, 33, 61));
        end.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        end.setOpaque(true);
        getContentPane().add(end, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 290, 155, 30));

        jLabel2.setBackground(new java.awt.Color(20, 33, 61));
        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 218, 109));
        jLabel2.setText("Start Date");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 290, 110, 30));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 218, 109));
        jLabel4.setText("Product Name");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 130, 30));

        jLabel5.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 218, 109));
        jLabel5.setText("Product ID");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 170, 100, 30));

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 218, 109));
        jLabel6.setText("Options");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(640, 170, -1, 30));

        month.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        month.setForeground(new java.awt.Color(20, 33, 61));
        month.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" }));
        month.setSelectedIndex(-1);
        getContentPane().add(month, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 290, 140, -1));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spd_mini_project/Banaras Supermarket.jpg"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 0, 260, 150));

        jLabel8.setBackground(new java.awt.Color(20, 33, 61));
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 460));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo1ActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startActionPerformed

    private void disp_chartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_disp_chartActionPerformed
        // TODO add your handling code here:
        if(tf1.getText().length() == 0 || tf1.getText().length() == 0 || select.getSelectedIndex() == -1){
            javax.swing.JOptionPane.showMessageDialog(this,"Fill in the required details");return;
        }
        if(select.getSelectedItem().equals("Date Range")){
            String temp_datepurchase;
            HashMap<String, Double> hm;
            try{
                hm = history_handler.product_sales_dates(Integer.valueOf(tf1.getText()), start.getEditor().getText(), end.getEditor().getText());
                if(hm.size() == 0){
                   javax.swing.JOptionPane.showMessageDialog(this,"No records found");return;
                }
                if(hm.containsKey("Error")){
                    javax.swing.JOptionPane.showMessageDialog(this,"Database Error");return;
                }
                if(hm.containsKey("Product ID Error")){
                    javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Product ID");return;
                }
            }
            catch(NumberFormatException ex){
                javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid numeric product ID");return;
            }
            TreeMap<String,Double> tm=new  TreeMap(hm);  
            Iterator itr=tm.keySet().iterator(); 
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(itr.hasNext()){
                temp_datepurchase = (String)itr.next();
                dataset.setValue(hm.get(temp_datepurchase),"Purchased quantity",temp_datepurchase);
            }
            JFreeChart chart = ChartFactory.createBarChart("Product Sales", "Date of product selling", "Purchased quantity (in grams or litres or pieces)", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(java.awt.Color.BLACK);
            ChartFrame frame = new ChartFrame("Stock of the product sold in the given date range",chart);
            frame.setVisible(true);
            frame.setSize(450,350);
            frame.setLocation(700, 300);
        }
        else if(select.getSelectedItem().equals("Month")){
            int temp_year;
            HashMap<Integer, Double> hm;
            try{
                hm = history_handler.product_sales_month(Integer.valueOf(tf1.getText()), Integer.valueOf(month.getSelectedIndex())+1);
                if(hm.size() == 0){
                   javax.swing.JOptionPane.showMessageDialog(this,"No records found");return;
                }
                if(hm.containsKey("Error")){
                    javax.swing.JOptionPane.showMessageDialog(this,"Database Error");return;
                }
                if(hm.containsKey("Product ID Error")){
                    javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Product ID");return;
                }
            }
            catch(NumberFormatException ex){
                javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid numeric product ID");return;
            }
            TreeMap<Integer,Double> tm=new  TreeMap(hm);  
            Iterator itr=tm.keySet().iterator(); 
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(itr.hasNext()){
                temp_year = (Integer)itr.next();
                dataset.setValue(hm.get(temp_year),"Purchased quantity",String.valueOf(temp_year));
            }
            JFreeChart chart = ChartFactory.createBarChart("Product Sales", "Year of product selling", "Purchased quantity (in grams or litres or pieces)", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(java.awt.Color.BLACK);
            ChartFrame frame = new ChartFrame("Stock of the product sold in the given date range",chart);
            frame.setVisible(true);
            frame.setSize(450,350);
            frame.setLocation(700, 300);
        }
        else if(select.getSelectedItem().equals("Year")){
            int temp_year;
            HashMap<Integer, Double> hm;
            try{
               hm = history_handler.product_sales_year(Integer.valueOf(tf1.getText()), Integer.valueOf(year.getText()));
               if(hm.size() == 0){
                   javax.swing.JOptionPane.showMessageDialog(this,"No records found");return;
               }
               if(hm.containsKey("Error")){
                   javax.swing.JOptionPane.showMessageDialog(this,"Database Error");return;
               }
               if(hm.containsKey("Product ID Error")){
                   javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Product ID");return;
               }
            }
            catch(NumberFormatException ex){
                javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid numeric product ID or year");return;
            }
            TreeMap<Integer,Double> tm=new  TreeMap(hm);  
            Iterator itr=tm.keySet().iterator(); 
            DefaultCategoryDataset dataset = new DefaultCategoryDataset();
            while(itr.hasNext()){
                temp_year = (Integer)itr.next();
                dataset.setValue(hm.get(temp_year),"Purchased quantity",articleMapOne.get(temp_year));
            }
            JFreeChart chart = ChartFactory.createBarChart("Product Sales", "Month of product selling", "Purchased quantity (in grams or litres or pieces)", dataset, PlotOrientation.VERTICAL, false, true, false);
            CategoryPlot p = chart.getCategoryPlot();
            p.setRangeGridlinePaint(java.awt.Color.BLACK);
            ChartFrame frame = new ChartFrame("Stock of the product sold in the given date range",chart);
            frame.setVisible(true);
            frame.setSize(450,350);
            frame.setLocation(700, 300);
        }    
    }//GEN-LAST:event_disp_chartActionPerformed

    private void selectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_selectActionPerformed
        // TODO add your handling code here:
        String item = (String)select.getSelectedItem();
        if(item.equals("Month")){
            reqlabel.setText("Enter the required month in numbers");
            start.setVisible(false);
            end.setVisible(false);
            year.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            month.setVisible(true);
        }
        else if(item.equals("Year")){
            reqlabel.setText("Enter the required year");
            start.setVisible(false);
            end.setVisible(false);
            month.setVisible(false);
            jLabel2.setVisible(false);
            jLabel3.setVisible(false);
            year.setVisible(true);
        }
        else{
            reqlabel.setText("Select the required start and end date");
            month.setVisible(false);
            year.setVisible(false);
            start.setVisible(true);
            end.setVisible(true);
            jLabel2.setVisible(true);
            jLabel3.setVisible(true);
        }
    }//GEN-LAST:event_selectActionPerformed

    private void yearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_yearActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_yearActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
        new AdminChoice(choice, name).setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(SalesHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(choice != 1 || name.equals("")){
                    new SPD_Mini_Project().setVisible(true);
                    return;
                }
                new SalesHistoryFrame(choice,name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JButton disp_chart;
    private org.jdesktop.swingx.JXDatePicker end;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JComboBox<String> month;
    private javax.swing.JLabel reqlabel;
    private javax.swing.JComboBox<String> select;
    private org.jdesktop.swingx.JXDatePicker start;
    private javax.swing.JTextField year;
    // End of variables declaration//GEN-END:variables
}
