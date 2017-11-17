package com.company;

public class Brick extends BaseObject
{
    //a matrix for drawing
    private static int[][] matrix = {
            {0, 0, 0, 0, 0},
            {0, 1, 1, 1, 0},
            {0, 1, 1, 1, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    public Brick(double x, double y)
    {
        super(x,y,3);
    }

    //drawing bricks

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'H');
    }

    //body is empty as an brick don't move on canvas

    @Override
    public void move()
    {
        //do nothing
    }
}
