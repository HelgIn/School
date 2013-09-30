package com.client;


import com.dto.*;
import entity.Route;
import objects.BuyTicketObject;
import com.dto.SearchAnswerInfo;
import objects.StationAnswerObject;

import javax.persistence.NoResultException;
import java.io.*;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConsoleClient {
    private Socket client;
    private DataInputStream in;
    private DataOutputStream out;
    private ObjectOutputStream outObj;
    private ObjectInputStream inObj;

    private Scanner scanner;

    private static Logger log = Logger.getLogger(ConsoleClient.class.getName());

    private boolean authorize = true;

    public boolean isAuthorized() {
        return authorize;
    }

    public void setAuthorize(boolean authorize) {
        this.authorize = authorize;
    }

    private void start() {
        System.out.println("Выберете действие");
        System.out.println("1. Найти поезд");
        System.out.println("2. Посмотреть расписание станции");
        if(isAuthorized()) {
            System.out.println("3. Добавить станцию");
            System.out.println("4. Добавить маршрут");
            System.out.println("5. Добавить поезд");
            System.out.println("6. Посмотреть пассажиров поезда");
            System.out.println("7. Посмотреть поезда");
        } else {
            System.out.println("9. Авторизация");
        }

        int choose = 0;
        try {
            choose = scanner.nextInt();

        } catch (Exception e) {
            printMessage("Ошибка выбора действия. Попробуйте еще раз.");
            scanner.nextLine();
            start();
            return;
        }

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
            case 5:
                addJourney();
                break;
            case 6:
                showPassengers();
                break;
            case 7:
                showJourneys();
                break;
            case 9:
                auth();
                break;
            default:
                printMessage("Ошибка выбора действия. Попробуйте еще раз.");
                scanner.nextLine();
                start();
                break;
        }
    }

    // 7 - просмотр всех поездов
    private void showJourneys() {
        GetJourneysInfo getJourneysInfo = new GetJourneysInfo();
        try {
            outObj.writeObject(getJourneysInfo);
        } catch (IOException e) {
            printMessage("Ошибка отправки данных");
            start();
        }

    }
    // 5 - добавить поезд
    public void addJourney() {
        try {
            scanner.nextLine();
            System.out.println("Маршрут:");
            String route = scanner.nextLine();
            System.out.println("Номер поезда:");
            int number = scanner.nextInt();
            System.out.println("Кол-во мест:");
            int availableSeats = scanner.nextInt();
            scanner.nextLine();
            System.out.println("Время отправки (чч-мм):");
            String arrivalTime = scanner.nextLine();

            if(!timeCheck(arrivalTime)) {
                printMessage("Неверный формат времени");
                addJourney();
                return;
            }
            long arrivalTimeLong = Long.valueOf(arrivalTime.replace(":", "") + "00");
            //log.info(String.valueOf(arrivalTimeLong));
            AddJourneyInfo addJourneyInfo = new AddJourneyInfo(route, number, availableSeats, String.valueOf(arrivalTimeLong));
            try {
                outObj.writeObject(addJourneyInfo);
            } catch (IOException e) {
                System.out.println("Ошибка. Не удалось отправить данные на сервер.");
            }
        } catch (InputMismatchException e) {
            printMessage("Введены некорректные данные.");
            addJourney();
        }

    }


    // 6 - посмотреть пассажиров
    public void showPassengers() {
        scanner.nextLine();
        System.out.println("Номер поезда:");
        int number = 0;
        try {
            number = scanner.nextInt();
        } catch (InputMismatchException e) {
            printMessage("Ошибка ввода данных");
            showPassengers();
            return;
        }
        JourneyInfo journeyInfo = new JourneyInfo(number);
        try {
            outObj.writeObject(journeyInfo);
        } catch (IOException e) {
            scanner.nextLine();
            printMessage("Ошибка отправки данных");
            start();
        }
    }

    private void findTrain(ObjectOutputStream outObj) {
        scanner.nextLine();
        System.out.println("Станция отправления:");
        String from = scanner.nextLine();
        System.out.println("Станция назначения:");
        String to = scanner.nextLine();

        System.out.println("От (чч:мм)");
        String fromTime = scanner.nextLine();
        System.out.println("До (чч:мм)");
        String toTime = scanner.nextLine();

        if(!from.equals("") && !to.equals("") && !fromTime.equals("") && !toTime.equals("")) {

            if(timeCheck(fromTime) && timeCheck(toTime)) {

                long forTimeLong = Long.valueOf(fromTime.replace(":", "")+"00");
                long toTimeLong = Long.valueOf(toTime.replace(":", "")+"00");



                SearchInfo searchInfo = new SearchInfo(from, to);
                searchInfo.setTimes(forTimeLong, toTimeLong);
                try {
                    outObj.writeObject(searchInfo);
                } catch (IOException e) {
                    printMessage("Ошибка отправки данных");
                    start();
                    return;
                }
            } else {
                printMessage("Неверный формат времени");
                start();
                return;
            }
        } else {
            printMessage("Введены некорректные данные");
            start();
        }
    }



    private void printMessage(String message) {
        System.out.println("*************************");
        System.out.println(message);
        System.out.println("*************************");
        scanner.nextLine();
    }

    private void question(List<Long> ids) {
        System.out.println("Купить билет?");
        String answer = scanner.next();
        if(!answer.equals("да")) {
            start();
            return;
        } else {
            System.out.println("Выберете номер поезда (-1 - выход)");
            long choose = -1;
            try {
                choose = scanner.nextLong();
            } catch (InputMismatchException e) {
                printMessage("Ошибка ввода данных (введите число)");
                question(ids);
                return;
            }
            if(ids.contains(choose)) {
                chooseRouteId(ids, choose);
                return;
            } else if(choose == -1) {
                start();
                return;
            } else {
                printMessage("Ошибка выбора поезда");
                question(ids);
            }
        }

    }

    // покупка билета
    private void chooseRouteId(List<Long> ids, long choose) {

        System.out.println("Кол-во билетов:");
        int count = 0;
        try {
            count = scanner.nextInt();
        } catch (InputMismatchException e) {
            scanner.nextLine();
            printMessage("Неверный формат данных. Повторите ввод.");
            chooseRouteId(ids, choose);
            return;
        }

        if(count == 0) {
            start();
        } else {
            TicketInfo ticketInfo = new TicketInfo();

            ticketInfo.setJourneyId(choose);
            // формируем список пассажиров

            for(int i = 1; i <= count; i++) {
                System.out.println("Имя " + i + "-го пассажира");
                String name = scanner.next();
                System.out.println("Фамилия " + i + "-го пассажира");
                String secondName = scanner.next();
                System.out.println("Дата рождения " + i + "-го пассажира (дд-мм-гггг)");
                String dateStr = scanner.next();


                if(name.equals("") || secondName.equals("") || dateStr.equals("")) {
                    System.out.println("Ошибка ввода данных.");
                    chooseRouteId(ids, choose);
                    return;
                } else {

                    if(checkDate(dateStr)) {
                        Calendar date = Calendar.getInstance();
                        try {
                            date.setTime(new SimpleDateFormat("dd-MM-yyyy").parse(dateStr));
                        } catch (ParseException e) {
                            scanner.nextLine();
                            System.out.println("Ошибка. Неверный формат даты.");
                            scanner.nextLine();
                            chooseRouteId(ids, choose);
                            return;
                        }
                        // добавим пассажира в лист
                        ticketInfo.addObject(new BuyTicketObject(name, secondName, date, choose));

                    } else {
                        System.out.println("Ошибка. Неверный формат даты.");
                        scanner.nextLine();
                        chooseRouteId(ids, choose);
                        return;
                    }
                }
            }
            try {
                outObj.writeObject(ticketInfo);
            } catch (IOException e) {
                printMessage("Ошибка отправки данных");
                start();
            }
        }
    }

    // проверка даты
    private boolean checkDate(String date) {
        Pattern p = Pattern.compile("(0[1-9]|[12][0-9]|3[01])[- /.](0[1-9]|1[012])[- /.](19|20)\\d\\d");
        Matcher m = p.matcher(date);
        return  m.matches();
    }

    // проверка времени
    private boolean timeCheck(String time) {
        Pattern p = Pattern.compile("[0-2][0-9]:[0-5][0-9]");
        Matcher m = p.matcher(time);
        return m.matches();
    }

    // расписание по станции
    private void stationSchedule() {
        scanner.nextLine();
        System.out.println("Станция");
        String station = scanner.nextLine();
        StationInfo si = new StationInfo(station);
        try {
            outObj.writeObject(si);
        } catch (IOException e) {
            printMessage("Ошибка отправки данных");
            start();
        }
    }

    // добавление станции
    private void addStation() {
        if(isAuthorized()) {
            scanner.nextLine();
            System.out.println("Название станции:");
            String station = scanner.nextLine();
            AddStationInfo addStationInfo = new AddStationInfo(station);
            try {
                outObj.writeObject(addStationInfo);
            } catch (IOException e) {
                printMessage("Ошибка отправки данных");
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
        if(isAuthorized()) {
            scanner.nextLine();
            System.out.println("Название маршрута:");
            String route = scanner.nextLine();
            AddRouteInfo addRouteInfo = new AddRouteInfo(route);
            try {
                outObj.writeObject(addRouteInfo);
            } catch (IOException e) {
                printMessage("Ошибка отправки данных");
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

    private void auth() {
        scanner.nextLine();
        System.out.println("Введите логин администратора");
        String login = scanner.nextLine();
        System.out.println("Введите пароль администратора");
        String password = scanner.nextLine();

        AuthInfo authInfo = new AuthInfo(login, password);
        try {
            outObj.writeObject(authInfo);
        } catch (IOException e) {
            System.out.println("Ошибка отправки данных");
        }
    }

    public ConsoleClient() throws IOException {

        client = new Socket("localhost", 9090);

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
            if (incomingObject instanceof SearchAnswerInfo){
                    // отобразим результаты поиска
                    System.out.println(incomingObject);
                    // предложим выбрать поезд и купить билет
                    question(((SearchAnswerInfo) incomingObject).getIDs());
            }
             /*
              * пришёл ответ покупки
              */
            if (incomingObject instanceof TicketInfo){

                scanner.nextLine();
                printMessage(((TicketInfo) incomingObject).getMessage());
                start();

            }

            /*
             * пришёл ответ станции
             */
            if (incomingObject instanceof StationInfo){

                ((StationInfo) incomingObject).print();
                scanner.nextLine();
                start();

            }

             /*
             * пришёл ответ авторизации
             */
            if (incomingObject instanceof AuthInfo){

                setAuthorize(((AuthInfo) incomingObject).getAuth());
                printMessage("Вы успешно авторизовались");
                start();

            }

            /*
             * пришёл ExceptionInfo
             */
            if (incomingObject instanceof ExceptionInfo){
                printMessage(((ExceptionInfo) incomingObject).getMessage());
                start();
            }


             /*
              * пришёл ответ списка пассажиров
              */
            if (incomingObject instanceof JourneyInfo){
                scanner.nextLine();
                ((JourneyInfo) incomingObject).print();
                scanner.nextLine();
                start();
            }


             /*
              * пришёл ответ добавления поезда
              */
            if (incomingObject instanceof AddJourneyInfo){
                printMessage(((AddJourneyInfo) incomingObject).getMessage());
                start();
            }


            /*
              * пришёл ответ добавления маршрута
              */
            if (incomingObject instanceof AddRouteInfo){
                printMessage(((AddRouteInfo) incomingObject).getMessage());
                start();
            }

             /*
                 * пришёл запрос добавление станции
                 */
            if (incomingObject instanceof AddStationInfo){
                printMessage(((AddStationInfo) incomingObject).getMessage());
                start();

            }

            /*
             * пришёл ответ всех поездов
             */
            if (incomingObject instanceof GetJourneysInfo){

                scanner.nextLine();

                ((GetJourneysInfo) incomingObject).print();
                scanner.nextLine();
                start();
            }
        }

    }



    public static void main(String[] args) {
        try {
            ConsoleClient cc = new ConsoleClient();
            cc.startClient();
        } catch (IOException e) {
            System.out.println("Не удалось подключиться к серверу");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}
