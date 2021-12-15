package lab7;


import java.awt.event.KeyEvent;

public class MyKeyEvent {
    public KeyEvent keyEvent;
    public boolean keyPresed;

    public MyKeyEvent ( KeyEvent keyEvent , boolean keyPresed ) {
        this.keyEvent = keyEvent;
        this.keyPresed = keyPresed;
    }
}
