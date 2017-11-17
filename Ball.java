package com.company;

public class Ball extends BaseObject
{
    //speed

    private double speed;
    //direction  (degrees 0...360)

    private double direction;

    //current value of movement vector (dx,dy)

    private double dx;
    private double dy;

    //whether an object is frozen or movable

    private boolean isFrozen;

    public Ball(double x, double y, double speed, double direction)
    {
        super(x, y, 1);

        this.direction = direction;
        this.speed = speed;

        this.isFrozen = true;
    }

    public double getSpeed()
    {
        return speed;
    }

    public void setSpeed(double speed)
    {
        this.speed = speed;
    }

    public double getDirection()
    {
        return direction;
    }

    public double getDx()
    {
        return dx;
    }

    public double getDy()
    {
        return dy;
    }

    //Setting new movement direction, Calculating new movement vector

    public void setDirection(double direction)
    {
        this.direction = direction;

        double angel = Math.toRadians(direction);
        dx = Math.cos(angel) * speed;
        dy = -Math.sin(angel) * speed;
    }

    //drawing a ball on canvas

    @Override
    public void draw(Canvas canvas)
    {
        canvas.setPoint(x, y, 'O');
    }

    //moving a ball on canvas

    public void move()
    {
        if (isFrozen) return;

        x += dx;
        y += dy;

        checkRebound(1, Arcanoid.game.getWidth(), 1, Arcanoid.game.getHeight() + 5);
    }

    //making sure that the ball is within canvas

    public void checkRebound(int minx, int maxx, int miny, int maxy)
    {
        if (x < minx)
        {
            x = minx + (minx - x);
            dx = -dx;
        }

        if (x > maxx)
        {
            x = maxx - (x - maxx);
            dx = -dx;
        }

        if (y < miny)
        {
            y = miny + (miny - y);
            dy = -dy;
        }

        if (y > maxy)
        {
            y = maxy - (y - maxy);
            dy = -dy;
        }
    }

    //running a ball, recalculating a movement vector

    public void start()
    {
        this.setDirection(direction);
        this.isFrozen = false;
    }
}
