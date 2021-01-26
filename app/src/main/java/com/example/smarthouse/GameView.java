package com.example.smarthouse;


public abstract class GameView {
    private MainRun mainRun; //главная активность
    private GamePaint gamePaint; // поддержка графики

    public GameView(MainRun mainRun) {
        this.mainRun = mainRun;
        gamePaint = mainRun.getGamePaint();
    }

    public abstract void run();
    public abstract void repaint();

    public MainRun getMainRun() {
        return mainRun;
    }

    public GamePaint getGamePaint() {
        return gamePaint;
    }
}
