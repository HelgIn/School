package com.server;

import com.entity.Train;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client {
    Socket client;
    Client() {
        try {
            client = new Socket("localhost", 8080);
            DataInputStream in = new DataInputStream(client.getInputStream());
            DataOutputStream out = new DataOutputStream(client.getOutputStream());

            ObjectOutputStream outObj = new ObjectOutputStream(out);
            Train tr  = new Train(44, 55);
            outObj.writeObject(tr);

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

