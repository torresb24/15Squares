package com.example.a15squares;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.graphics.Canvas;

public class GameView extends SurfaceView implements View.OnClickListener, View.OnTouchListener {

    private Grid gameGrid;
    private float screenHeight;
    private float screenWidth;


    //GameView Constructor
    public GameView(Context context, AttributeSet attrs) {

        super(context, attrs);



        //Dynamically change game dimensions based off of screen data of device
        //Used below link to learn how to do this
        //https://developer.android.com/reference/android/util/DisplayMetrics
        screenHeight = context.getResources().getDisplayMetrics().heightPixels;
        screenWidth = context.getResources().getDisplayMetrics().widthPixels;

        //need for onDraw
        setWillNotDraw(false);

        //create new board
        gameGrid = new Grid(screenWidth, screenHeight);
    }

    protected void onDraw(Canvas canvas) {
        //draw grid
        gameGrid.drawGrid(canvas);
        invalidate();

    }

    @Override
    public void onClick(View view) {
        //randomize squares when button clicked
        gameGrid.randomizeSquares();

    }

    @Override
    //move squares when touched
    public boolean onTouch(View view, MotionEvent motionEvent) {
        //referenced this to figure out how to touch individual square
        //https://developer.android.com/reference/android/graphics/Rect
        float xCoordSquare = motionEvent.getX();
        float yCoordSquare = motionEvent.getY();
        //call for swap on click
        gameGrid.moveSquares(xCoordSquare, yCoordSquare);
        invalidate();
        return false;
    }
}
