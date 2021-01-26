package com.example.smarthouse;

import android.graphics.Color;

import com.example.smarthouse.Views.MenuView;

import java.util.ArrayList;

import static com.example.smarthouse.BitmapLoader.baseButton;
import static com.example.smarthouse.BitmapLoader.baseButtonClicked;
import static com.example.smarthouse.BitmapLoader.bathLights;
import static com.example.smarthouse.BitmapLoader.bedRoomLight;
import static com.example.smarthouse.BitmapLoader.conditionOn;
import static com.example.smarthouse.BitmapLoader.curtainsClosed;
import static com.example.smarthouse.BitmapLoader.darkerBath;
import static com.example.smarthouse.BitmapLoader.darkerBedRoom;
import static com.example.smarthouse.BitmapLoader.darkerKitchen;
import static com.example.smarthouse.BitmapLoader.darkerLivingRoom;
import static com.example.smarthouse.BitmapLoader.house;
import static com.example.smarthouse.BitmapLoader.kitchenLight;
import static com.example.smarthouse.BitmapLoader.livingRoomLight;
import static com.example.smarthouse.BitmapLoader.nothing;
import static com.example.smarthouse.Views.MenuView.priceView;
import static com.example.smarthouse.Views.PriceView.history;

public class SmartHouse implements Loopable {
    private ArrayList<BasicButton> lamps, water, other; //лампы и вода
    private ArrayList<BasicButton> lightFunctions, waterFunctions, otherFunctions;//кнопки функционала умного дома
    private ArrayList<EasyTimer> timers, waterTimers;//таймеры для счета времени использования воды и света
    private int dark;
    private BasicButton lightsControl, waterControl, moneyControl, otherControl;
    private MainRun mainRun;
    private int temperature;
    private EasyTimer conditionTimer;

    public SmartHouse(MainRun mainRun) {
        this.mainRun = mainRun;

        temperature = 22;
        conditionTimer = new EasyTimer();

        lamps = new ArrayList<>();
        water = new ArrayList<>();
        other = new ArrayList<>();

        otherFunctions = new ArrayList<>();
        lightFunctions = new ArrayList<>();
        waterFunctions = new ArrayList<>();

        lightsControl = new BasicButton(mainRun, 600, 20, mainRun.getString(R.string.light_control), Color.BLACK, 20, baseButton, baseButtonClicked, 10, 30);
        waterControl = new BasicButton(mainRun, 600, 80, mainRun.getString(R.string.water_control), Color.BLACK, 20, baseButton, baseButtonClicked, 10, 30);
        moneyControl = new BasicButton(mainRun, 20, 20, mainRun.getString(R.string.price), Color.BLACK, 25, baseButton, baseButtonClicked, 15, 30);
        otherControl = new BasicButton(mainRun, 410, 20, mainRun.getString(R.string.other_control), Color.BLACK, 20, baseButton, baseButtonClicked, 10, 30);

        lightFunctions.add(new BasicButton(mainRun, 600, 80, mainRun.getString(R.string.light) + " " + mainRun.getString(R.string.in_bed_room), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));
        lightFunctions.add(new BasicButton(mainRun, 600, 140, mainRun.getString(R.string.light) + " " + mainRun.getString(R.string.in_living_room), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));
        lightFunctions.add(new BasicButton(mainRun, 600, 200, mainRun.getString(R.string.light) + " " + mainRun.getString(R.string.in_bathroom), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));
        lightFunctions.add(new BasicButton(mainRun, 600, 260, mainRun.getString(R.string.light) + " " + mainRun.getString(R.string.in_the_kitchen), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));

        waterFunctions.add(new BasicButton(mainRun, 600, 140, mainRun.getString(R.string.water) + " " + mainRun.getString(R.string.in_bathroom), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));
        waterFunctions.add(new BasicButton(mainRun, 600, 200, mainRun.getString(R.string.water) + " " + mainRun.getString(R.string.in_the_kitchen), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));

        otherFunctions.add(new BasicButton(mainRun, 410, 80, mainRun.getString(R.string.curtains_bedroom), Color.BLACK, 18, baseButton, baseButtonClicked, 5, 30));
        otherFunctions.add(new BasicButton(mainRun, 410, 140, mainRun.getString(R.string.condition), Color.BLACK, 20, baseButton, baseButtonClicked, 5, 30));

        lamps.add(new BasicButton(mainRun, 205, 20, bedRoomLight, nothing));
        lamps.add(new BasicButton(mainRun, 330, 410, livingRoomLight, nothing));
        lamps.add(new BasicButton(mainRun, 520, 160, bathLights, nothing));

        lamps.add(new BasicButton(mainRun, 90, 220, kitchenLight, nothing));
        lamps.add(new BasicButton(mainRun, 200, 220, kitchenLight, nothing));
        lamps.add(new BasicButton(mainRun, 295, 220, kitchenLight, nothing));
        lamps.add(new BasicButton(mainRun, 397, 220, kitchenLight, nothing));

        water.add(new BasicButton(mainRun, 710, 283, BitmapLoader.waterBath, nothing));
        water.add(new BasicButton(mainRun, 345, 290, BitmapLoader.waterKitchen, nothing));

        other.add(new BasicButton(mainRun, 196, 73, BitmapLoader.curtains, curtainsClosed));
        other.add(new BasicButton(mainRun, 50, 385, BitmapLoader.condition, conditionOn));

        waterTimers = new ArrayList<>();
        for (int i = 0; i < water.size(); i++) waterTimers.add(new EasyTimer());
        for (int i = 0; i < water.size(); i++) waterTimers.get(i).startTimer();


        timers = new ArrayList<>();
        for (int i = 0; i < lamps.size(); i++) {
            timers.add(new EasyTimer());
        }
        dark = 0;
        for (int i = 0; i < timers.size(); i++) {
            timers.get(i).startTimer();
        }

        conditionTimer.stopTimer();
    }

    //Если та или иная кнопка нажата, свет/вода прекращают работу, время, которое вернет таймер,
    // привязанный к кнопке, заносится в историю и в переменную lightPrice/waterPrice
    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        gamePaint.setVisibleBitmap(house, 0, -50);
        for (int i = 0; i < lamps.size(); i++) lamps.get(i).run(gamePaint);
        for (int i = 0; i < water.size(); i++) water.get(i).run(gamePaint);
        for (int i = 0; i <other.size(); i++)other.get(i).run(gamePaint);

        lightsControl.run(gamePaint);
        otherControl.run(gamePaint);
        if (!lightsControl.isClicked()) waterControl.run(gamePaint);
        moneyControl.run(gamePaint);

        gamePaint.write(mainRun.getString(R.string.temperature) + " +" + temperature,510,150,Color.BLACK,20);

        for (int i = 0; i < 3; i++) {
            if (lamps.get(i).isClicked()) {
                String info = "";
                switch (i) {
                    case 0:
                        gamePaint.setVisibleBitmap(darkerBedRoom, 0, -50);
                        info = mainRun.getString(R.string.using_lamp) + " " + mainRun.getString(R.string.in_bed_room) + " = ";
                        break;
                    case 1:
                        gamePaint.setVisibleBitmap(darkerLivingRoom, 0, -50);
                        info = mainRun.getString(R.string.using_lamp) + " " + mainRun.getString(R.string.in_living_room) + " = ";
                        break;
                    case 2:
                        gamePaint.setVisibleBitmap(darkerBath, 0, -50);
                        info = mainRun.getString(R.string.using_lamp) + " " + mainRun.getString(R.string.in_bathroom) + " = ";
                        break;
                }
                if (Math.round(timers.get(i).getPssedTime()) > 0)
                    history.add(info + Math.round(timers.get(i).getPssedTime()));
                MenuView.lightPrice += Math.round(timers.get(i).getPssedTime());
                timers.get(i).stopTimer();
            } else {
                if (timers.get(i).isStop())
                    timers.get(i).startTimer();
            }
        }

        if (lightsControl.isClicked()) {
            if (waterControl.isClicked()) waterControl.setClicked(false);
            for (int i = 0; i < lightFunctions.size(); i++)
                lightFunctions.get(i).run(gamePaint);
        }
        if (waterControl.isClicked()) {
            if (lightsControl.isClicked()) lightsControl.setClicked(false);
            for (int i = 0; i < waterFunctions.size(); i++)
                waterFunctions.get(i).run(gamePaint);
        }

        if (otherControl.isClicked()) {
            for (int i = 0; i < otherFunctions.size(); i++) {
                otherFunctions.get(i).run(gamePaint);
            }
        }

        for (int i = 0; i < dark; i++) gamePaint.setVisibleBitmap(darkerKitchen, 0, -50);

    }

    @Override
    public void repaint() {

        for (int i = 0; i < 3; i++) {
            if (lightFunctions.get(i).isClicked()) {
                if (lamps.get(i).isClicked()) {
                    lamps.get(i).setClicked(false);
                    timers.get(i).startTimer();

                } else {
                    lamps.get(i).setClicked(true);
                    String name = "";
                    switch (i) {
                        case 0:
                            name = mainRun.getString(R.string.in_bed_room);
                            ;
                            break;
                        case 1:
                            name = mainRun.getString(R.string.in_the_kitchen);
                            break;
                        case 2:
                            name = mainRun.getString(R.string.in_bathroom);
                            break;
                    }
                    if (Math.round(timers.get(i).getPssedTime()) > 0)
                        history.add(mainRun.getString(R.string.using_lamp) + " " + name + " = " + Math.round(timers.get(i).getPssedTime()));
                    MenuView.lightPrice += Math.round(timers.get(i).getPssedTime());
                    timers.get(i).stopTimer();
                }
            }
            lightFunctions.get(i).setClicked(false);
        }

        if (lightFunctions.get(3).isClicked()) {
            for (int i = 3; i < lamps.size(); i++)
                if (lamps.get(i).isClicked()) {
                    lamps.get(i).setClicked(false);
                    if (timers.get(i).isStop()) timers.get(i).startTimer();
                } else lamps.get(i).setClicked(true);

            lightFunctions.get(3).setClicked(false);
        }

        dark = 0;
        for (int i = 3; i < lamps.size(); i++) {
            if (lamps.get(i).isClicked()) {
                dark++;
                if (Math.round(timers.get(i).getPssedTime()) > 0)
                    history.add(mainRun.getString(R.string.using_lamp) + " " + (i - 2) + mainRun.getString(R.string.in_the_kitchen) + " = " + Math.round(timers.get(i).getPssedTime()));
                MenuView.lightPrice += Math.round(timers.get(i).getPssedTime());
                timers.get(i).stopTimer();
            } else if (timers.get(i).isStop()) timers.get(i).startTimer();
        }

        for (int i = 0; i < water.size(); i++) {
            if (water.get(i).isClicked()) {
                String info = "";
                switch (i) {
                    case 0:
                        info = mainRun.getString(R.string.using_water) + " " + mainRun.getString(R.string.in_bathroom) + " ";
                        break;
                    case 1:
                        info = mainRun.getString(R.string.using_water) + " " + mainRun.getString(R.string.in_the_kitchen) + " ";
                        break;
                }
                if (Math.round(waterTimers.get(i).getPssedTime()) > 0)
                    history.add(info + " = " + Math.round(waterTimers.get(i).getPssedTime()));
                MenuView.waterPrice += Math.round(waterTimers.get(i).getPssedTime());
                waterTimers.get(i).stopTimer();
            } else if (waterTimers.get(i).isStop()) waterTimers.get(i).startTimer();
        }


        if (moneyControl.isClicked()) {
            mainRun.setView(priceView);
            moneyControl.setClicked(false);
        }

        for (int i = 0; i < waterFunctions.size(); i++) {
            if (waterFunctions.get(i).isClicked()) {
                if (water.get(i).isClicked()) water.get(i).setClicked(false);
                else water.get(i).setClicked(true);
                waterFunctions.get(i).setClicked(false);
            }
        }
        for (int i = 0; i < otherFunctions.size(); i++) {
            if (otherFunctions.get(i).isClicked()) {
                if (other.get(i).isClicked()) other.get(i).setClicked(false);
                else other.get(i).setClicked(true);
            }
            otherFunctions.get(i).setClicked(false);
        }

        if(other.get(1).isClicked()) {
             if(!conditionTimer.isStop())System.out.println(conditionTimer.getPssedTime());
            if (conditionTimer.isStop()) conditionTimer.startTimer();
            if(conditionTimer.getPssedTime()>10 && temperature>=16){
                temperature-=2;
                conditionTimer.stopTimer();
            }
        }else{
            if(conditionTimer.isStop())conditionTimer.startTimer();
            if(temperature<=25 && conditionTimer.getPssedTime()>10){
                temperature+=2;
                conditionTimer.stopTimer();
            }
        }

    }

}
