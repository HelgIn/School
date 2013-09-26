package com.client;


import com.dto.*;
import entity.Route;
import entity.Ticket;

import javax.persistence.NoResultException;
import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Scanner;

public class ConsoleClient {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    Scanner scanner;

    private void start() {
        System.out.println("Выберете действие");
        System.out.println("1. Найти поезд");
        System.out.println("2. Посмотреть расписание станции");
        System.out.println("3. Добавить станцию");
        System.out.println("4. Добавить маршрут");
        int choose = scanner.nextInt();

        switch (choose) {
            case 1:
                findTrain(outObj);
                break;
            case 2:
                stationSchedule();
                break;
            case 3:
                addStation();
                break;
            case 4:
                addRoute();
                break;
            default:
                System.out.println("Ошибка выбора действия. Попробуйте еще раз.");
                start();
                break;
        }
    }

    private void findTrain(ObjectOutputStream outObj) {
        scanner.nextLine();
        System.out.println("Станция отправления:");
        String from = scanner.nextLine();
        System.out.println("Станция назначения:");
        String to = scanner.nextLine();
        System.out.println(from + to);
        if(!from.equals("") && !to.equals("")) {

            SearchInfo searchInfo = new SearchInfo(from, to);
            try {
                outObj.writeObject(searchInfo);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void exit() {
        start();
    }

    // покупка билета
    private void chooseRouteId(List<Long> ids) {
        System.out.println("Купить билет?");
        String answer = scanner.next();
        if(!answer.equals("y")) {
            exit();
        } else {
            System.out.println("Выберете номер поезда (-1 - выход)");
            long choose = scanner.nextLong();
            if(ids.contains(choose)) {
                System.out.println("Кол-во билетов:");
                int count = scanner.nextInt();

                TicketInfo ticketInfo = new TicketInfo();

                for(int i = 1; i <= count; i++) {
                    System.out.println("Имя " + i + "-го пассажира");
                    String name = scanner.next();
                    System.out.println("Фамилия " + i + "-го пассажира");
                    String secondName = scanner.next();
                    System.out.println("Дата рождения " + i + "-го пассажира");
                    String date = scanner.next();

                    if(name.equals("") || secondName.equals("") || date.equals("")) {
                        System.out.println("Ошибка");
                        chooseRouteId(ids);
                    } else {

                        ticketInfo.getTicketList().add(new BuyTicketObject(name, secondName, date, choose));
                        try {
                            outObj.writeObject(ticketInfo);
                        } catch (IOException e) {
                            System.out.println("Ошибка отправки данных");
                        }

                    }
                }
            } else if(choose == -1) {
                exit();
            } else {
                System.out.println("Ошибка выбора поезда");
                chooseRouteId(ids);
            }
        }
    }


    // расписание по станции
    private void stationSchedule() {
        System.out.println("Станция");
        String station = scanner.nextLine();
        StationInfo si = new StationInfo(station);
        try {
            outObj.writeObject(si);
        } catch (IOException e) {
            System.out.println("Ошибка отправки данных");
        }
    }

    // добавление станции
    private void addStation() {
        if(auth()) {
            System.out.println("Название станции:");
            String station = scanner.nextLine();
            AddStationInfo addStationInfo = new AddStationInfo(station);
            try {
                outObj.writeObject(addStationInfo);
                System.out.println("Станция '" + station + "' добавлена");
                start();
            } catch (IOException e) {
                System.out.println("Ошибка отправки данных");
            }
        } else {
            System.out.println("неверный пароль (0 - еще раз 1 - в главное меню)");
            int choose = scanner.nextInt();
            if(choose == 0) {
                addStation();
            } else {
                start();
            }
        }

    }

    private void addRoute() {
        if(auth()) {
            System.out.println("Название маршрута:");
            String route = scanner.nextLine();
            AddRouteInfo addRouteInfo = new AddRouteInfo(route);
            try {
                outObj.writeObject(addRouteInfo);
                System.out.println("Маршрут '" + route + "' добавлен");
                start();
            } catch (IOException e) {
                System.out.println("Ошибка отправки данных");
            }
        } else {
            System.out.println("неверный пароль (0 - еще раз 1 - в главное меню)");
            int choose = scanner.nextInt();
            if(choose == 0) {
                addStation();
            } else {
                start();
            }
        }
    }

    private boolean auth() {
        System.out.println("Введите пароль администратора");
        String password = scanner.next();
        if(password.equals("test")) {
            return true;
        }  else {
            return false;
        }
    }

    public ConsoleClient() throws IOException {

        client = new Socket("localhost", 8080);

        scanner = new Scanner(System.in);
        in = new DataInputStream(client.getInputStream());
        out = new DataOutputStream(client.getOutputStream());


        outObj = new ObjectOutputStream(out);
        inObj = new ObjectInputStream(in);
    }

    public void startClient() throws IOException, ClassNotFoundException {

        start();

        while (true) {
            Object incomingObject = inObj.readObject();
            /*
             * пришёл ответ поиска
             */
            if (incomingObject instanceof SearchInfoAnswer){
                if(((SearchInfoAnswer) incomingObject).getSearchObj().size() > 0) {
                    // отобразим результаты поиска
                    System.out.println(incomingObject);
                    // предложим выбрать поезд и купить билет
                    chooseRouteId(((SearchInfoAnswer) incomingObject).getIDs());
                }
                else {
                    System.out.println("Не найдено, повторите поиск");
                    start();
                }
            }
             /*
              * пришёл ответ покупки
              */
            if (incomingObject instanceof Boolean){
                if(incomingObject.equals(true)) {
                    System.out.println("Покупка завершена");
                }  else {
                    System.out.println("Ошибка покупки билета. Попробуйте еще раз.");
                }
                start();
            }
            /*
             * пришёл ответ станции
             */
            if (incomingObject instanceof StationAnswer){
                if(((StationAnswer) incomingObject).getRoutes().size() > 0) {

                    for(Route r : ((StationAnswer) incomingObject).getRoutes()) {
                        System.out.println(r);
                    }
                }
                else {
                    System.out.println("Не найдено, повторите поиск");
                }
                start();
            }
            /*
             * пришёл NoResultException
             */
            if (incomingObject instanceof NoResultException){
                System.out.println("Не найдено, повторите поиск");
                start();
            }
        }

    }
}
