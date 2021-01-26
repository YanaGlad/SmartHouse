package com.example.smarthouse;

import android.view.MotionEvent;
import android.view.View;

public class TouchListener implements View.OnTouchListener {

    private float touchX, touchY; // координаты прикосновений
    private boolean  touchUp;
    private float screenWidth = 1, screenHeight = 1, k = 1; // отношения ширины и длины, коэффициент подобия

      TouchListener(View view, float screenWidth, float screenHeight) { //Конструктор, который используется если экраны не подобны
        view.setOnTouchListener(this);
        this.screenWidth = screenWidth;
        this.screenHeight = screenHeight;
    }

      TouchListener(View view, float k) { //Конструктор, который используется если экраны подобны
        view.setOnTouchListener(this);
        this.k = k;
    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {

        synchronized (this) {
             touchUp = false;

            if (event.getAction() == MotionEvent.ACTION_UP) { //Получим действие, которое произвел пользователь над экраном
                //Если отрыв пальца от экрана
                    touchX = event.getX() * screenWidth * k;
                    touchY = event.getY() * screenHeight * k;
                     touchUp = true;
            }
        }
        return true;
    }

      boolean up(int x, int y, int touchWidth, int touchHeight) {
        if (touchUp) {
            if (touchLimits(x, y, touchWidth, touchHeight)) {
                touchUp = false;
                return true;
            }
        }
        return false;
    }

    private boolean touchLimits(int x, int y, int touchWidth, int touchHeight) { //Задать рамки касания
        return touchX >= x && touchX <= x + touchWidth && touchY <= y && touchY >= y - touchHeight;
    }

}
