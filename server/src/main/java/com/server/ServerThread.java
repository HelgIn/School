package com.server;

import java.io.*;
import java.net.Socket;

public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            ObjectInputStream inObj = new ObjectInputStream(in);
            try {
                System.out.println(inObj.readObject().toString());
            } catch (ClassNotFoundException e) {
                System.out.println("Невозможно прочитать данные");
            }

        } catch (IOException e) {
            System.out.println("Message error");
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                System.err.println("Could not listen on port: 8080");
            }
        }
    }
}