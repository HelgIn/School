package com.server_mod;

public class Server {

    public static void main(String[] args)  {


        MultiThreadedServer server = new MultiThreadedServer(9090);
        new Thread(server).start();

//        try {
//            Thread.sleep(20 * 1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.out.println("Stopping Server");
//        server.stop();


//        ServerSocket serverSocket = null;
//        boolean listening = true;
//        try {
//            serverSocket = new ServerSocket(8080);
//        } catch (IOException e) {
//            System.err.println("Could not listen on port: 8080");
//        }
//        while (listening) {
//            try {
//                new ServerThread(serverSocket.accept()).start();
//            } catch (IOException e) {
//                System.err.println("Could not listen on port: 8080");
//            }
//        }
//        try {
//            serverSocket.close();
//        } catch (IOException e) {
//            System.err.println("Could not listen on port: 8080");
//        }
    }
}