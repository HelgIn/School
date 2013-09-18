package com.test;

import com.entity.*;
import com.services.TrainService;


public class ServerTest {
    public static void main(String[] args) {
        TrainService ts = new TrainService();
        ts.addTrain(new Train(3, 5));
    }
}
