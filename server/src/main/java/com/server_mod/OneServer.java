package com.server_mod;

import com.dto.SearchInfo;
import com.dto.StartInfo;
import com.dto.TicketInfo;
import com.services.SearchService;
import com.services.StartService;
import com.services.TicketService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;


public class OneServer {
    public static void main(String[] args)  {
        ServerSocket serverSocket = null;
        boolean listening = true;
        EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        StartService ss = new StartService(em);
        StartInfo si = new StartInfo();
        si.setStations(ss.getStations());



        try {
            serverSocket = new ServerSocket(8080);
            Socket socket = serverSocket.accept();

            DataInputStream sin = new DataInputStream (socket.getInputStream());
            DataOutputStream sout = new DataOutputStream(socket.getOutputStream());

            ObjectInputStream inObj = new ObjectInputStream(sin);
            ObjectOutputStream outObj = new ObjectOutputStream(sout);

            outObj.writeObject(si);
            outObj.flush();



            while(true) {
                Object incomingObject = inObj.readObject();

                /*
                 * пришёл запрос поиска
                 */
                if (incomingObject instanceof SearchInfo){
                    SearchService searchService = new SearchService((SearchInfo) incomingObject, em);
                    outObj.writeObject(searchService.search());
                }
                /*
                 * пришёл запрос на билет
                 */
                else if (incomingObject instanceof TicketInfo){
                    TicketService ticketService = new TicketService((TicketInfo) incomingObject, em);
                    ticketService.addTicket();
                    //outObj.writeObject(ticketService.addTicket());
                }

            }


        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}