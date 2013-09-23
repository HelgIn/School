
package com.client;

import com.dto.SearchInfo;

import javax.swing.*;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class TicketFrame extends javax.swing.JFrame {


    public TicketFrame(Socket client) {
        this.client = client;
        initComponents();
    }

    public TicketFrame(ObjectOutputStream out) {
        this.out = out;
        initComponents();
    }



    javax.swing.JComboBox getJComboFrom() {
        return jComboFrom;
    }
    javax.swing.JComboBox getJComboTo() {
        return jComboTo;
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        SearchButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jComboFrom = new javax.swing.JComboBox();
        jComboTo = new javax.swing.JComboBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        jSearchTable = new javax.swing.JTable();



        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        SearchButton.setText("Search");
        SearchButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SearchButtonMouseClicked(evt);
            }
        });



        jLabel1.setText("From:");

        jLabel2.setText("To:");

        jComboFrom.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));
        jComboFrom.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ComboFromActionPerformed(evt);
            }
        });

        jComboTo.setModel(new javax.swing.DefaultComboBoxModel(new String[]{"Item 1", "Item 2", "Item 3", "Item 4"}));

        jSearchTable.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null, null, null},

                },
                new String[]{
                        "id", "From", "To", "Arrival", "Seats", "Time"
                }
        ));
        jScrollPane1.setViewportView(jSearchTable);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, 0)
                        .addComponent(SearchButton)
                        .addGap(0, 0, 0))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addGap(18, 18, 18)
                                .addComponent(jComboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jComboTo, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap(39, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(jComboFrom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jComboTo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                .addComponent(SearchButton)
                .addContainerGap())
        );

        pack();
    }

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
            JOptionPane.showMessageDialog(new JFrame(), message, "Ошибка",
                    JOptionPane.ERROR_MESSAGE);
        } else {
            SearchInfo searchInfo = new SearchInfo(from, to);
            // заполним таблицу

            jSearchTable.setModel(new javax.swing.table.DefaultTableModel(
                    new Object[][]{
                            {1, from, to, "10:00", "500", "310"},

                    },
                    new String[]{
                            "id", "From", "To", "Arrival", "Seats", "Time"
                    }
            ));

            try {
//                DataOutputStream dataOut = new DataOutputStream(client.getOutputStream());
//                ObjectOutputStream out = new ObjectOutputStream(dataOut);

                out.writeObject(searchInfo);
                out.flush();
            } catch (IOException e) {
//                message = "Ошибка отправки данных";
//                JOptionPane.showMessageDialog(new JFrame(), message, "Ошибка",
//                        JOptionPane.ERROR_MESSAGE);
                e.printStackTrace();
            }

        }
    }

    private void ComboFromActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ComboFromActionPerformed
    }


    private javax.swing.JComboBox jComboFrom;
    private javax.swing.JComboBox jComboTo;

    private javax.swing.JButton SearchButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jSearchTable;

    private Socket client;
    private ObjectOutputStream out;
}
