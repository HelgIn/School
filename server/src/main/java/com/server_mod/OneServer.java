package com.server_mod;

import com.dto.*;
import com.services.*;
import entity.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;


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
                    try {
                        outObj.writeObject(searchService.search());
                    } catch (NoResultException e) {
                        outObj.writeObject(e);
                    }
                }
                /*
                 * пришёл запрос на билет
                 */
                else if (incomingObject instanceof TicketInfo){
                    TicketService ticketService = new TicketService((TicketInfo) incomingObject, em);
                    outObj.writeObject(ticketService.addTicket());
                }

                /*
                 * пришёл запрос поиск по станции
                 */
                else if (incomingObject instanceof StationInfo){
                    StationService stationService = new StationService((StationInfo) incomingObject, em);
                    try {
                        outObj.writeObject(stationService.search());
                    } catch (NoResultException e) {
                        outObj.writeObject(e);
                    }

                }

                /*
                 * пришёл запрос добавление станции
                 */
                else if (incomingObject instanceof AddStationInfo){
                    AddStationService addStationService = new AddStationService((AddStationInfo) incomingObject, em);
                    addStationService.add();

                }

                /*
                 * пришёл запрос добавление маршрута
                 */
                else if (incomingObject instanceof AddRouteInfo){
                    AddRouteService addRouteService = new AddRouteService((AddRouteInfo) incomingObject, em);
                    addRouteService.add();
                }

            }


        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }
}