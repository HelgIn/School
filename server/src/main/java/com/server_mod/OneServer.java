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
import java.util.InputMismatchException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


public class OneServer {
    private static Logger log = Logger.getLogger(OneServer.class.getName());
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
                        log.log(Level.INFO, e.getMessage());
                        ExceptionInfo exceptionInfo = new ExceptionInfo(e, "Ничего не найдено. Повторите поиск.");
                        outObj.writeObject(exceptionInfo);
                    }
                }
                /*
                 * пришёл запрос на билет
                 */
                if (incomingObject instanceof TicketInfo){
                    TicketService ticketService = new TicketService((TicketInfo) incomingObject, em);
                    outObj.writeObject(ticketService.addTicket());
                }

                /*
                 * пришёл запрос поиск по станции
                 */
                if (incomingObject instanceof StationInfo){
                    StationService stationService = new StationService((StationInfo) incomingObject, em);
                    try {
                        outObj.writeObject(stationService.search());
                    } catch (NoResultException e) {
                        log.log(Level.INFO, e.getMessage());
                        ExceptionInfo exceptionInfo = new ExceptionInfo(e, "Ничего не найдено. Повторите поиск.");
                        outObj.writeObject(exceptionInfo);
                    }

                }

                /*
                 * пришёл запрос добавление станции
                 */
                if (incomingObject instanceof AddStationInfo){
                    AddStationService addStationService = new AddStationService((AddStationInfo) incomingObject, em);
                    outObj.writeObject(addStationService.add());
                }

                /*
                 * пришёл запрос добавление маршрута
                 */
                if (incomingObject instanceof AddRouteInfo){
                    AddRouteService addRouteService = new AddRouteService((AddRouteInfo) incomingObject, em);
                    outObj.writeObject(addRouteService.add());
                }


                 /*
                 * пришёл запрос списка пассажиров
                 */
                if (incomingObject instanceof JourneyInfo){
                    JourneyService journeyService = new JourneyService((JourneyInfo) incomingObject, em);
                    try {
                        journeyService.getJourneyPassengers();
                        outObj.writeObject(incomingObject);
                    } catch (NoResultException e) {
                        log.log(Level.INFO, e.getMessage());
                        ExceptionInfo exceptionInfo = new ExceptionInfo(e, "Ничего не найдено. Повторите поиск.");
                        outObj.writeObject(exceptionInfo);
                    }
                }

                 /*
                 * пришёл запрос авторизации
                 */
                if (incomingObject instanceof AuthInfo){
                    try {
                        AuthService authService = new AuthService((AuthInfo) incomingObject, em);
                        outObj.writeObject(authService.authorize());
                    } catch (NoResultException e) {
                        log.log(Level.INFO, e.getMessage());
                        ExceptionInfo exceptionInfo = new ExceptionInfo(e, "Неверный логин или пароль");
                        outObj.writeObject(exceptionInfo);

                    }
                }


                 /*
                 * пришёл запрос добавления поезда
                 */
                if (incomingObject instanceof AddJourneyInfo){
                    try {
                        AddJourneyService addJourneyService = new AddJourneyService((AddJourneyInfo) incomingObject, em);
                        outObj.writeObject(addJourneyService.addJourney());
                    } catch (NoResultException e) {
                        log.log(Level.INFO, e.getMessage());
                        ExceptionInfo exceptionInfo = new ExceptionInfo(e, "Не удалось добавить поезд. Проверьте правильность данных.");
                        outObj.writeObject(exceptionInfo);

                    } catch (InputMismatchException e) {
                        log.log(Level.INFO, e.getMessage());
                        ExceptionInfo exceptionInfo = new ExceptionInfo(e, "Введены некорректные данные.");
                        outObj.writeObject(exceptionInfo);
                    }
                }


                /*
                 * пришёл запрос всех поездов
                 */
                if (incomingObject instanceof GetJourneysInfo){

                    GetJourneysService getJourneysService = new GetJourneysService((GetJourneysInfo) incomingObject, em);
                    outObj.writeObject(getJourneysService.getJourneys());

                }

            }


        } catch (IOException e) {
            System.err.println("Could not listen on port: 8080");
            log.log(Level.OFF, e.getMessage());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.log(Level.OFF, e.getMessage());
        }

    }
}