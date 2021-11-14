/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;

import Backend_Files.AddProduct;
import Backend_Files.PurchaseHistory;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileFilter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import org.apache.commons.io.filefilter.WildcardFileFilter;

/**
 *
 * @author Bala
 */
public class PurchaseHistoryFrame extends javax.swing.JFrame {

    /**
     * Creates new form PurchaseHistoryFrame
     */
    static int choice = -1;
    private String cust_id;
    static String keeper_name = "";
    public PurchaseHistoryFrame(int choice, String name) {
        initComponents();
        this.model = (DefaultTableModel)jTable1.getModel();
        start.setFormats("yyyy-MM-dd");
        end.setFormats("yyyy-MM-dd");
        this.keeper_name = name;
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Maiandra GD", Font.BOLD, 18));
        try{table_click();}
        catch(Exception ex){javax.swing.JOptionPane.showMessageDialog(this,"Table row problem");}
        loadCustomerMob("N");
        autoSuggestMob();
    }

    PurchaseHistory history_handler = new PurchaseHistory();
    DefaultTableModel model;
    double total_price;
    AddProduct add_handler = new AddProduct();
    private JTextField tf3;
    private Vector<String> v3 = new Vector<String>();
    private boolean hide_flag2 = false;
    FileFilter fileFilter;
    File dir;
    File[] files;
    Path path;
    String date_ordered, temp;
    
    void loadCustomerMob(String text){
       v3 = add_handler.similar_customer_mob(text);
       if(v3.isEmpty()){}
       else if("Error".equals(v3.elementAt(0))){
           javax.swing.JOptionPane.showMessageDialog(this,"Error in fetching Mobile numbers from database");
       }
    }
    private void setModelMob(DefaultComboBoxModel mdl, String str){
        combo3.setModel(mdl);
        combo3.setSelectedIndex(-1);
        tf3.setText(str);
    }
    private static DefaultComboBoxModel getSuggestedModelMob(List<String> list){
        DefaultComboBoxModel m  = new DefaultComboBoxModel();
        for (String s: list){
            m.addElement((s));
        }
        
        return m;
    }
    void autoSuggestMob(){
        combo3.setEditable(true);
        tf3 = (JTextField) combo3.getEditor().getEditorComponent();
        tf3.addKeyListener(new KeyListener(){
            public void keyTyped(KeyEvent e){
                EventQueue.invokeLater(new Runnable(){
                    public void run() {
                        String text = tf3.getText();
                        //Change here to fetch from database
                        if(text.length() == 0){
                            loadCustomerMob("None");
                            combo3.hidePopup();
                            setModelMob(new DefaultComboBoxModel(v3), text);
                        }
                        else{
                            loadCustomerMob(text);
                            DefaultComboBoxModel m = getSuggestedModelMob(v3);
                            
                            if(m.getSize() == 0 || hide_flag2){
                                combo3.hidePopup();
                                hide_flag2 = false;
                            }
                            else{
                                setModelMob(m,text);
                                combo3.showPopup();
                            }
                        }
                    }
                });
            }
            
            @Override
            public void keyPressed(KeyEvent e){
                String text = tf3.getText();
                int code = e.getKeyCode();
                
                if(code == KeyEvent.VK_ENTER){
                    if(v3.contains(text)){
                        v3.addElement(text);
                        Collections.sort(v3);
                        setModelMob(getSuggestedModelMob(v3),text);
                        cust_id = "Cust";
                        cust_id += String.valueOf(add_handler.complete_customer_ID(text));
                    }
                    hide_flag2 = true;
                }
                else if(code == KeyEvent.VK_ESCAPE){
                    hide_flag2 = true;
                }
                else if(code == KeyEvent.VK_RIGHT){
                    for(int i=0; i < v3.size(); i++){
                        String str = v3.elementAt(i);
                        if(str.startsWith(text)){
                            combo3.setSelectedIndex(-1);
                            tf3.setText(str);
                            return;
                        }
                    }
                }
            }
            
            public void keyReleased(KeyEvent e){
                
            }
        });
    }
    
    private void table_click(){
        jTable1.addMouseListener(new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            //System.out.println("Clicked!!!");
            date_ordered = (String)((Vector)model.getDataVector().elementAt(jTable1.getSelectedRow())).elementAt(2);
            dir = new File(".");
            temp = cust_id+"_"+date_ordered+"*.txt";
            //System.out.println(temp);
            fileFilter = new WildcardFileFilter(temp);
            files = dir.listFiles(fileFilter);
            //System.out.println(Arrays.toString(files));
            temp = "<html>";
            if(files.length == 0){
                temp += "No bills found for the given date";
            }
            else{
                for (int i = 0; i < files.length; i++) {
                    path = Paths.get(files[i].toString());
                    try{
                       List<String> lines = Files.readAllLines(path);
                       for(int j=0;j<lines.size();j++){
                            temp += "<pre>";
                            temp += lines.get(j);
                            temp += "</pre>";
                       }
                    }
                    catch(Exception ex){
                        //HandleException
                    }
                    temp += "<pre>\n\n</pre>";
                }
            }
            temp += "</html>";
            //System.out.println(temp);
            jLabel5.setText(temp);
        }
        @Override
        public void mouseExited(MouseEvent e) {
        }
        @Override
        public void mouseEntered(MouseEvent e) {
        }
        @Override
        public void mouseClicked(MouseEvent e) {
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
        start = new org.jdesktop.swingx.JXDatePicker();
        end = new org.jdesktop.swingx.JXDatePicker();
        load = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        back = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        combo3 = new javax.swing.JComboBox<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(370, 40));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 218, 109));
        jLabel1.setText("PURCHASE HISTORY");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 40, -1, -1));

        start.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        start.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startActionPerformed(evt);
            }
        });
        getContentPane().add(start, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 120, 170, -1));

        end.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(end, new org.netbeans.lib.awtextra.AbsoluteConstraints(818, 120, 150, -1));

        load.setBackground(new java.awt.Color(20, 33, 61));
        load.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        load.setForeground(new java.awt.Color(255, 218, 109));
        load.setText("Load Details");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });
        getContentPane().add(load, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 180, 160, -1));

        jTable1.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity Ordered", "Date Ordered"
            }
        ));
        jTable1.setRowHeight(25);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 240, 780, 210));

        back.setBackground(new java.awt.Color(20, 33, 61));
        back.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        back.setForeground(new java.awt.Color(255, 218, 109));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, -1, -1));

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 218, 109));
        jLabel2.setText("Customer Details");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, -1, 30));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 109));
        jLabel3.setText("Start date");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, -1, 30));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 218, 109));
        jLabel4.setText("End date");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(730, 120, -1, 30));

        combo3.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(combo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 120, 190, -1));

        jScrollPane2.setBorder(null);

        jLabel5.setBackground(new java.awt.Color(20, 33, 61));
        jLabel5.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 218, 109));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Click any record on the table to view the contents of the bill on that particular day");
        jLabel5.setOpaque(true);
        jScrollPane2.setViewportView(jLabel5);

        getContentPane().add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 520, 900, 330));

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 218, 109));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("View Bill");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 470, 140, -1));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spd_mini_project/Banaras Supermarket.jpg"))); // NOI18N
        jLabel8.setText("jLabel8");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 0, 260, 140));

        jLabel7.setBackground(new java.awt.Color(20, 33, 61));
        jLabel7.setOpaque(true);
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1120, 920));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        // TODO add your handling code here:
        if(tf3.getText().equals("") || start.getEditor().getText().equals("") || end.getEditor().getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Fill in all the required details");
            return;
        }
        model.setRowCount(0);
        total_price = 0;
        String[][] products = history_handler.purchase(tf3.getText().split(" ")[0], start.getEditor().getText(), end.getEditor().getText());
        if(products[0][0].equals("Customer not found")){
            javax.swing.JOptionPane.showMessageDialog(this,"Customer not found");
            return;
        }
        else if(products[0][0].equals("No records")){
            javax.swing.JOptionPane.showMessageDialog(this,"No purchases found");
            return;
        }
        else if(products[0][0].equals("Error")){
            javax.swing.JOptionPane.showMessageDialog(this,"Database Error");
            return;
        }
        for(String[] product: products){
            model.addRow(new Object[] {product[0],product[1],product[2],product[3]});
            total_price += Double.parseDouble(product[3]);
        }
    }//GEN-LAST:event_loadActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
        new ShopkeeperChoice(choice, keeper_name).setVisible(true);
    }//GEN-LAST:event_backActionPerformed

    private void startActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_startActionPerformed

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
            java.util.logging.Logger.getLogger(PurchaseHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PurchaseHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PurchaseHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PurchaseHistoryFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(choice != 2){
                    new SPD_Mini_Project().setVisible(true);
                    return;
                }
                new PurchaseHistoryFrame(choice, keeper_name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> combo3;
    private org.jdesktop.swingx.JXDatePicker end;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton load;
    private org.jdesktop.swingx.JXDatePicker start;
    // End of variables declaration//GEN-END:variables
}
