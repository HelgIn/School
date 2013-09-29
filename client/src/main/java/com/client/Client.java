package com.client;

import com.dto.SearchAnswerInfo;
import com.dto.SearchInfo;
import objects.StationAnswerObject;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    Scanner scanner;


    public void start() {
        System.out.println("Client start");
        System.out.println("Выберете действие");
        System.out.println("1. Найти ппоезд");
        System.out.println("2. Посмотреть расписание станции");
        System.out.println("3. Добавить станцию");
    }

    public void findTrain(ObjectOutputStream outObj) {
        System.out.println("Станция отправления:");
        String from = scanner.nextLine();
        System.out.println("Станция назначения:");
        String to = scanner.nextLine();

        System.out.println(from);
        System.out.println(to);

        if(!from.equals("") && !to.equals("")) {

            SearchInfo searchInfo = new SearchInfo(from, to);
            try {
                outObj.writeObject(searchInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public Client() {

        scanner = new Scanner(System.in);

        try {

            client = new Socket("localhost", 8080);

            in = new DataInputStream(client.getInputStream());
            out = new DataOutputStream(client.getOutputStream());


            outObj = new ObjectOutputStream(out);
            inObj = new ObjectInputStream(in);


//            }

//            switch (choose) {
//                case 1:
//                    System.out.println("Выбрано " + choose);
//
//                    System.out.println("Станция отправления:");
//                    String from = scanner.nextLine();
//                    System.out.println("Станция назначения:");
//                    String to = scanner.nextLine();
//
//                    System.out.println(from);
//                    System.out.println(to);
//
//                    if(!from.equals("") && !to.equals("")) {
//
//                        SearchInfo searchInfo = new SearchInfo(from, to);
//                        try {
//                            outObj.writeObject(searchInfo);
//                        } catch (IOException e) {
//                            e.printStackTrace();
//                        }
//                    }
//
////                    findTrain(outObj);
//                    break;
//
//                case 2:  System.out.println("Выбрано " + choose); break;
//                case 3:  System.out.println("Выбрано " + choose); break;
//                default: start(); break;
//            }




            //JFrame wnd = new JFrame(outObj);

//            try {
//                StartInfo si  = (StartInfo) inObj.readObject();
//                wnd.getJComboFrom().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
//                wnd.getJComboTo().setModel(new javax.swing.DefaultComboBoxModel(si.getStations().toArray()));
//                wnd.setVisible(true);
//
//                while (true) {
//                    Object incomingObject = inObj.readObject();
//                    /*
//                     * пришёл ответ поиска
//                        */
//                    if (incomingObject instanceof SearchAnswerInfo){
//                        if(((SearchAnswerInfo) incomingObject).getSearchObj().size() > 0) {
//                            wnd.printSearchResults((SearchAnswerInfo) incomingObject);
//                        }
//                        else {
//                            JFrame.showError("Поезда не найдены");
//                        }
//                    }
//                }
//
//            } catch (ClassNotFoundException e) {
//                System.out.println("Stations loading error");
//            }

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


    public void startClient() throws IOException, ClassNotFoundException {
        System.out.println("Client start");
        System.out.println("Выберете действие");
        System.out.println("1. Найти ппоезд");
        System.out.println("2. Посмотреть расписание станции");
        System.out.println("3. Добавить станцию");

        int choose = scanner.nextInt();


        System.out.println("Станция отправления:");
        String from = scanner.nextLine();
        System.out.println("Станция назначения:");
        String to = scanner.nextLine();

        System.out.println(from);
        System.out.println(to);

        if(!from.equals("") && !to.equals("")) {

            SearchInfo searchInfo = new SearchInfo(from, to);
            try {
                outObj.writeObject(searchInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        while (true) {
            Object incomingObject = inObj.readObject();

            System.out.println(incomingObject);
            /*
             * пришёл ответ станции
             */
            if (incomingObject instanceof StationAnswerObject){
                System.out.println("here");
                if(((StationAnswerObject) incomingObject).getRoutes().size() > 0) {
                    System.out.println(incomingObject);
                }
                else {
                    System.out.println("Не найдено, повторите поиск");
                    start();
                }
            }

            /*
             * пришёл ответ поиска
             */
            if (incomingObject instanceof SearchAnswerInfo){
                if(((SearchAnswerInfo) incomingObject).getSearchObj().size() > 0) {
                    System.out.println(incomingObject);
                }
                else {
                    System.out.println("Не найдено, повторите поиск");
                    start();
                }
            }

        }

    }


    public static void main(String [] args) throws IOException, ClassNotFoundException {

        ConsoleClient cc = new ConsoleClient();
        cc.startClient();

    }
}

