package lab7;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Queue;

public class Racket extends Rectangle implements Runnable {
    private boolean blue;
    private boolean direction;
    private boolean isMoving;
    private int SPEED = 10;
    public static final int X_SIZE = 25;
    public static final int Y_SIZE = 100;
    Queue<MyKeyEvent> queue;
    private final int WINDOW_X;
    private final int WINDOW_Y;


    Racket ( boolean blue , int WINDOW_X , int WINDOW_Y , Queue<MyKeyEvent> q ) {
        super ( (blue) ? 0 : WINDOW_X - X_SIZE , WINDOW_Y / 2 - Y_SIZE / 2 , X_SIZE , Y_SIZE );
        direction = true;
        this.queue = q;
        this.blue = blue;
        this.WINDOW_X = WINDOW_X;
        this.WINDOW_Y = WINDOW_Y;
        isMoving = false;
    }

    public synchronized int getYPosition ( ) {
        return y;
    }

    public void start_moving ( boolean up ) {
        direction = up;
        isMoving = true;
    }

    public void stop_moving ( ) {
        isMoving = false;
    }

    public void draw ( Graphics g ) {
        //System.out.println ( "Racket drawn" );
        if ( blue ) {
            g.setColor ( Color.CYAN );
        } else {
            g.setColor ( Color.pink );
        }
        g.fillRect ( x , y , width , height );
    }


    @Override
    public void run ( ) {
        System.out.println ( "Racket run" );
        long lasTime = System.nanoTime ( );
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;

        while (true) {
            long now = System.nanoTime ( );
            lasTime = now;
            //System.out.println ( "checking Q" );
            synchronized (queue) {
                if ( !queue.isEmpty ( ) ) {
                    System.out.println ( "Q is not empty" );
                    MyKeyEvent key = queue.remove ( );
                    if ( key.keyEvent.getKeyCode ( ) == KeyEvent.VK_UP || key.keyEvent.getKeyCode ( ) == KeyEvent.VK_W ) {
                        if ( key.keyPresed ) {
                            direction = true;
                            isMoving = true;
                        } else if (direction) stop_moving ( );
                    } else {
                        if ( key.keyPresed ) {
                            direction = false;
                            isMoving = true;
                        } else if (!direction) stop_moving ( );
                    }
                }
            }
        }
    }

    public void move ( ) {
        if ( isMoving ) {
            if ( direction && y - SPEED >= 0 ) y -= SPEED;
            if ( !direction && y + SPEED + Y_SIZE <= WINDOW_Y ) y += SPEED;
        }
    }
    public void resetPosition(){
        y=WINDOW_Y / 2 - Y_SIZE / 2;
    }


    public Rectangle getRect ( ) {
        return new Rectangle ( x,y,X_SIZE,Y_SIZE );
    }
}
