package com.example.smarthouse;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;
import android.view.WindowManager;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class MainRun extends AppCompatActivity {

    private GamePaint gamePaint;
    private TouchListener touchListener;
    private GameView gameView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        // В методе onCreate() создан экземпляр класса  Point, который содержит в себе 2 координаты x и y.
        Point point = new Point();
        // Теперь нужно получить размер дисплея телефона, на котором запущено приложение и поместить это значение в point.
        Display display = getWindowManager().getDefaultDisplay();
        display.getSize(point);
        //Cтандартная высота и ширина экрана.
        float final_height = 600;
        float final_width = 800;

        // Теперь этими размерами определяется самый основной Bitmap, на котором и будет происходить отображение игры.
        // Его размер всегда будет определенным – 800*600.

        Bitmap bmp = Bitmap.createBitmap((int) final_width, (int) final_height, Bitmap.Config.ARGB_8888);
        float w = final_width / point.x;
        float h = final_height / point.y;

        //Главное  назначение  этих отношений/коэффициента
        // подобия экранов – подстроить слушатель событий( прикосновений к экрану )
        // под размер изображения.

        GameLoop gameLoop = new GameLoop(this, bmp);
        gamePaint = new GamePaint(getAssets(), bmp);

        if (w == h) touchListener = new TouchListener(gameLoop, w);
        else touchListener = new TouchListener(gameLoop, w, h);

        gameView = getNewView();

        setContentView(gameLoop);
        gameLoop.startGame();
    }

    public GamePaint getGamePaint() {
        return gamePaint;
    }

    public TouchListener getTouchListener() {
        return touchListener;
    }

    public void setView(GameView gameView) {
        if (gameView == null) {
            throw new IllegalArgumentException("Something went wrong :(");
        }
        this.gameView = gameView;
    }

    public GameView getCurrentView() {
        return gameView;
    }

    public GameView getNewView() {
        return gameView;
    }


}