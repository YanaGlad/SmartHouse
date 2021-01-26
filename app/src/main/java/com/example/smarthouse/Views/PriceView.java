package com.example.smarthouse.Views;

import android.graphics.Color;

import com.example.smarthouse.BasicButton;
import com.example.smarthouse.GameView;
import com.example.smarthouse.MainRun;
import com.example.smarthouse.R;

import java.util.ArrayList;

import static com.example.smarthouse.BitmapLoader.backgroundPrice;
import static com.example.smarthouse.BitmapLoader.baseButton;
import static com.example.smarthouse.BitmapLoader.baseButtonClicked;
import static com.example.smarthouse.BitmapLoader.historyBackground;
import static com.example.smarthouse.BitmapLoader.listDown;
import static com.example.smarthouse.BitmapLoader.listUp;
import static com.example.smarthouse.Views.MenuView.lightPrice;
import static com.example.smarthouse.Views.MenuView.houseView;
import static com.example.smarthouse.Views.MenuView.waterPrice;

public class PriceView extends GameView {
    private BasicButton historyButton, goBack, up, down;
    public static ArrayList<String> history;
    private int stepTxt, txtY, startTxt;

    public PriceView(MainRun mainRun) {
        super(mainRun);
        history = new ArrayList<>();
        stepTxt = 30;
        startTxt = 50;
        txtY = 50;
        historyButton = new BasicButton(mainRun, 600, 90, mainRun.getString(R.string.more_info), Color.BLACK, 20, baseButton, baseButtonClicked, 10, 30);
        goBack = new BasicButton(mainRun, 20, 540, mainRun.getString(R.string.back), Color.BLACK, 30, baseButton, baseButtonClicked, 10, 40);
        up = new BasicButton(mainRun, 720, 30, listUp, listUp);
        down = new BasicButton(mainRun, 720, 540, listDown, listDown);
    }

    @Override
    public void run() {
        repaint();
        super.getGamePaint().setVisibleBitmap(backgroundPrice, 0, 0);
        historyButton.run(super.getGamePaint());
        goBack.run(super.getGamePaint());
        super.getGamePaint().write(super.getMainRun().getString(R.string.price_full), 12, 50, Color.RED, 45);
        super.getGamePaint().write(super.getMainRun().getString(R.string.price) + " " + super.getMainRun().getString(R.string.for_light) + ": " + lightPrice + " " + super.getMainRun().getString(R.string.rub), 20, 120, Color.BLACK, 40);
        super.getGamePaint().write(super.getMainRun().getString(R.string.price) + " " + super.getMainRun().getString(R.string.for_water) + ": " + waterPrice + " " + super.getMainRun().getString(R.string.rub) , 20, 170, Color.BLACK, 40);

        if (historyButton.isClicked()) {
            super.getGamePaint().setVisibleBitmap(historyBackground, 300, 0);
            up.run(super.getGamePaint());
            down.run(super.getGamePaint());

            txtY = startTxt;
            for (int i = 0; i < history.size(); i++) {
                if (txtY >= 50)
                    super.getGamePaint().write(history.get(i), 320, txtY, Color.BLACK, 20);
                txtY += stepTxt;
            }

            if (down.isClicked()) {
                startTxt -= 30;
                down.setClicked(false);
            }
            if (up.isClicked()) {
                startTxt += 30;
                up.setClicked(false);
            }
        }
    }

    @Override
    public void repaint() {
        if (goBack.isClicked()) {
            super.getMainRun().setView(houseView);
            goBack.setClicked(false);
        }
    }
}
