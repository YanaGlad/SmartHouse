package com.example.smarthouse.Views;

import com.example.smarthouse.GameView;
import com.example.smarthouse.MainRun;
import com.example.smarthouse.SmartHouse;
import static com.example.smarthouse.BitmapLoader.skyBackground;

public class HouseView extends GameView {
    private SmartHouse smartHouse;

    public HouseView(MainRun mainRun) {
        super(mainRun);
        smartHouse = new SmartHouse(mainRun);
     }


    @Override
    public void run() {
        repaint();
        super.getGamePaint().setVisibleBitmap(skyBackground,0,0);
        smartHouse.run(super.getGamePaint());
     }

    @Override
    public void repaint() {

    }
}
