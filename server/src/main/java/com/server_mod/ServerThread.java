package com.server_mod;

import com.dto.SearchInfo;
import com.dto.TicketInfo;

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
                SearchInfo si = (SearchInfo) inObj.readObject();
                System.out.println(si.getFrom());
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            while (true) {

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