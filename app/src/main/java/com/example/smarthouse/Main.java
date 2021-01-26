package com.example.smarthouse;//package com.example.gravity;

import android.content.Context;
import com.example.smarthouse.Views.MenuView;


public class Main extends MainRun {
    Context context;

    public MenuView getNewView() {
        context = this;
        BitmapLoader bitmapLoader = new BitmapLoader(this.getGamePaint());
        return new MenuView(this);
    }

}


