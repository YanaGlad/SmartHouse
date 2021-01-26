package com.example.smarthouse;

import android.graphics.Bitmap;

//Загружает изображения
public class BitmapLoader {
    public static Bitmap house, kitchenLight, bedRoomLight, nothing, darkerKitchen, darkerBedRoom, darkerLivingRoom, livingRoomLight,
            skyBackground, bathLights, darkerBath, backgroundPrice, historyBackground, listDown, listUp, waterBath, waterKitchen, baseButton, baseButtonClicked,
            mainMenuButton, mainMenuButtonClicked, curtains, curtainsClosed, condition, conditionOn;

    public BitmapLoader( GamePaint gamePaint) {
        house = gamePaint.createNewGraphicsBitmap("house.png");
        kitchenLight = gamePaint.createNewGraphicsBitmap("lights.png");
        bedRoomLight = gamePaint.createNewGraphicsBitmap("bedRoomLight.png");
        mainMenuButton = gamePaint.createNewGraphicsBitmap("mainMenuButton.png");
        mainMenuButtonClicked = gamePaint.createNewGraphicsBitmap("mainMenuButtonClicked.png");
        nothing = gamePaint.createNewGraphicsBitmap("nothing.png");
        darkerKitchen = gamePaint.createNewGraphicsBitmap("darkerKitchen.png");
        skyBackground = gamePaint.createNewGraphicsBitmap("day.png");
        darkerBedRoom = gamePaint.createNewGraphicsBitmap("darkerBedRoom.png");
        livingRoomLight = gamePaint.createNewGraphicsBitmap("lustra.png");
        darkerLivingRoom = gamePaint.createNewGraphicsBitmap("darkerMain.png");
        bathLights = gamePaint.createNewGraphicsBitmap("lightsBath.png");
        darkerBath = gamePaint.createNewGraphicsBitmap("darkerBath.png");
        backgroundPrice = gamePaint.createNewGraphicsBitmap("houseBackground.png");
        baseButton = gamePaint.createNewGraphicsBitmap("baseButton.png");
        baseButtonClicked = gamePaint.createNewGraphicsBitmap("baseButtonClicked.png");
        waterBath = gamePaint.createNewGraphicsBitmap("water.png");
        waterKitchen = gamePaint.createNewGraphicsBitmap("waterKitchen.png");
        listDown = gamePaint.createNewGraphicsBitmap("down.png");
        listUp = gamePaint.createNewGraphicsBitmap("up.png");
        historyBackground = gamePaint.createNewGraphicsBitmap("history.png");
        curtains = gamePaint.createNewGraphicsBitmap("curtainsOpened.png");
        curtainsClosed = gamePaint.createNewGraphicsBitmap("curtains.png");
        condition = gamePaint.createNewGraphicsBitmap("condition.png");
        conditionOn = gamePaint.createNewGraphicsBitmap("conditionOn.png");

    }


}
