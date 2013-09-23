package com.server_mod;

import com.dto.SearchInfo;
import com.dto.StartInfo;
import com.dto.TicketInfo;
import com.entity.Station;
import com.services.SearchService;
import com.services.StartService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import java.io.*;
import java.net.Socket;
import java.util.List;

public class ServerThread extends Thread {

    private Socket socket = null;

    public ServerThread(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        EntityManager em = Persistence.createEntityManagerFactory("SCHOOL").createEntityManager();
        StartService ss = new StartService(em);
        StartInfo si = new StartInfo();
        si.setStations(ss.getStations());


        try {
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            ObjectInputStream inObj = new ObjectInputStream(in);
            ObjectOutputStream outObj = new ObjectOutputStream(out);

            outObj.writeObject(si);
            outObj.flush();

            try {
                SearchInfo incomingObject = (SearchInfo) inObj.readObject();


                /*
                 * пришёл запрос поиска
                 */
                if (incomingObject instanceof SearchInfo){
                    SearchService searchService = new SearchService(incomingObject, em);
                    searchService.search();
                }

            } catch (ClassNotFoundException e) {
                System.out.println("Ошибка передачи данных");
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