/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;
import Backend_Files.AddProductDatabase;
/**
 *
 * @author Bala
 */
public class AddProductFrame extends javax.swing.JFrame {

    /**
     * Creates new form BillOrderGeneratorFrame
     */
    static int choice = -1;
    static String name = "";
    public AddProductFrame(int choice, String name) {
        initComponents();
        this.choice = choice;
        this.name = name;
    }

    AddProductDatabase add_db_handler = new AddProductDatabase();
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        product_name = new javax.swing.JTextField();
        product_quantity = new javax.swing.JTextField();
        price = new javax.swing.JTextField();
        minimum = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        add_product = new javax.swing.JButton();
        reset = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        stock = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(590, 225));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 218, 109));
        jLabel1.setText("ADD PRODUCT");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 50, -1, -1));

        product_name.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        product_name.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(product_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 150, 162, -1));

        product_quantity.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        product_quantity.setForeground(new java.awt.Color(20, 33, 61));
        product_quantity.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                product_quantityActionPerformed(evt);
            }
        });
        getContentPane().add(product_quantity, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 230, 162, -1));

        price.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        price.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(price, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 162, -1));

        minimum.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        getContentPane().add(minimum, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 470, 162, 30));

        jLabel8.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 218, 109));
        jLabel8.setText("Enter the minimum stock quantity (with units)");
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 470, 420, -1));

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 218, 109));
        jLabel2.setText("Enter the product name");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 150, -1, 30));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 109));
        jLabel3.setText("Enter the product quantity (with units)");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 230, -1, 30));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 218, 109));
        jLabel4.setText("Enter the price of the product");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 310, -1, 30));

        add_product.setBackground(new java.awt.Color(20, 33, 61));
        add_product.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        add_product.setForeground(new java.awt.Color(255, 218, 109));
        add_product.setText("Add Product");
        add_product.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_productActionPerformed(evt);
            }
        });
        getContentPane().add(add_product, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 530, 160, -1));

        reset.setBackground(new java.awt.Color(20, 33, 61));
        reset.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        reset.setForeground(new java.awt.Color(255, 218, 109));
        reset.setText("Reset");
        reset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetActionPerformed(evt);
            }
        });
        getContentPane().add(reset, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 530, 120, -1));

        jButton3.setBackground(new java.awt.Color(20, 33, 61));
        jButton3.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 218, 109));
        jButton3.setText("Back");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(31, 29, -1, -1));

        jLabel5.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 218, 109));
        jLabel5.setText("Enter the total stock of the product (with units)");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 390, 430, 30));

        stock.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        stock.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(stock, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 390, 162, 30));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spd_mini_project/Banaras Supermarket - Register Form.jpg"))); // NOI18N
        jLabel7.setText("jLabel7");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, -10, 260, 140));

        jLabel6.setBackground(new java.awt.Color(20, 33, 61));
        jLabel6.setOpaque(true);
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 720, 610));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void product_quantityActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_product_quantityActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_product_quantityActionPerformed

    private void resetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetActionPerformed
        // TODO add your handling code here:
        new AddProductFrame(choice,name).setVisible(true);
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
    }//GEN-LAST:event_resetActionPerformed

    private void add_productActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_productActionPerformed
        // TODO add your handling code here:
        try{
            if(product_name.getText().equals("") || product_quantity.getText().equals("")|| price.getText().equals("") || stock.getText().equals("") || minimum.getText().equals("")){
                javax.swing.JOptionPane.showMessageDialog(this,"Fill in all the fields");
                return;
            }
            String[] temp1 = minimum.getText().split(" ");
            String[] temp = product_quantity.getText().split(" ");
            double quantity = Double.parseDouble(temp[0]);
            String unit = temp[1];
            temp = stock.getText().split(" ");
            quantity = Double.parseDouble(temp[0]);
            quantity = Double.parseDouble(price.getText());
            if((unit.contains("g") && temp[1].contains("g") && temp1[1].contains("g")) || (unit.contains("l") && temp[1].contains("l")&& temp1[1].contains("l")) || (unit.equals(temp[1]) && unit.equals(temp1[1]))){
                int result = add_db_handler.add_product_database(product_name.getText(), product_quantity.getText(), quantity, stock.getText(), minimum.getText());
                switch (result) {
                    case -1:
                        javax.swing.JOptionPane.showMessageDialog(this,"Error");
                        break;
                    case 0:
                        javax.swing.JOptionPane.showMessageDialog(this,"Product name and quantity exists");
                        break;
                    case 1:
                        javax.swing.JOptionPane.showMessageDialog(this,"Product added successfully");
                        break;
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
            javax.swing.JOptionPane.showMessageDialog(this,"Please enter the units for quantity, stock and stock minimum limit");
        }
    }//GEN-LAST:event_add_productActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
        new AdminChoice(choice, name).setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

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
            java.util.logging.Logger.getLogger(AddProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AddProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AddProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AddProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new AddProductFrame(choice,name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_product;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JTextField minimum;
    private javax.swing.JTextField price;
    private javax.swing.JTextField product_name;
    private javax.swing.JTextField product_quantity;
    private javax.swing.JButton reset;
    private javax.swing.JTextField stock;
    // End of variables declaration//GEN-END:variables
}
