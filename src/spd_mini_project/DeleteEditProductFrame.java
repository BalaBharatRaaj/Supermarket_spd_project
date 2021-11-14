/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;

import Backend_Files.AddProduct;
import Backend_Files.DeleteProductDatabase;
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
public class DeleteEditProductFrame extends javax.swing.JFrame {

    /**
     * Creates new form EditProduct
     */
    static int choice = -1;
    static String name = "";
    public DeleteEditProductFrame(int choice, String name) {
        initComponents();
        loadProductsName("None");
        autoSuggestName();
        loadProductsID("-1");
        autoSuggestID();
        this.choice = choice;
        this.name = name;
    }
    
    private Vector<String> v1 = new Vector<String>();
    private Vector<Integer> v2 = new Vector<Integer>();
    AddProduct add_handler = new AddProduct();
    EditProductDatabase edit_handler = new EditProductDatabase();
    DeleteProductDatabase delete_handler = new DeleteProductDatabase();
    private JTextField tf, tf1;
    private boolean hide_flag = false, hide_flag1 = false;
    
    //Functions for the Product Name ComboBox
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
                            loadProductsID("None");
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
        product_name = new javax.swing.JTextField();
        product_quantity = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        stock = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        save = new javax.swing.JButton();
        delete = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        back = new javax.swing.JButton();
        load = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        minlimit = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(450, 170));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 218, 109));
        jLabel1.setText("DELETE OR EDIT PRODUCT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 60, -1, -1));

        combo1.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        combo1.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 160, 200, 35));

        combo2.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        combo2.setForeground(new java.awt.Color(20, 33, 61));
        combo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo2ActionPerformed(evt);
            }
        });
        getContentPane().add(combo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 150, 35));

        product_name.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        product_name.setForeground(new java.awt.Color(20, 33, 61));
        product_name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_nameActionPerformed(evt);
            }
        });
        getContentPane().add(product_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 250, 210, 35));

        product_quantity.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        product_quantity.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(product_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 340, 210, 35));

        price.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        price.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 430, 210, 35));

        stock.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        stock.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 520, 210, 35));

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 218, 109));
        jLabel2.setText("Product Name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 250, 160, 35));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 109));
        jLabel3.setText("Quantity");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 340, -1, 35));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 218, 109));
        jLabel4.setText("Product Stock");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 520, 130, 35));

        jLabel5.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 218, 109));
        jLabel5.setText("Price");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 430, 93, -1));

        save.setBackground(new java.awt.Color(20, 33, 61));
        save.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        save.setForeground(new java.awt.Color(255, 218, 109));
        save.setText("Save Changes");
        save.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveActionPerformed(evt);
            }
        });
        getContentPane().add(save, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 300, 175, -1));

        delete.setBackground(new java.awt.Color(20, 33, 61));
        delete.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        delete.setForeground(new java.awt.Color(255, 218, 109));
        delete.setText("Delete");
        delete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteActionPerformed(evt);
            }
        });
        getContentPane().add(delete, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 430, 175, -1));

        reset.setBackground(new java.awt.Color(20, 33, 61));
        reset.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 218, 109));
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        getContentPane().add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 570, 175, -1));

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

        load.setBackground(new java.awt.Color(20, 33, 61));
        load.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        load.setForeground(new java.awt.Color(255, 218, 109));
        load.setText("Load Results");
        load.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadActionPerformed(evt);
            }
        });
        getContentPane().add(load, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 160, 160, 35));

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 218, 109));
        jLabel6.setText("Stock Minimum Limit ");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 610, -1, 35));

        minlimit.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        minlimit.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(minlimit, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 610, 210, 35));

        jLabel7.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 218, 109));
        jLabel7.setText("Product Name, Quantity");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 160, -1, 30));

        jLabel8.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 218, 109));
        jLabel8.setText("Product ID");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, -1, 30));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spd_mini_project/Banaras Supermarket.jpg"))); // NOI18N
        jLabel10.setText("jLabel10");
        getContentPane().add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, -10, 260, 140));

        jLabel9.setBackground(new java.awt.Color(20, 33, 61));
        jLabel9.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        jLabel9.setOpaque(true);
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 980, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void combo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo2ActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_combo2ActionPerformed

    private void saveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveActionPerformed
        // TODO add your handling code here:
        try{
            String[] temp = product_quantity.getText().split(" ");
            String[] temp1 = minlimit.getText().split(" ");
            double quantity = Double.parseDouble(temp[0]);
            String unit = temp[1];
            temp = stock.getText().split(" ");
            quantity = Double.parseDouble(temp[0]);
            quantity = Double.parseDouble(price.getText());
            if((unit.contains("g") && temp[1].contains("g") && temp1[1].contains("g")) || (unit.contains("l") && temp[1].contains("l")) && temp1[1].contains("l") || (unit.equals(temp[1]) && unit.equals(temp1[1]))){
                int result = edit_handler.edit_product_database(product_name.getText(), Integer.valueOf(tf1.getText()), product_quantity.getText(), quantity, stock.getText(), minlimit.getText());
                switch (result) {
                    case 0:
                        javax.swing.JOptionPane.showMessageDialog(this,"Product Not found");
                        break;
                    case -1:
                        javax.swing.JOptionPane.showMessageDialog(this,"Database Failure");
                        break;
                    case 1:
                        javax.swing.JOptionPane.showMessageDialog(this,"Product Updated Successfully");
                        product_name.setText("");
                        price.setText("");
                        product_quantity.setText("");
                        stock.setText("");
                        minlimit.setText("");
                        tf.setText("");
                        tf1.setText("");
                        break;
                    case 2:
                        javax.swing.JOptionPane.showMessageDialog(this,"Selected product cannot be deleted as it has been ordered by customers");
                    default:
                        break;
                }
            }
            else{
                javax.swing.JOptionPane.showMessageDialog(this,"Check the units of stock, quantity and stock minimum limit");
            }
        }
        catch(NumberFormatException ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid number");
        }
        catch(ArrayIndexOutOfBoundsException ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Please enter the units for quantity and stock");
        }
    }//GEN-LAST:event_saveActionPerformed

    private void loadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadActionPerformed
        // TODO add your handling code here:
        try{
            String products[] = edit_handler.fetch_product(Integer.valueOf(tf1.getText()));
            if(products[0].equals("Failure")){javax.swing.JOptionPane.showMessageDialog(this,"Product ID not in the database");return;}
            else if(products[0].equals("Error")){javax.swing.JOptionPane.showMessageDialog(this,"Database connection failure");return;}
            product_name.setText(products[0]);
            price.setText(products[1]);
            product_quantity.setText(products[2]);
            stock.setText(products[3]);
            minlimit.setText(products[4]);
        }
        catch(NumberFormatException ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Product ID");
        }
    }//GEN-LAST:event_loadActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        new DeleteEditProductFrame(choice,name).setVisible(true);
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
    }//GEN-LAST:event_resetActionPerformed

    private void deleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteActionPerformed
        // TODO add your handling code here:
        try{
            int result = delete_handler.delete_product(Integer.valueOf(tf1.getText()));
            switch (result) {
                case 0:
                    javax.swing.JOptionPane.showMessageDialog(this,"Product Not found");
                    break;
                case -1:
                    javax.swing.JOptionPane.showMessageDialog(this,"Database Failure");
                    break;
                case 1:
                    javax.swing.JOptionPane.showMessageDialog(this,"Product Deleted Successfully");
                    product_name.setText("");
                    price.setText("");
                    product_quantity.setText("");
                    stock.setText("");
                    minlimit.setText("");
                    tf.setText("");
                    tf1.setText("");
                    break;
                default:
                    break;
            }
        }
        catch(NumberFormatException ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Enter a valid Product ID");
        }
    }//GEN-LAST:event_deleteActionPerformed

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

    private void product_nameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_nameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_product_nameActionPerformed

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
            java.util.logging.Logger.getLogger(DeleteEditProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DeleteEditProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DeleteEditProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DeleteEditProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                if(choice != 1 || name.equals("")){
                    new SPD_Mini_Project().setVisible(true);
                    return;
                }
                new DeleteEditProductFrame(choice,name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JButton delete;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JButton load;
    private javax.swing.JTextField minlimit;
    private javax.swing.JTextField price;
    private javax.swing.JTextField product_name;
    private javax.swing.JTextField product_quantity;
    private javax.swing.JButton reset;
    private javax.swing.JButton save;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables
}
