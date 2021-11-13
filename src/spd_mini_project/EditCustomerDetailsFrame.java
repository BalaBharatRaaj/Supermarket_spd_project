/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;

import Backend_Files.AddProduct;
import Backend_Files.DeleteProductDatabase;
import Backend_Files.EditCustomerDetails;
import Backend_Files.EditProductDatabase;
import java.awt.EventQueue;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Collections;
import java.util.List;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTextField;

/**
 *
 * @author Bala
 */
public class EditCustomerDetailsFrame extends javax.swing.JFrame {

    /**
     * Creates new form EditCustomerDetailsFrame
     */
    static int choice = -1;
    static String keeper_name = "";
    public EditCustomerDetailsFrame(int choice, String name) {
        initComponents();
        loadCustomerName("None");
        autoSuggestName();
        loadAadharID("-1");
        autoSuggestID();
        this.keeper_name = name;
    }
    
    private Vector<String> v1 = new Vector<String>();
    private Vector<String> v2 = new Vector<String>();
    EditCustomerDetails edit_handler = new EditCustomerDetails();
    DeleteProductDatabase delete_handler = new DeleteProductDatabase();
    private JTextField tf, tf1;
    private boolean hide_flag = false, hide_flag1 = false;
    
    
    void loadCustomerName(String text){
       v1 = edit_handler.similar_customer_name(text);
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
                            loadCustomerName("None");
                            combo1.hidePopup();
                            setModelName(new DefaultComboBoxModel(v1), text);
                        }
                        else{
                            loadCustomerName(text);
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
                        tf1.setText(String.valueOf(edit_handler.complete_customer_name(text)));
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
    void loadAadharID(String text){
        v2 = edit_handler.similar_aadhar_id(text);
        if(v2.isEmpty()){}
        else if(v2.elementAt(0) == "-1"){
           javax.swing.JOptionPane.showMessageDialog(this,"Error in fetching Aadhar IDs from database");
       }
    }
    private void setModelID(DefaultComboBoxModel mdl, String str){
        combo2.setModel(mdl);
        combo2.setSelectedIndex(-1);
        tf1.setText(str);
    }
    private static DefaultComboBoxModel getSuggestedModelID(List<String> list){
        DefaultComboBoxModel m  = new DefaultComboBoxModel();
        for (String s: list){
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
                            loadAadharID("None");
                            combo2.hidePopup();
                            setModelID(new DefaultComboBoxModel(v2), text);
                        }
                        else{
                            loadAadharID(text);
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
                    if(v2.contains(text)){
                        v2.addElement(text);
                        Collections.sort(v2);
                        setModelID(getSuggestedModelID(v2),text);
                        tf.setText(String.valueOf(edit_handler.complete_aadhar_id(text)));
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
        load = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        address = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        mobno = new javax.swing.JTextField();
        save = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        back = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("EDIT CUSTOMER DETAILS");

        load.setText("Load details");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });

        jLabel2.setText("Customer Name");

        jLabel3.setText("Customer Address");

        jLabel4.setText("Customer mobile number");

        save.setText("Save changes");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });

        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });

        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });

        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        jLabel5.setText("Customer Name, Phone number");

        jLabel6.setText("Aadhar ID");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(back))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(87, 87, 87)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(name)
                                    .addComponent(address)
                                    .addComponent(mobno, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE))
                                .addGap(120, 120, 120))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                                .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(74, 74, 74)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(reset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(save, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(load)
                            .addComponent(delete, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(150, 150, 150)
                        .addComponent(jLabel1)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(back))
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(combo1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(combo2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(load, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6))
                .addGap(71, 71, 71)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(address, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(save)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(mobno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(delete)))
                .addGap(22, 22, 22)
                .addComponent(reset)
                .addContainerGap(105, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        if(name.getText().length() == 0 || address.getText().length() == 0 || mobno.getText().length() == 0){
            javax.swing.JOptionPane.showMessageDialog(this,"Enter all the details");
        }
        else{
            if(mobno.getText().length() != 10 || mobno.getText().matches("[0-9]+") != true){
                javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid mobile number");
                return;
            }
            if(tf1.getText().length() != 12 || tf1.getText().matches("[0-9]+")!= true){
                javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Aadhar ID");
                return;
            }
            int result = edit_handler.edit_customer_details(name.getText(), tf1.getText(), address.getText(), mobno.getText());
            switch (result) {
                case 0:
                    javax.swing.JOptionPane.showMessageDialog(this,"Customer not in Database");
                    break;
                case 1:
                    javax.swing.JOptionPane.showMessageDialog(this,"Customer details updated succesfully");
                    tf1.setText("");
                    tf.setText("");
                    name.setText("");
                    mobno.setText("");
                    address.setText("");
                    break;
                case -1:
                    javax.swing.JOptionPane.showMessageDialog(this,"Database Error");
                    break;
                default:
                    break;
            }
        }
    }//GEN-LAST:event_saveActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        try{
            int result = edit_handler.delete_customer(tf1.getText());
            switch (result) {
                case 0:
                    javax.swing.JOptionPane.showMessageDialog(this,"Customer Not found");
                    break;
                case -1:
                    javax.swing.JOptionPane.showMessageDialog(this,"Customer has a history of product purchases. Cannot delete the history");
                    break;
                case 1:
                    javax.swing.JOptionPane.showMessageDialog(this,"Product Deleted Successfully");
                    name.setText("");
                    address.setText("");
                    mobno.setText("");
                    tf.setText("");
                    tf1.setText("");
                    break;
                default:
                    break;
            }
        }
        catch(Exception ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Product ID");
        }
    }//GEN-LAST:event_deleteActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        new EditCustomerDetailsFrame(choice, keeper_name).setVisible(true);
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
    }//GEN-LAST:event_resetActionPerformed

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        // TODO add your handling code here:
        try{
            if(tf1.getText().length() != 12 || tf1.getText().matches("[0-9]+") != true){
                javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Aadhar ID");
                return;
            }
            String products[] = edit_handler.fetch_customer(tf1.getText());
            if(products[0].equals("Failure")){javax.swing.JOptionPane.showMessageDialog(this,"Aadhar ID not in the database");}
            else if(products[0].equals("Error")){javax.swing.JOptionPane.showMessageDialog(this,"Database connection failure");}
            name.setText(products[0]);
            address.setText(products[1]);
            mobno.setText(products[2]);
        }
        catch(Exception ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Some error occured");
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
            java.util.logging.Logger.getLogger(EditCustomerDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EditCustomerDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EditCustomerDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EditCustomerDetailsFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(choice != 2){
                    new SPD_Mini_Project().setVisible(true);
                    return;
                }
                new EditCustomerDetailsFrame(choice, keeper_name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField address;
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton load;
    private javax.swing.JTextField mobno;
    private javax.swing.JTextField name;
    private javax.swing.JButton reset;
    private javax.swing.JButton save;
    // End of variables declaration//GEN-END:variables
}
