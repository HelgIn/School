package com.client;

import com.dto.StartInfo;

import java.io.*;
import java.net.Socket;

public class Client {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    Client() {


        try {

            client = new Socket("localhost", 8080);

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());


            outObj = new ObjectOutputStream(out);
            inObj = new ObjectInputStream(in);

            TicketFrame wnd = new TicketFrame(outObj);

            try {
                StartInfo si  = (StartInfo) inObj.readObject();
                wnd.getJComboFrom().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
                wnd.getJComboTo().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
                wnd.setVisible(true);

            } catch (ClassNotFoundException e) {
                System.out.println("Stations loading error");
            }

            wnd.setVisible(true);

            while (true) {

            }



//            client = new Socket("localhost", 8080);
//
//
//            dataIn =  new DataInputStream(client.getInputStream());
//            inObj = new ObjectInputStream(dataIn);
//
//            wnd = new TicketFrame(client);
//            try {
//                StartInfo si  = (StartInfo) inObj.readObject();
//                wnd.getJComboFrom().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
//                wnd.getJComboTo().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
//                wnd.setVisible(true);
//
//            } catch (ClassNotFoundException e) {
//                System.out.println("Stations loading error");
//            }
//
//
//            while (true) {
//
//            }

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



    public static void main(String [] args) {
        Client cl = new Client();
    }
}

