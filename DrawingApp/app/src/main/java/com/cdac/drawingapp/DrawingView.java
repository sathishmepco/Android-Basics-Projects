package com.cdac.drawingapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import java.util.Random;

/**
 * Created by A Sathish Kumar on 29-11-2015.
 */
public class DrawingView extends View {

    int r = 0;
    int g = 0;
    int b = 0;
    float x=250,y=250;

    public DrawingView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Paint white = new Paint();
        white.setColor(getResources().getColor(android.R.color.white));
        canvas.drawRect(0, 0, getWidth(), getHeight(), white);

		Paint red = new Paint();
		red.setColor(Color.RED);
		canvas.drawCircle(100, 100, 50, red);
        red.setTextSize(50);
		canvas.drawText("Hellow World!", 200, 200, red);
        canvas.drawCircle(x,y,100,red);

        Paint black = new Paint();
        //black.setColor(getResources().getColor(R.color.black));
        black.setColor(Color.rgb(r, g, b));
        canvas.drawRect(100, 100, 200, 200, black);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {

        x = event.getX();
        y = event.getY();
        invalidate();

        return super.onTouchEvent(event);
    }

    private void changeColor(){
        Random rand = new Random();
        r = rand.nextInt(255);
        g = rand.nextInt(255);
        b = rand.nextInt(255);
        invalidate();
    }
}
