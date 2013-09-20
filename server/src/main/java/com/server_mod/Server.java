package com.server_mod;

import java.io.IOException;
import java.net.ServerSocket;

public class Server {

    public static void main(String[] args)  {
        ServerSocket serverSocket = null;
        boolean listening = true;
        try {
            serverSocket = new ServerSocket(8080);
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080");
        }
        while (listening) {
            try {
                new ServerThread(serverSocket.accept()).start();
            } catch (IOException e) {
                System.err.println("Could not listen on port: 8080");
            }
        }
        try {
            serverSocket.close();
        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080");
        }
    }
}