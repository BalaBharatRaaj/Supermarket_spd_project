/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;

import Backend_Files.Login_Backend;

/**
 *
 * @author Bala
 */
public class Login extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public static int choice = -1;
    Login_Backend login_handler = new Login_Backend();
    public Login(){
        //
    }
    public Login(int choice) {
        this.choice = choice;
        initComponents();
        if(choice == 2){
            jLabel1.setText("SHOPKEEPER");
        }
        else if(choice == 1){
            jLabel1.setText("ADMINISTRATOR");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        back = new javax.swing.JButton();
        pass = new javax.swing.JPasswordField();
        show = new javax.swing.JCheckBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(600, 235));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 218, 109));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("LOGIN");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 200, 40));

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 218, 109));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 80, 240, 40));

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 218, 109));
        jLabel2.setText("Email Address");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, -1, -1));

        email.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        email.setForeground(new java.awt.Color(20, 33, 61));
        email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailActionPerformed(evt);
            }
        });
        getContentPane().add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 220, -1));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 109));
        jLabel3.setText("Password");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 280, 101, -1));

        login.setBackground(new java.awt.Color(20, 33, 61));
        login.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        login.setForeground(new java.awt.Color(255, 218, 109));
        login.setText("Login");
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });
        getContentPane().add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 380, 100, -1));

        back.setBackground(new java.awt.Color(20, 33, 61));
        back.setFont(new java.awt.Font("Maiandra GD", 1, 20)); // NOI18N
        back.setForeground(new java.awt.Color(255, 218, 109));
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });
        getContentPane().add(back, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 80, -1));

        pass.setFont(new java.awt.Font("Maiandra GD", 0, 18)); // NOI18N
        pass.setForeground(new java.awt.Color(20, 33, 61));
        getContentPane().add(pass, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 280, 220, -1));

        show.setBackground(new java.awt.Color(20, 33, 61));
        show.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        show.setForeground(new java.awt.Color(255, 218, 109));
        show.setText("Show Password");
        show.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showActionPerformed(evt);
            }
        });
        getContentPane().add(show, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 280, 180, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spd_mini_project/Banaras Supermarket.jpg"))); // NOI18N
        jLabel5.setText("jLabel5");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 0, 270, 140));

        jLabel4.setBackground(new java.awt.Color(20, 33, 61));
        jLabel4.setOpaque(true);
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 680, 510));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_emailActionPerformed

    private void showActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showActionPerformed
        // TODO add your handling code here:
        if(show.isSelected() == true){
            pass.setEchoChar((char)0);
        }
        else{
            pass.setEchoChar('*');
        }
    }//GEN-LAST:event_showActionPerformed

    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        // TODO add your handling code here:
        if(email.getText().contains("@") && pass.getPassword().length != 0){
            String name = login_handler.account_check(email.getText(), new String(pass.getPassword()), choice);
            if(name.equals("-1")){
                javax.swing.JOptionPane.showMessageDialog(this,"Password incorrect or incorrect choice");
                return;
            }
            else if(name.equals("Email not found")){
                javax.swing.JOptionPane.showMessageDialog(this,"Email not found");
                return;
            }
            else if(name.equals("Failure")){
                javax.swing.JOptionPane.showMessageDialog(this,"Database Error");
                return;
            }
            else{
                if(choice == 1){
                    new AdminChoice(choice, name).setVisible(true);
                }
                else if(choice == 2){
                    new ShopkeeperChoice(choice, name).setVisible(true);
                }
                this.setVisible(false);
                try {
                        this.finalize();
                    } catch (Throwable ex) {
                        javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
                    }
                return;
            }
        }
        else{
            javax.swing.JOptionPane.showMessageDialog(this,"Check the mail ID and password");
            return;
        }
        
    }//GEN-LAST:event_loginActionPerformed

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
            this.finalize();
        } catch (Throwable ex) {
            javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
        }
        new SPD_Mini_Project().setVisible(true);
        return;
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
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                    if(choice == -1){
                        new SPD_Mini_Project().setVisible(true);
                        return;
                    }
                    new Login(choice).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton back;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton login;
    private javax.swing.JPasswordField pass;
    private javax.swing.JCheckBox show;
    // End of variables declaration//GEN-END:variables
}
