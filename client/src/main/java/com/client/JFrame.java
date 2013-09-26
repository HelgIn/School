/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.client;

import com.dto.SearchInfo;
import com.dto.SearchInfoAnswer;
import com.dto.SearchInfoObject;
import com.dto.TicketInfo;
import entity.Route;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author Oleg
 */
public class JFrame extends javax.swing.JFrame {



    /**
     * Creates new form JFrame
     */
    public JFrame() {
        initComponents();
    }

    public JFrame(ObjectOutputStream out) {
        this.out = out;
        initComponents();
        this.setTitle("Tickets");
    }

    javax.swing.JComboBox getJComboFrom() {
        return jComboFrom;
    }
    javax.swing.JComboBox getJComboTo() {
        return jComboTo;
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
        jLabel2 = new javax.swing.JLabel();
        jComboFrom = new javax.swing.JComboBox();
        jComboTo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jSearchButton = new javax.swing.JButton();
        jBuyButton = new javax.swing.JButton();
        jTextFieldName = new javax.swing.JTextField();
        jTextFieldSecond = new javax.swing.JTextField();
        jTextFieldDate = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();




        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("From:");

        jLabel2.setText("To:");

        jComboFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jComboTo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tm = new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null},

                },
                new String[]{
                        "Номер", "Поезд"
                }
        );


        jScrollPane1.setViewportView(jTable1);

        jSearchButton.setText("Search");

        jSearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchButtonMouseClicked(evt);
            }
        });

        jBuyButton.setText("Buy");

        jBuyButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                BuyButtonMouseClicked(evt);
            }
        });

        jLabel3.setText("Name:");

        jLabel4.setText("Second name:");

        jLabel5.setText("Date of birth:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jSearchButton)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboTo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldSecond)
                            .addComponent(jTextFieldName)
                            .addComponent(jTextFieldDate, javax.swing.GroupLayout.DEFAULT_SIZE, 189, Short.MAX_VALUE))
                        .addGap(104, 104, 104))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jBuyButton)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSearchButton)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldSecond, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextFieldDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addGap(18, 18, 18)
                .addComponent(jBuyButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(JFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new JFrame().setVisible(true);
            }
        });
    }

    /////////////////////
    /////////////////////

    /*
* LISTENERS
*/
    private void SearchButtonMouseClicked(java.awt.event.MouseEvent evt) {

        String message;
        String from = (String) jComboFrom.getSelectedItem();
        String to = (String) jComboTo.getSelectedItem();
        System.out.println(jComboFrom.getSelectedItem());
        System.out.println(jComboTo.getSelectedItem());
        if(from.equals(to)) {
            message = "Город отправления совпадает с городом назначения";
            JOptionPane.showMessageDialog(new javax.swing.JFrame(), message, "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            SearchInfo searchInfo = new SearchInfo(from, to);
            // заполним таблицу



            try {
                out.writeObject(searchInfo);
            } catch (IOException e) {
                message = "Ошибка отправки данных";
                JOptionPane.showMessageDialog(new javax.swing.JFrame(), message, "Ошибка",
                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        }
    }

    private void BuyButtonMouseClicked(java.awt.event.MouseEvent evt) {

        String name = jTextFieldName.getText();
        String secondName = jTextFieldSecond.getText();
        String date = jTextFieldDate.getText();

        System.out.println(jTable1.getValueAt(jTable1.getSelectedRow(), 0));

        if(name.equals("") || secondName.equals("") || date.equals("")) {
            JFrame.showError("Заполните данные пассажира");
        } else {

            TicketInfo ticket = new TicketInfo(name, secondName, date, (Long) jTable1.getValueAt(jTable1.getSelectedRow(), 0));
            try {
                out.writeObject(ticket);
            } catch (IOException e) {
                showError("Ошибка отправки данных");
                e.printStackTrace();
            }
            System.out.println(ticket);
        }

    }

    public static void showError(String message) {
        JOptionPane.showMessageDialog(new javax.swing.JFrame(), message, "Ошибка",
                JOptionPane.ERROR_MESSAGE);
    }



    public void printSearchResults(SearchInfoAnswer sa) {

        final int rows = sa.getSearchObj().size();

        tm = new javax.swing.table.DefaultTableModel(
                new Object[] {"Номер", "Поезд", "Время отправки", "Мест"}, rows
        );

       // tm.
        jTable1.setModel(tm);
        int index = 0;
        for(SearchInfoObject search : sa.getSearchObj()) {
            System.out.println(search.getName());
            jTable1.setValueAt(search.getId(), index, 0);
            jTable1.setValueAt(search.getName(), index, 1);
            jTable1.setValueAt(search.getArrivalTime(), index, 2);
            jTable1.setValueAt(search.getAvailableSeats(), index, 3);
            index++;

        }


    }

    //////////////////////
    //////////////////


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBuyButton;
    private javax.swing.JComboBox jComboFrom;
    private javax.swing.JComboBox jComboTo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton jSearchButton;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextFieldDate;
    private javax.swing.JTextField jTextFieldName;
    private javax.swing.JTextField jTextFieldSecond;

    private ObjectOutputStream out;
    private TableModel tm;
    // End of variables declaration//GEN-END:variables
}