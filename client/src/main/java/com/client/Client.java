package com.client;

import com.entity.Train;

import javax.swing.*;
import java.awt.*;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream outObj;
    Client() {
        try {
            client = new Socket("localhost", 8080);

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());

            outObj = new ObjectOutputStream(out);

            Frame wnd = new Frame(outObj);
            wnd.setVisible(true);

            while(true) {

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

