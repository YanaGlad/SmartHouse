package com.example.smarthouse;


import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

@SuppressLint("ViewConstructor")
public class GameLoop extends SurfaceView implements Runnable {
    private boolean running = false; // работа потока
    Thread gameThread = null; // игровой поток
    MainRun mainRun;
    Bitmap bmp; // Основной Bitmap, который будет привязан к canvas
    SurfaceHolder surfaceHolder; // Объект класса SurfaceHolder, который поможет получить canvas
    Canvas canvas; // "Холст", на котором будет отрисовываться игра
    Rect rect;

    public GameLoop(MainRun mainRun, Bitmap bmp) {
        super(mainRun);
        this.bmp = bmp;
        this.mainRun = mainRun;
        this.surfaceHolder = getHolder();
        rect = new Rect();
        canvas = new Canvas();
    }

    @Override
    public void run() {
        float lastTime = System.nanoTime(); // время на момент запуска run()
        float delta = 0; // эта переменная поможет отпредилить, сколько времени прошло после присваения lastTime значения
        while (running) { // пока поток запущен
            float UPDATE_TIME = 1000000000 / 60;  //60 раз в секунду
            delta += (System.nanoTime() - lastTime) / UPDATE_TIME; // увеличиваем дельту на прошедшее время
            lastTime = System.nanoTime(); // Обновляем время в переменной lastTime
            if (delta > 1) { // если дельта больше 1, в будет запущен метод runGame(), который отобразить кадр игры
                runGame();
                delta--; //уменьшим дельту до 0 и повторим процесс.
            }
        }
    }

    private void runGame() { // Этот метод отвечает за отрисовку игры на canvas
        if (surfaceHolder.getSurface().isValid()) { // Доступен ли surface
            canvas = surfaceHolder.lockCanvas(); // блокируем холст
            canvas.getClipBounds(rect); // отрезаем прямоугольник
            canvas.drawBitmap(bmp, null, rect, null); // рисуем на canvas'e основной Bitmap
            mainRun.getCurrentView().run(); // Запускаем текущий игровой кадр
            surfaceHolder.unlockCanvasAndPost(canvas); // разблокируем холст
        }
    }
    //Запускает GameLoop
    public void startGame() {
        if (running) return;
        running = true;
        gameThread = new Thread(this);
        gameThread.start();
    }

}
