package com.example.a15squares;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.Collections;

public class Grid {
    //private variables
    private float gridLength;
    private float gridHeight;
    private float cellLength;
    ArrayList<Squares> board = new ArrayList<Squares>();
    private int squareNum;




    //Grid Constructor initialize grid with 15 squares
    public Grid(float gridLen, float gridHeight){
        this.gridLength = gridLen;
        this.gridHeight = gridHeight;

        for(int i = 0; i < 15; ++i){
            board.add(new Squares(i+1, gridLen));
        }

        //add blank square for open movement
        board.add(new Squares(0, gridLength));
        board.get(board.size() - 1).setBlank(true);
        randomizeSquares();



    }

    //drawGrid Method, create grid array arrange squares onto 4x4 board
    //Help from Student Cleithon P.
    public void drawGrid(Canvas canvas){

        int row = 0;

        for (int iterate = 0; iterate <= 15; iterate++) {

            //draw square onto grid
            board.get(iterate).draw(canvas, (float)((iterate % 4)) / 4 * gridLength, (float) row / 4 * gridLength);
            //set coordinates of square
            board.get(iterate).setSquareCoordinate((float)((iterate % 4)) / 4 * gridLength, (float) row / 4 * gridLength);

            //move to next row once done
            if ((iterate % 4 == 3) && (iterate != 0)) {
                row++;
            }
        }
     }


    //randomizeSquares Method, initial randomized placement of squares onto grid
    public void randomizeSquares(){
        for(Squares boolSquares:board){
            boolSquares.setCorrect(false);
        }
        Collections.shuffle(board);
        isInRightPlace();

    }

    //isInRightPlace Method, checks if square is in correct place on grid
    //Use number displayed on square, if it equals the cell of the grid's value plus one, then it is in the right place.
    public boolean isInRightPlace(){
        for(Squares squareIndex:board){
            if(squareIndex.getSquareNumber() == board.indexOf(squareIndex) + 1){
                squareIndex.setCorrect(true);
            }
            else{
                squareIndex.setCorrect(false);
            }
        }

        return false;
    }

    //moveSquares method
    //basic swap motion
    public void moveSquares(float xCoord, float yCoord){
        //create temporary values to hold the indexed squares
        Squares tempTouchedSquare = nextToBlank(xCoord, yCoord);
        Squares tempBlankSquare = findBlank();

        //if these indexed squares can move, swap them for movement
        //referenced this api for Collections methods
        if(canMove(tempTouchedSquare, tempBlankSquare)){
            Collections.swap(board, board.indexOf(tempTouchedSquare), board.indexOf(tempBlankSquare));
            isInRightPlace();
        }
    }

    //check if square can move to an adjacent blank square
    //help from student Alex Leonor
    public boolean canMove(Squares touched, Squares blank) {

        int indexTouched = board.indexOf(touched);
        int indexBlank = board.indexOf(blank);

        //top row bounds checking
        if((0 <= indexBlank) && (indexBlank < 3)){
            if((indexTouched == indexBlank - 1) || (indexTouched == indexBlank + 1) || (indexTouched == indexBlank + 4)){
                return true;
            }
        }

        //left side bounds checking
        if(indexBlank % 4 == 0){
            if((indexTouched == indexBlank + 1) || (indexTouched == indexBlank - 4) || (indexTouched == indexBlank + 4)){
                return true;
            }
        }

        //right side bounds checking
        if(indexBlank % 4 == 3){
            if((indexTouched == indexBlank - 1) || (indexTouched == indexBlank - 4) || (indexTouched == indexBlank +4)){
                return true;
            }
        }

        //bottom side bounds checking
        if((12 <= indexBlank) && (indexBlank <= 15)){
            if((indexTouched == indexBlank - 1) || (indexTouched == indexBlank + 1) || (indexTouched == indexBlank - 4)){
                return true;
            }
        }

        //middle bounds checking
        else{
            if((indexTouched == indexBlank + 1) || (indexTouched == indexBlank - 1) || (indexTouched == indexBlank + 4) || (indexTouched == indexBlank -4)) {
                return true;
            }
        }

     return false;
    }

    //helper method to help above method in checking if square can move to blank space
    public Squares findBlank(){
        for(Squares arraySqr: board){
            if(arraySqr.getBlank()){
                return arraySqr;
            }
        }
        return null;
    }

    //helper method to distinguish if touched square legally can move
    public Squares nextToBlank(float x, float y){
        for(Squares squareArray: board){
            if(squareArray.isWithinTouch(x,y)){
                return squareArray;
            }
        }
        return null;
    }

}
