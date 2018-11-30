package edu.nau.flippyfish;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Handler;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;


public class GameView extends View {

    // custom view class
    Handler handler;
    Runnable runnable;
    final int UPDATE_MILLIS=30;
    Bitmap background;
    Display display;
    Point point;
    int dWidth, dHeight; //device width and height
    Rect rect;
    Bitmap[] mrFlippy;
    int fishFrame = 0;
    int velocity = 0, gravity = 3;

    int fishY, fishX;

    public GameView(Context context) {
        super(context);
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                invalidate(); //calls onDraw

            }
        };

        background = BitmapFactory.decodeResource(getResources(), R.drawable.voodoo_cactus_underwater);
        display = ((Activity)getContext()).getWindowManager().getDefaultDisplay();
        point = new Point();
        display.getSize(point);
        dWidth = point.x;
        dHeight = point.y;
        rect = new Rect(0,0,dWidth,dHeight);
        mrFlippy = new Bitmap[2];
        mrFlippy[0] = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        mrFlippy[1] = BitmapFactory.decodeResource(getResources(), R.drawable.fish);
        fishX = dWidth/2 - mrFlippy[0].getWidth()/2; // initially the fish will be in center
        fishY = dHeight/2 - mrFlippy[0].getHeight()/2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //drawing background on canvas
        //canvas.drawBitmap(background, 00, 0, null);
        canvas.drawBitmap(background,null,rect,null);
        if(fishFrame == 0) {
            fishFrame = 1;
        }
        else {
            fishFrame = 0;
        }

        velocity += gravity; // as the bird falls, it gets aster and faster
        fishY += velocity;

        // bird will display at center.
        //both sprite animations have ome dimension

        canvas.drawBitmap(mrFlippy[fishFrame],fishX,fishY, null );
        handler.postDelayed(runnable, UPDATE_MILLIS);
    }

    // Touch event


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        int action = event.getAction();
        if(action == MotionEvent.ACTION_DOWN) { // if tap detected
            velocity = -40; // Moves upwards

        }
        return true; // done w touch event
    }
}


