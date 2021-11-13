/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package spd_mini_project;
import java.lang.Math;
import javax.swing.*;
import java.util.Vector;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.EventQueue;
import java.awt.event.KeyListener;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import Backend_Files.AddProduct;
import Backend_Files.PrintFinalBill;
import java.awt.Font;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
/**
 *
 * @author Bala
 */
public final class BillProductFrame extends javax.swing.JFrame {

    /**
     * Creates new form AddProduct
     */
    static int choice = -1;
    static String keeper_name = "";
    public BillProductFrame(int choice, String name) {
        initComponents();
        this.model = (DefaultTableModel)jTable1.getModel();
        total.setEditable(false);
        total.setText("0.0");
        custid.setEditable(false);
        loadProductsName("None");
        autoSuggestName();
        loadProductsID("-1");
        autoSuggestID();
        loadCustomerMob("N");
        autoSuggestMob();
        try{table_click();}
        catch(Exception ex){javax.swing.JOptionPane.showMessageDialog(this,"Table row problem");}
        JTableHeader header = jTable1.getTableHeader();
        header.setFont(new Font("Maiandra GD", Font.BOLD, 18));
        this.keeper_name = name;
    }
    
    private Vector<String> v1 = new Vector<String>();
    private Vector<Integer> v2 = new Vector<Integer>();
    private Vector<String> v3 = new Vector<String>();
    private Vector<Vector<String>> print_products = new Vector<Vector<String>>();
    Vector row = new Vector();
    private JTextField tf, tf1, tf3;
    double total_amount = 0;
    DefaultTableModel model;
    private boolean hide_flag = false, hide_flag1 = false, hide_flag2 = false;
    AddProduct add_handler = new AddProduct();
    PrintFinalBill print_handler = new PrintFinalBill();
    int getSelectedRowForDeletion;
    
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
                        custid.setText(String.valueOf(add_handler.complete_customer_ID(text)));
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
    
    //Function for table mouseclick listen
    private void table_click(){
        jTable1.addMouseListener(new MouseListener() {
        @Override
        public void mouseReleased(MouseEvent e) {
        }
        @Override
        public void mousePressed(MouseEvent e) {
            Vector selectedRow = (Vector)model.getDataVector().elementAt(jTable1.getSelectedRow());
            row = selectedRow;
            getSelectedRowForDeletion = jTable1.getSelectedRow();
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
    private void table_addcontents(){
        try{
            String quantity = quantity_text.getText();
            String[] temp = quantity.split(" ");
            System.out.println(quantity);
            String unit = tf.getText().split(" ")[2];
            if((unit.contains("g") && temp[1].contains("g")) || (unit.contains("l") && temp[1].contains("l")) || (unit.equals(temp[1]))){
               String[] products = add_handler.add_product(Integer.valueOf(tf1.getText()),quantity);
                if(null != products[0])switch (products[0]) {
                    case "Error":
                        javax.swing.JOptionPane.showMessageDialog(this,"Database Error");return;
                    case "Failure":
                        javax.swing.JOptionPane.showMessageDialog(this,"Check the Product ID and Product Name");return;
                    case "Stock not available":
                        javax.swing.JOptionPane.showMessageDialog(this,"Stock not available... Order less or try again later");return;
                    default:
                        break;
                }
                double gn_quantity, user_quantity;
                temp = products[3].split(" ");
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
                temp = quantity.split(" ");
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
                double cal_price = (user_quantity/gn_quantity)*Float.valueOf(products[2]);
                model.addRow(new Object[] {products[0],products[1],quantity,(double)Math.round(cal_price*100)/100});
                total_amount += (double)Math.round(cal_price*100)/100;
                tf.setEditable(true);
                tf1.setEditable(true);
                tf.setText("");
                tf1.setText("");
                quantity_text.setText("");
                total.setEditable(true);
                total.setText(String.valueOf(total_amount));
                total.setEditable(false);

            }
            else{
                 javax.swing.JOptionPane.showMessageDialog(this,"Units of Product quantity and User requested quantity do not match");return;
            }
            
        }
        catch(NumberFormatException ex){
            System.out.println(ex);
            javax.swing.JOptionPane.showMessageDialog(this,"Check the quantity entered");
        }
        catch(ArrayIndexOutOfBoundsException ex){
            javax.swing.JOptionPane.showMessageDialog(this,"Please enter the units for quantity");
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

        jLabel1 = new javax.swing.JLabel();
        combo1 = new javax.swing.JComboBox<>();
        combo2 = new javax.swing.JComboBox<>();
        quantity_text = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        add_button = new javax.swing.JButton();
        edit_button = new javax.swing.JButton();
        delete_button = new javax.swing.JButton();
        print_button = new javax.swing.JButton();
        reset_button = new javax.swing.JButton();
        back_button = new javax.swing.JButton();
        total = new javax.swing.JTextField();
        quick = new javax.swing.JButton();
        combo3 = new javax.swing.JComboBox<>();
        custid = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(410, 45));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 218, 109));
        jLabel1.setText("PRODUCT BILLING");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 60, 280, 34));

        combo1.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        combo1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo1ActionPerformed(evt);
            }
        });
        getContentPane().add(combo1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 230, 190, 35));

        combo2.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        combo2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                combo2ActionPerformed(evt);
            }
        });
        getContentPane().add(combo2, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 230, 190, 33));

        quantity_text.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        quantity_text.setToolTipText("Enter the quanitity");
        quantity_text.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quantity_textActionPerformed(evt);
            }
        });
        getContentPane().add(quantity_text, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 230, 130, 39));

        jTable1.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product ID", "Product Name", "Quantity Ordered", "Price"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setRowHeight(20);
        jScrollPane1.setViewportView(jTable1);

        getContentPane().add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 320, 690, 480));

        add_button.setBackground(new java.awt.Color(20, 33, 61));
        add_button.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        add_button.setForeground(new java.awt.Color(255, 218, 109));
        add_button.setText("Add");
        add_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                add_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(add_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 360, 220, -1));

        edit_button.setBackground(new java.awt.Color(20, 33, 61));
        edit_button.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        edit_button.setForeground(new java.awt.Color(255, 218, 109));
        edit_button.setText("Edit");
        edit_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                edit_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(edit_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 420, 220, -1));

        delete_button.setBackground(new java.awt.Color(20, 33, 61));
        delete_button.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        delete_button.setForeground(new java.awt.Color(255, 218, 109));
        delete_button.setText("Delete");
        delete_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(delete_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 480, 220, -1));

        print_button.setBackground(new java.awt.Color(20, 33, 61));
        print_button.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        print_button.setForeground(new java.awt.Color(255, 218, 109));
        print_button.setText("Print");
        print_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                print_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(print_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 540, 220, -1));

        reset_button.setBackground(new java.awt.Color(20, 33, 61));
        reset_button.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        reset_button.setForeground(new java.awt.Color(255, 218, 109));
        reset_button.setText("Reset");
        reset_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reset_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(reset_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 600, 220, -1));

        back_button.setBackground(new java.awt.Color(20, 33, 61));
        back_button.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        back_button.setForeground(new java.awt.Color(255, 218, 109));
        back_button.setText("Back");
        back_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                back_buttonActionPerformed(evt);
            }
        });
        getContentPane().add(back_button, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 90, 30));

        total.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        getContentPane().add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 830, 170, 40));

        quick.setBackground(new java.awt.Color(20, 33, 61));
        quick.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        quick.setForeground(new java.awt.Color(255, 218, 109));
        quick.setText("Quick Register");
        quick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickActionPerformed(evt);
            }
        });
        getContentPane().add(quick, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 720, 220, -1));

        combo3.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        getContentPane().add(combo3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 160, 190, 34));

        custid.setFont(new java.awt.Font("Maiandra GD", 0, 16)); // NOI18N
        getContentPane().add(custid, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 160, 190, 34));

        jLabel2.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 218, 109));
        jLabel2.setText("Customer Phone no");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, 220, -1));

        jLabel3.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 218, 109));
        jLabel3.setText("Customer ID");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 120, -1));

        jLabel4.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 218, 109));
        jLabel4.setText("Product Name, Quantity");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 240, 220, -1));

        jLabel5.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 218, 109));
        jLabel5.setText("Product ID");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, 110, -1));

        jLabel6.setFont(new java.awt.Font("Maiandra GD", 1, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 218, 109));
        jLabel6.setText("Quantity");
        getContentPane().add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(820, 240, 80, -1));

        jLabel7.setFont(new java.awt.Font("Maiandra GD", 1, 24)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 218, 109));
        jLabel7.setText("Total Price");
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(420, 840, 130, -1));

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/spd_mini_project/Banaras Supermarket.jpg"))); // NOI18N
        getContentPane().add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 0, 280, 190));

        jLabel8.setBackground(new java.awt.Color(20, 33, 61));
        jLabel8.setOpaque(true);
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1080, 900));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void reset_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reset_buttonActionPerformed
        // TODO add your handling code here:
        new BillProductFrame(choice, keeper_name).setVisible(true);
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
    }//GEN-LAST:event_reset_buttonActionPerformed

    private void add_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_add_buttonActionPerformed
        // TODO add your handling code here:
        if(quantity_text.getText().equals("") || tf.getText().equals("") || tf1.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Please enter all the required details");
            return;
        }
        int row_count = jTable1.getRowCount();
        for(int i=0;i<row_count;i++){
            if(Integer.parseInt(tf1.getText()) == Integer.parseInt((String)((Vector<String>)model.getDataVector().elementAt(i)).elementAt(0))){
                javax.swing.JOptionPane.showMessageDialog(this,"Product already exists in the table... Click on Edit option to change the quantity");
                return;
            }
        }
        table_addcontents();
    }//GEN-LAST:event_add_buttonActionPerformed

    private void combo2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo2ActionPerformed

    private void combo1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_combo1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_combo1ActionPerformed

    private void quantity_textActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quantity_textActionPerformed
        // TODO add your handling code here: 
    }//GEN-LAST:event_quantity_textActionPerformed

    private void edit_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_edit_buttonActionPerformed
        // TODO add your handling code here:
        String quantity = add_handler.quantity_fetch(Integer.parseInt(String.valueOf(row.elementAt(0))));
        if(quantity.equals("Error")){
            javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
        }
        tf1.setText(String.valueOf(row.elementAt(0)));
        tf.setText(String.valueOf(row.elementAt(1))+" "+quantity);
        tf1.setEditable(false);
        tf.setEditable(false);
        quantity_text.setText(String.valueOf(row.elementAt(2)));
        total_amount -= ((Number) row.elementAt(3)).doubleValue();
        total.setEditable(true);
        total.setText(String.valueOf(total_amount));
        total.setEditable(true);
        model.removeRow(getSelectedRowForDeletion);
    }//GEN-LAST:event_edit_buttonActionPerformed

    private void delete_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_buttonActionPerformed
        // TODO add your handling code here:
        total_amount -= ((Number) row.elementAt(3)).doubleValue();
        total.setEditable(true);
        total.setText(String.valueOf(total_amount));
        total.setEditable(true);
        model.removeRow(getSelectedRowForDeletion);
    }//GEN-LAST:event_delete_buttonActionPerformed

    private void quickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quickActionPerformed
        // TODO add your handling code here:
        RegisterCustomerForm reg_frame = new RegisterCustomerForm(true, choice, keeper_name);
        reg_frame.setVisible(true);
    }//GEN-LAST:event_quickActionPerformed

    private void print_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_print_buttonActionPerformed
        // TODO add your handling code here:
        if(tf3.getText().equals("") || custid.getText().equals("")){
            javax.swing.JOptionPane.showMessageDialog(this,"Please enter the customer ID and name");
            return;
        }
        if(model.getRowCount() == 0){
            javax.swing.JOptionPane.showMessageDialog(this,"No products added");
            return;
        }
        int row_count = jTable1.getRowCount();
        for(int i=0;i<row_count;i++){
            Vector<String> selectedRow = (Vector<String>)model.getDataVector().elementAt(i);
            print_products.addElement((Vector<String>)selectedRow.clone());
        }
        int res = print_handler.final_bill(print_products, Float.valueOf(total.getText()), Integer.valueOf(custid.getText()), tf3.getText(), keeper_name);
        if(res == 1){
            javax.swing.JOptionPane.showMessageDialog(this,"Bill generation sucessfull");
            tf.setText("");
            tf1.setText("");
            tf3.setText("");
            total.setText("");
            custid.setText("");
            model.setRowCount(0);
            quantity_text.setText("");
        }
        else if(res == 0){
            javax.swing.JOptionPane.showMessageDialog(this,"Product ID does no exist");
        }
        else if(res == 2){
            javax.swing.JOptionPane.showMessageDialog(this,"File creation failed");
        }
        else if(res == -1){
            javax.swing.JOptionPane.showMessageDialog(this,"Database or IO failure");
        }
    }//GEN-LAST:event_print_buttonActionPerformed

    private void back_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_back_buttonActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        try {
                this.finalize();
            } catch (Throwable ex) {
                javax.swing.JOptionPane.showMessageDialog(this,"Application failure");
            }
        new ShopkeeperChoice(choice, keeper_name).setVisible(true);
    }//GEN-LAST:event_back_buttonActionPerformed

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
            java.util.logging.Logger.getLogger(BillProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(BillProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(BillProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(BillProductFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                //if(choice != 2){
                //    new SPD_Mini_Project().setVisible(true);
                //    return;
                //}
                new BillProductFrame(choice, keeper_name).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton add_button;
    private javax.swing.JButton back_button;
    private javax.swing.JComboBox<String> combo1;
    private javax.swing.JComboBox<String> combo2;
    private javax.swing.JComboBox<String> combo3;
    private javax.swing.JTextField custid;
    private javax.swing.JButton delete_button;
    private javax.swing.JButton edit_button;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton print_button;
    private javax.swing.JTextField quantity_text;
    private javax.swing.JButton quick;
    private javax.swing.JButton reset_button;
    private javax.swing.JTextField total;
    // End of variables declaration//GEN-END:variables
}
