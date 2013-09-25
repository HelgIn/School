package com.client;

import com.dto.SearchInfoAnswer;
import com.dto.StartInfo;

import javax.swing.*;
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

            JFrame wnd = new JFrame(outObj);

            try {
                StartInfo si  = (StartInfo) inObj.readObject();
                wnd.getJComboFrom().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
                wnd.getJComboTo().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
                wnd.setVisible(true);

                while (true) {
                    Object incomingObject = inObj.readObject();
                    /*
                     * пришёл ответ поиска
                        */
                    if (incomingObject instanceof SearchInfoAnswer){
                        if(((SearchInfoAnswer) incomingObject).getSearchObj().size() > 0) {
                            wnd.printSearchResults((SearchInfoAnswer) incomingObject);
                        }
                        else {
                            JFrame.showError("Поезда не найдены");
                        }
                    }
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Stations loading error");
            }

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

