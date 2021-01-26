package com.example.smarthouse;

import android.graphics.Bitmap;

import androidx.annotation.Nullable;

public class BasicButton implements Loopable {
    private MainRun mainRun;
    private boolean clicked;
    private String text = null;
    private int size = 0;
    private Bitmap button, clickedButton;
    private int stepX = 0, stepY = 0;
    private int color;
    private int x,y;

    //Конструктор кнопки с текстом
    public BasicButton(MainRun mainRun, int x, int y, @Nullable String text, int color, int size, Bitmap button, Bitmap clickedButton, int stepX, int stepY) {
        this.x = x;
        this.y = y;
        this.text = text;
        this.color = color;
        this.size = size;
        this.mainRun = mainRun;
        this.button = button;
        this.clickedButton = clickedButton;
        this.stepX = stepX;
        this.stepY = stepY;
        clicked = false;
    }

    //Конструктор кнопки без текста
    public BasicButton(MainRun mainRun, int x, int y, Bitmap button, Bitmap clickedButton) {
        this.x = x;
        this.y = y;
         this.mainRun = mainRun;
        this.button = button;
        this.clickedButton = clickedButton;
        clicked = false;

    }

    @Override
    public void run(GamePaint gamePaint) {
        repaint();
        if (clicked)
            gamePaint.setVisibleBitmap(clickedButton, x, y);//baseButtonClicked
        else gamePaint.setVisibleBitmap(button, x, y); //baseButton

        if (text != null) gamePaint.write(text, x + stepX, y + stepY, color, size);
    }

    @Override
    public void repaint() {
        if (mainRun.getTouchListener().up(x, y + button.getHeight(), button.getWidth(), button.getHeight())) {
            clicked = !clicked;
        }
    }

    public boolean isClicked() {
        return clicked;
    }

    public void setClicked(boolean clicked) {
        this.clicked = clicked;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setText(String text) {
        this.text = text;
    }
}
