/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;

import Backend_Files.AddProduct;
import Backend_Files.PurchaseHistory;
import java.awt.EventQueue;
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
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
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
    private String cust_id = "Cust";
    static String keeper_name = "";
    public PurchaseHistoryFrame(int choice, String name) {
        initComponents();
        this.model = (DefaultTableModel)jTable1.getModel();
        start.setFormats("yyyy-MM-dd");
        end.setFormats("yyyy-MM-dd");
        this.keeper_name = name;
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
            Path path;
            String date_ordered = (String)((Vector)model.getDataVector().elementAt(jTable1.getSelectedRow())).elementAt(2);
            File dir = new File(".");
            String temp = cust_id+"_"+date_ordered+"*.txt";
            FileFilter fileFilter = new WildcardFileFilter(temp);
            File[] files = dir.listFiles(fileFilter);
            temp = "<html>";
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
            temp += "</html>";
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

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("PURCHASE HISTORY");

        load.setText("Load Details");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Name", "Quantity Ordered", "Date Ordered"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel2.setText("Phone no, Name");

        jLabel3.setText("Start date");

        jLabel4.setText("End date");

        jScrollPane2.setBorder(null);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Click any record on the table to view the contents of the bill on that particular day");
        jScrollPane2.setViewportView(jLabel5);

        jLabel6.setText("View Bill");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(back)
                        .addGap(121, 121, 121))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(82, 82, 82)
                        .addComponent(jLabel2)
                        .addGap(18, 18, 18)
                        .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43)
                        .addComponent(jLabel3)
                        .addGap(39, 39, 39)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jLabel4)
                        .addGap(18, 18, 18)
                        .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 183, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(424, 424, 424)
                        .addComponent(load))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(116, 116, 116)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 764, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(462, 462, 462)
                        .addComponent(jLabel6)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(back))
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(start, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(end, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(combo3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(39, 39, 39)
                .addComponent(load)
                .addGap(31, 31, 31)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(37, 37, 37)
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 448, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(49, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        // TODO add your handling code here:
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
                /*if(choice != 2){
                    new SPD_Mini_Project().setVisible(true);
                    return;
                }*/
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
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton load;
    private org.jdesktop.swingx.JXDatePicker start;
    // End of variables declaration//GEN-END:variables
}
