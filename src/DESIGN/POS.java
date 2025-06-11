/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package DESIGN;

import DATABASE.DataControl;
import DESIGN.items.Cart;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author HP
 */
public class POS extends javax.swing.JFrame {
    private  int total = 0;
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(POS.class.getName());
    public  JPanel mainPanel = new JPanel();
    private static POS instance;
    static int change;
    private  ArrayList<String> ids = new ArrayList<>();
    private static ArrayList<String> names = new ArrayList<>();
    private static ArrayList<Integer> qtys  = new ArrayList<>();
    private static ArrayList<Integer> prices  = new ArrayList<>();
    String title = "<html>&nbsp;&nbsp;&nbsp;Momshies Homemade Products <br>&nbsp;&nbsp;&nbsp;4114 Governor's Drive, Brgy, <br>&nbsp;&nbsp;&nbsp;Dasmari\u00f1as, Cavite <br>&nbsp;&nbsp;&nbsp;Luzon, Philippines <br>&nbsp;&nbsp;&nbsp;+63 123456789 <br>-------------------------------------------------------------------------------------------------<br><br><html>";
    /**
     * Creates new form POS
     */
    public POS() {
        mainPanel = new JPanel();
        instance = this;
        initComponents();
        setExtendedState(JFrame.MAXIMIZED_BOTH);
    }
    public void payGcash(){
        errorMsg.setText("");
        printGcashPanel();
        try (DataControl dc = new DataControl()) {
            dc.saveIncome(total);
        } catch (SQLException ex) {
            return;
        }
        clearCart();
        loadPanel();
        resetLabel();
    }
    public static POS getInstance() {
        return instance;
    }
    public void deleteCart(int id){
        int index = ids.indexOf(String.valueOf(id));
        if (index != -1) {
            ids.remove(index);
            names.remove(index);
            qtys.remove(index);
            prices.remove(index);
            recalculateTotal();
            restartLabel();
            loadPanel();
        }
    }
    public void clearCart(){
        ids.clear();
        names.clear();
        qtys.clear();
        prices.clear();
    }
    public void addToCart(String id, String name, int qty, int price){
        if (ids.contains(id)) {
            int index = ids.indexOf(id);
            qtys.set(index, qtys.get(index) + qty);
        } else {
            ids.add(id);
            names.add(name);
            qtys.add(qty);
            prices.add(price);
        }
    }
    public void printMyPanel(){
        printPanel.removeAll();
        printPanel.add(new JLabel(title) {{
            setAlignmentX(LEFT_ALIGNMENT);
        }});
        for (int i = 0; i < ids.size(); i++) {
            String order = "<html>&nbsp;&nbsp;&nbsp;id: " + ids.get(i)+ " | " + names.get(i) + " " + qtys.get(i) + "pcs ₱" +(qtys.get(i) * prices.get(i)) +"<html>";
            printPanel.add(new JLabel(order) {{
                setAlignmentX(LEFT_ALIGNMENT);
            }});
        }
        printPanel.add(new JLabel("<html><br><br>-------------------------------------------------------------------------------------------------<br>&nbsp;&nbsp;&nbsp;Total : "+total +"<br>&nbsp;&nbsp;&nbsp;Cash Amount : "+ cashPaid.getText() +"<br>&nbsp;&nbsp;&nbsp;Change :"+ change+" <html>") {{
                setAlignmentX(LEFT_ALIGNMENT);
            }});
        printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.Y_AXIS));
        printPanel.revalidate(); 
        printPanel.repaint(); 
    }
    public void printGcashPanel(){
        printPanel.removeAll();
        printPanel.add(new JLabel(title) {{
            setAlignmentX(LEFT_ALIGNMENT);
        }});
        for (int i = 0; i < ids.size(); i++) {
            String order = "<html>&nbsp;&nbsp;&nbsp;id: " + ids.get(i)+ " | " + names.get(i) + " " + qtys.get(i) + "pcs ₱" +(qtys.get(i) * prices.get(i)) +"<html>";
            printPanel.add(new JLabel(order) {{
                setAlignmentX(LEFT_ALIGNMENT);
            }});
        }
        printPanel.add(new JLabel("<html><br><br>-------------------------------------------------------------------------------------------------<br>&nbsp;&nbsp;&nbsp;Total : "+total +"<br>&nbsp;&nbsp;&nbsp;Gcash Payment : "+ total +"<br>&nbsp;&nbsp;&nbsp;<html>") {{
                setAlignmentX(LEFT_ALIGNMENT);
            }});
        printPanel.setLayout(new BoxLayout(printPanel, BoxLayout.Y_AXIS));
        printPanel.revalidate(); 
        printPanel.repaint(); 
    }
    public void loadPanel() {
         mainPanel.removeAll(); 

        for (int i = 0; i < ids.size(); i++) {
            Cart itemPanel = new Cart(
                ids.get(i), names.get(i), qtys.get(i), prices.get(i), getInstance()
            );
            mainPanel.add(itemPanel);
        }

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.revalidate(); 
        mainPanel.repaint(); 
    }
        // Used by CartItemPanel to update quantity
    public void updateItemQty(String id, int newQty) {
         int index = ids.indexOf(id);
        if (index != -1) {
            qtys.set(index, newQty);
            recalculateTotal();
            restartLabel();
        }
    }

    public void removeItem(String id) {
        deleteCart(Integer.parseInt(id));
    }

    private void recalculateTotal() {
        total = 0;
        for (int i = 0; i < ids.size(); i++) {
            total += qtys.get(i) * prices.get(i);
        }
    }

    public void setTheLabel(String id){
        int index = ids.indexOf(id);
        int qty = qtys.get(index);
        int price = prices.get(index);
        total += qty * price;
    }

    public void setLabel(int price){
        total += price;
        subtotallabel.setText("TOTAL: " + total);
        errorMsg.setText("");
        cashPaid.setText("");
    }
    public void restartLabel(){
        subtotallabel.setText("TOTAL: " + total);
        errorMsg.setText("");
        cashPaid.setText("");
    }
    public void resetLabel(){
        total = 0;
        subtotallabel.setText("TOTAL: " + total);
        errorMsg.setText("");
        cashPaid.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        bread = new javax.swing.JButton();
        brownies = new javax.swing.JButton();
        carbonara = new javax.swing.JButton();
        mac = new javax.swing.JButton();
        cookie = new javax.swing.JButton();
        lasagna = new javax.swing.JButton();
        putoFlan = new javax.swing.JButton();
        malabon = new javax.swing.JButton();
        puto = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        subtotallabel = new javax.swing.JLabel();
        scrollPanel = new javax.swing.JScrollPane(mainPanel);
        jLabel2 = new javax.swing.JLabel();
        payment = new javax.swing.JComboBox<>();
        cashPaid = new javax.swing.JTextField();
        cashAmnt = new javax.swing.JLabel();
        errorMsg = new javax.swing.JLabel();
        clearBtn = new DESIGN.items.MyButton();
        myButton1 = new DESIGN.items.MyButton();
        printPanel = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(251, 158, 58));

        jPanel3.setBackground(new java.awt.Color(251, 158, 58));

        jPanel2.setBackground(new java.awt.Color(251, 158, 58));
        jPanel2.setLayout(new java.awt.GridLayout(3, 3, 8, 8));

        bread.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/bread.png"))); // NOI18N
        bread.setText("Bread");
        bread.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        bread.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bread.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        bread.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bread.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                breadActionPerformed(evt);
            }
        });
        jPanel2.add(bread);

        brownies.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/brownies.png"))); // NOI18N
        brownies.setText("Brownies");
        brownies.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        brownies.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        brownies.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        brownies.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        brownies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browniesActionPerformed(evt);
            }
        });
        jPanel2.add(brownies);

        carbonara.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/carbonara.png"))); // NOI18N
        carbonara.setText("Carbonara");
        carbonara.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        carbonara.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        carbonara.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        carbonara.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        carbonara.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                carbonaraActionPerformed(evt);
            }
        });
        jPanel2.add(carbonara);

        mac.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/mac.png"))); // NOI18N
        mac.setText("Mac");
        mac.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        mac.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mac.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mac.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                macActionPerformed(evt);
            }
        });
        jPanel2.add(mac);

        cookie.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/cookie.png"))); // NOI18N
        cookie.setText("Cookie");
        cookie.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cookie.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cookie.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        cookie.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cookie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cookieActionPerformed(evt);
            }
        });
        jPanel2.add(cookie);

        lasagna.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/lasagna.png"))); // NOI18N
        lasagna.setText("Lasagna");
        lasagna.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lasagna.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lasagna.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lasagna.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        lasagna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lasagnaActionPerformed(evt);
            }
        });
        jPanel2.add(lasagna);

        putoFlan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/putoflan.png"))); // NOI18N
        putoFlan.setText("PutoFlan");
        putoFlan.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        putoFlan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        putoFlan.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        putoFlan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        putoFlan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                putoFlanActionPerformed(evt);
            }
        });
        jPanel2.add(putoFlan);

        malabon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/malabon.png"))); // NOI18N
        malabon.setText("Malabon");
        malabon.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        malabon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        malabon.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        malabon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        malabon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                malabonActionPerformed(evt);
            }
        });
        jPanel2.add(malabon);

        puto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/FE/idk/puto.png"))); // NOI18N
        puto.setText("Puto");
        puto.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        puto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        puto.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        puto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        puto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                putoActionPerformed(evt);
            }
        });
        jPanel2.add(puto);

        jPanel4.setBackground(new java.awt.Color(251, 158, 58));

        subtotallabel.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        subtotallabel.setText("TOTAL: " + total);

        scrollPanel.setMaximumSize(new java.awt.Dimension(398, 410));
        scrollPanel.setMinimumSize(new java.awt.Dimension(398, 410));
        scrollPanel.setPreferredSize(new java.awt.Dimension(398, 410));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel2.setText("PAYMENT METHOD :");

        payment.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cash", "Gcash" }));
        payment.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                paymentActionPerformed(evt);
            }
        });

        cashPaid.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cashPaidActionPerformed(evt);
            }
        });

        cashAmnt.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        cashAmnt.setText("CASH AMOUNT :");

        errorMsg.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        errorMsg.setForeground(new java.awt.Color(153, 0, 0));

        clearBtn.setForeground(new java.awt.Color(0, 0, 0));
        clearBtn.setText("CLEAR");
        clearBtn.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        clearBtn.setHover(new java.awt.Color(153, 153, 153));
        clearBtn.setRadius(12);
        clearBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearBtnActionPerformed(evt);
            }
        });

        myButton1.setForeground(new java.awt.Color(0, 0, 0));
        myButton1.setText("PAY");
        myButton1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        myButton1.setHover(new java.awt.Color(204, 204, 204));
        myButton1.setRadius(16);
        myButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                myButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cashAmnt, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(errorMsg, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(cashPaid, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(payment, 0, 187, Short.MAX_VALUE)
                            .addComponent(myButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(clearBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(subtotallabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(scrollPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(scrollPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 451, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(clearBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(subtotallabel, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(payment, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cashPaid)
                    .addComponent(cashAmnt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(myButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(errorMsg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(4, 4, 4))
        );

        printPanel.setMaximumSize(new java.awt.Dimension(392, 646));
        printPanel.setMinimumSize(new java.awt.Dimension(392, 646));
        printPanel.setPreferredSize(new java.awt.Dimension(392, 646));

        javax.swing.GroupLayout printPanelLayout = new javax.swing.GroupLayout(printPanel);
        printPanel.setLayout(printPanelLayout);
        printPanelLayout.setHorizontalGroup(
            printPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 392, Short.MAX_VALUE)
        );
        printPanelLayout.setVerticalGroup(
            printPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 382, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(45, 45, 45)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(printPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 646, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(printPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(36, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void paymentActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_paymentActionPerformed
        if(payment.getSelectedItem().equals("Gcash")){
            cashAmnt.setVisible(false);
            cashPaid.setVisible(false);
        }else{
            cashAmnt.setVisible(true);
            cashPaid.setVisible(true);
        }
    }//GEN-LAST:event_paymentActionPerformed

    private void myButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_myButton1ActionPerformed
        int select = payment.getSelectedIndex();
        if (select == 0) { // Cash
            String cash = cashPaid.getText();
            if (cash.isEmpty()) {
                errorMsg.setText("Add Cash");
                return;
            }

            int cashInt;
            try {
                cashInt = Integer.parseInt(cash);
            } catch (NumberFormatException e) {
                errorMsg.setText("Invalid input");
                return;
            }

            if (total > cashInt) {
                errorMsg.setText("Insufficient cash");
            } else {
                change = cashInt - total;
                errorMsg.setText("");
                printMyPanel();
                try (DataControl dc = new DataControl()) {
                    dc.saveIncome(total);
                } catch (SQLException ex) {
                    return;
                }
                clearCart();
                loadPanel();
                resetLabel();
            }
        } else {
            errorMsg.setText("");
            if(total >0){
                new Gcash(this).setVisible(true);
            }else{
                errorMsg.setText("Add product");
            }
            
        }
    }//GEN-LAST:event_myButton1ActionPerformed

    private void breadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_breadActionPerformed
        setLabel(100);
        addToCart("1", "Bread", 1, 100);
        loadPanel();
    }//GEN-LAST:event_breadActionPerformed

    private void browniesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browniesActionPerformed
        setLabel(130);
        addToCart("2", "Brownies", 1, 130);
        loadPanel();
    }//GEN-LAST:event_browniesActionPerformed

    private void carbonaraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_carbonaraActionPerformed
        setLabel(140);
        addToCart("3", "Carbonara", 1, 140);
        loadPanel();
    }//GEN-LAST:event_carbonaraActionPerformed

    private void macActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_macActionPerformed
        setLabel(150);
        addToCart("4", "Mac", 1, 150);
        loadPanel();
    }//GEN-LAST:event_macActionPerformed

    private void cookieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cookieActionPerformed
        setLabel(100);
        addToCart("5", "Cookie", 1, 100);
        loadPanel();
    }//GEN-LAST:event_cookieActionPerformed

    private void lasagnaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lasagnaActionPerformed
        setLabel(160);
        addToCart("6", "Lasagna", 1, 160);
        loadPanel();
    }//GEN-LAST:event_lasagnaActionPerformed

    private void malabonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_malabonActionPerformed
        setLabel(140);
        addToCart("8", "Malabon", 1, 140);
        loadPanel();
    }//GEN-LAST:event_malabonActionPerformed

    private void putoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_putoActionPerformed
        setLabel(110);
        addToCart("9", "Puto", 1, 110);
        loadPanel();
    }//GEN-LAST:event_putoActionPerformed

    private void putoFlanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_putoFlanActionPerformed
        setLabel(120);
        addToCart("7", "Puto Flan", 1, 120);
        loadPanel();
    }//GEN-LAST:event_putoFlanActionPerformed

    private void clearBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearBtnActionPerformed
        clearCart();
        mainPanel.removeAll(); 
        mainPanel.revalidate(); 
        mainPanel.repaint();  
        resetLabel();
    }//GEN-LAST:event_clearBtnActionPerformed

    private void cashPaidActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cashPaidActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cashPaidActionPerformed

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
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new POS().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bread;
    private javax.swing.JButton brownies;
    private javax.swing.JButton carbonara;
    private javax.swing.JLabel cashAmnt;
    private javax.swing.JTextField cashPaid;
    private DESIGN.items.MyButton clearBtn;
    private javax.swing.JButton cookie;
    private javax.swing.JLabel errorMsg;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JButton lasagna;
    private javax.swing.JButton mac;
    private javax.swing.JButton malabon;
    private DESIGN.items.MyButton myButton1;
    private javax.swing.JComboBox<String> payment;
    private javax.swing.JPanel printPanel;
    private javax.swing.JButton puto;
    private javax.swing.JButton putoFlan;
    private javax.swing.JScrollPane scrollPanel;
    private javax.swing.JLabel subtotallabel;
    // End of variables declaration//GEN-END:variables
}
