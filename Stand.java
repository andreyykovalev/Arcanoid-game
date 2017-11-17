package com.company;

public class Stand extends BaseObject
{
    //matrix for drawing

    private static int[][] matrix = {
            {1, 1, 1, 1, 1},
            {1, 0, 0, 0, 1},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
            {0, 0, 0, 0, 0},
    };

    //speed
    private double speed = 1;
    //direction (-1 to the left, +1 to the right)
    private double direction = 0;

    public Stand(double x, double y)
    {
        super(x,y,3);
    }

    //method moves the stand according to the current value of direction

    public void move()
    {
        double dx = speed * direction;
        x = x + dx;

        checkBorders(radius, Arcanoid.game.getWidth() - radius + 1, 1, Arcanoid.game.getHeight() + 1);
    }

    //method for direction equal to 'left'

    public void moveLeft()
    {
        direction = -1;
    }

    //method for direction equal to 'right'


    public void moveRight()
    {
        direction = 1;
    }

    public double getSpeed()
    {
        return speed;
    }

    public double getDirection()
    {
        return direction;
    }

    //drawing the stand on the canvas

    @Override
    public void draw(Canvas canvas)
    {
        canvas.drawMatrix(x - radius + 1, y, matrix, 'M');
    }
}
