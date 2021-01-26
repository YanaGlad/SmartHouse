package com.example.smarthouse.Views;


import android.graphics.Color;
import com.example.smarthouse.BasicButton;
import com.example.smarthouse.GameView;
import com.example.smarthouse.MainRun;
import com.example.smarthouse.R;

import static com.example.smarthouse.BitmapLoader.backgroundPrice;
import static com.example.smarthouse.BitmapLoader.mainMenuButton;
import static com.example.smarthouse.BitmapLoader.mainMenuButtonClicked;


public class MenuView extends GameView {

    private BasicButton play;
    public static HouseView houseView;
    public static PriceView priceView;

    public static int lightPrice, waterPrice;

    public MenuView(MainRun mainRun){
        super(mainRun);
        houseView = new HouseView(mainRun);
        priceView = new PriceView(mainRun);
        lightPrice = 0;
        waterPrice = 0;
        play = new BasicButton(mainRun, 220, 250, mainRun.getString(R.string.start), Color.BLACK, 40, mainMenuButton, mainMenuButtonClicked, 50, 50);

    }

    @Override
    public void run() {
        repaint();
        super.getGamePaint().setVisibleBitmap(backgroundPrice, 0, 0);
        play.run(super.getGamePaint());
    }

    @Override
    public void repaint() {
        if (play.isClicked()) super.getMainRun().setView(houseView);
    }

}
