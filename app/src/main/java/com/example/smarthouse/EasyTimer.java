package com.example.smarthouse;


public class EasyTimer {
    private double startTime;
    private boolean stop = false;

    //запуск таймера
    void startTimer() {
        stop = false;
        startTime = System.nanoTime() / 1000000000;
    }

    void stopTimer() {
        stop = true;
    } //отключить таймер

    double getPssedTime() { // получить время с момента старта
        double elapsedTime = System.nanoTime() / 1000000000 - startTime;
        if (!stop) return elapsedTime;
        return 0;
    }

    boolean isStop() {
        return stop;
    }
}
