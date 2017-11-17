package com.company;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Iterator;

//The main class of this game

public class Arcanoid
{
    public static Arcanoid game;

    //width and height of the field

    private int width;
    private int height;

    //this array will serve for storing values type of bricks

    private ArrayList<Brick> bricks = new ArrayList<Brick>();

    //this variable functions as a ball in the game

    private Ball ball;

    //this variable functions as a stand in the game

    private Stand stand;

    //checks whether the game is over or not
    private boolean isGameOver = false;

    public Arcanoid(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks()
    {
        return bricks;
    }

    public Ball getBall()
    {
        return ball;
    }

    public void setBall(Ball ball)
    {
        this.ball = ball;
    }

    public Stand getStand()
    {
        return stand;
    }

    public void setStand(Stand stand)
    {
        this.stand = stand;
    }

    //this method draws all the game objects: a ball, bricks, a stand

    public void draw(Canvas canvas)
    {
        drawBoders(canvas);
        for (Brick brick : game.bricks) {
            brick.draw(canvas);
        }
        game.ball.draw(canvas);
        game.stand.draw(canvas);
    }

    //this method will draw the borders of the game

    private void drawBoders(Canvas canvas)
    {
        //draw game
        for (int i = 0; i < width + 2; i++)
        {
            for (int j = 0; j < height + 2; j++)
            {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++)
        {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++)
        {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    //the main cycle of the program

    public void run() throws Exception
    {
        //creating a canvas

        Canvas canvas = new Canvas(width, height);

        //creating keyboard observer and staring it

        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        //the following will allow us to use a keyboard during the game, while a variable 'isGameOver' isn't 'false'

        while (!isGameOver)
        {
            //this condition guarantees that the actions will be completed only if any button is pressed

            if (keyboardObserver.hasKeyEvents())
            {
                KeyEvent event = keyboardObserver.getEventFromTop();

                //'left arrow' means moving the figure to the left

                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();

                    //'right arrow' means moving the figure to the right

                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();

                    //if 'whitespace' is pressed we start the ball

                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            //moving the objects

            move();

            //checking whether there were any bumps during the game

            checkBricksBump();
            checkStandBump();

            //checking if the ball has passed through the stand
            checkEndGame();

            //clearing the canvas

            canvas.clear();
            draw(canvas);
            canvas.print();

            //pause
            Thread.sleep(1000);
        }

        //printing 'game over'

        System.out.println("Game Over!");
    }

    // this method moves a ball and a stand

    public void move()
    {
        //moves the ball
        //moves the brick
        game.ball.move();
        game.stand.move();
    }

    //checking bumps with bricks. if there was any the ball flies to a random direction 0...360 degrees

    public void checkBricksBump()
    {
        //if the ball bumps the brick, the bricks gets deleted, ball gets pushed to a random direction

        Iterator<Brick> brickIterator = bricks.iterator();
        while (brickIterator.hasNext()) {
            if (ball.isIntersec(brickIterator.next())) {
                double angel = Math.random() * 360;
                ball.setDirection(angel);
                brickIterator.remove();
            }
        }
    }

    //checking bumps. if there was any the ball flies to a random direction 80...100 degrees

    public void checkStandBump()
    {
        //if the ball bumps the stand, the ball gets pushed to a random direction

        if (ball.isIntersec(stand)) {
            double angel = 80 + Math.random()*20;
            ball.setDirection(angel);
        }
    }

    //checking if the ball was flown through the stand. if so 'game over'

    public void checkEndGame()
    {
        if (ball.y > getHeight())
            isGameOver = true;
    }

    public int getWidth()
    {
        return width;
    }

    public int getHeight()
    {
        return height;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    //starting the game, creating all its objects

    public static void main(String[] args) throws Exception
    {
        game = new Arcanoid(20, 30);

        Ball ball = new Ball(10, 29, 2,  95);
        game.setBall(ball);

        Stand stand = new Stand(10, 30);
        game.setStand(stand);

        game.getBricks().add(new Brick(3, 3));
        game.getBricks().add(new Brick(7, 5));
        game.getBricks().add(new Brick(12, 5));
        game.getBricks().add(new Brick(16, 3));

        game.run();
    }
}
