package com.client;

import listeners.Search;

import javax.swing.*;
import java.io.ObjectOutputStream;

class Frame extends JFrame {

    private JLabel jLabel;
    private JLabel jLabelDist;

    private JTextField jTextField;
    private JButton jButton;
    private JComboBox jArrival;
    private JComboBox jDestination;

    public Frame(ObjectOutputStream obj)
    {
        super();
        this.setSize(600, 400);
        this.getContentPane().setLayout(null);
        this.add(getJLabelDist(), null);
        this.add(getJLabel(), null);


       // this.add(getJTextField(), null);
       this.add(getJButton(), null);

        this.add(getArrivalJComboBox(), null);
        this.add(getDestinationJComboBox(), null);

        this.setTitle("Tickets");

        getJButton().addActionListener(new Search(this));

    }

    private javax.swing.JLabel getJLabel() {

        if(jLabel == null) {
            jLabel = new javax.swing.JLabel();
            jLabel.setBounds(10, 15, 53, 18);
            jLabel.setText("From:");
        }
        return jLabel;
    }


    private javax.swing.JLabel getJLabelDist() {

        if(jLabelDist == null) {
            jLabelDist = new javax.swing.JLabel();
            jLabelDist.setBounds(240, 15, 53, 18);
            jLabelDist.setText("To:");
        }
        return jLabelDist;
    }


    private javax.swing.JTextField getJTextField() {
        if(jTextField == null) {
            jTextField = new javax.swing.JTextField();
            jTextField.setBounds(10, 10, 160, 20);
        }
        return jTextField;
    }



    private javax.swing.JButton getJButton() {
        if(jButton == null) {
            jButton = new javax.swing.JButton();
            jButton.setBounds(10, 300, 100, 30);
            jButton.setText("Search");
        }
        return jButton;
    }


    private javax.swing.JComboBox getArrivalJComboBox() {
        if(jArrival == null) {
            jArrival = new javax.swing.JComboBox();
            jArrival.setBounds(100, 10, 120, 30);
        }
        return jArrival;
    }
    private javax.swing.JComboBox getDestinationJComboBox() {
        if(jDestination == null) {
            jDestination = new javax.swing.JComboBox();
            jDestination.setBounds(300, 10, 120, 30);
        }
        return jDestination;
    }
}