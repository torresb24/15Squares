package com.example.a15squares;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

import java.util.ArrayList;

public class Squares {
    //private variables
    private Paint squareColor; //the square Color
    private float squareLength;
    private int squareNumber;
    private float xCoord;
    private float yCoord;
    private Paint squareText = new Paint();
    private Rect bounds;
    private float textSize;
    private boolean isBlank = false;
    private Paint correctColor = new Paint();
    private boolean isCorrect = false;



    //Square Constructor
    public Squares(int squareNum, float squareWidth)
    {
        squareLength = squareWidth / 4; //hardcode number so all squares are equal
        squareColor = new Paint();
        squareColor.setARGB(255, 31, 220,250);
        xCoord = 0;
        yCoord = 0;
        squareNumber = squareNum;
        squareText.setColor(Color.BLACK);
        squareText.setTextSize(squareWidth / 8);
        textSize = squareLength/2;
        correctColor.setColor(Color.GREEN);


    }

    //drawSquare method
    public void draw(Canvas canvas, float coordX, float coordY)
    {
        //create square object
        //Help with Rect object from student Alex Leonor
        //Used rect object in order to get center of square and for bounds checking by using premade methods for calculations of square interface placement
        this.bounds = new Rect((int) coordX, (int) coordY, (int)(coordX + squareLength), (int) (coordY + squareLength));

        //draw square
        //if statement in order to allow program to draw blank space when at 16th position

        if(!isBlank) {
            if(isCorrect) {
                canvas.drawRect(bounds, correctColor);
            }
            else {
                canvas.drawRect(bounds, squareColor);
            }


            //place number on top of each square object 1-15
            //Referenced this website to refresh how to turn int into String
            canvas.drawText(Integer.toString(squareNumber), bounds.centerX(), bounds.centerY() + textSize / 3, this.squareText);
        }
    }

    //helper method to check bounds before drawing square
    public void setSquareCoordinate(float x, float y){
        this.xCoord = x;
        this.yCoord = y;
    }

    //set blank square for open movement
    public void setBlank(boolean isEmpty){
        this.isBlank = isEmpty;
    }

    public boolean getBlank(){
        return this.isBlank;
    }

    //Are X and Y corrdinates of touch within given squares bounds? method
    public boolean isWithinTouch(float xTap, float yTap){
        return bounds.contains((int) xTap, (int) yTap);
    }

    public int getSquareNumber(){
        return this.squareNumber;
    }

    public void setCorrect(boolean bool){
        this.isCorrect = bool;
    }
}
