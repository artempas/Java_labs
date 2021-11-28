package lab6;

import javax.swing.border.Border;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class RoundedBorder implements Border {

    private int radius;
    private int x;
    private int y;
    private int width;
    private int height;


    RoundedBorder ( int radius ) {
        this.radius = radius;
    }


    public Insets getBorderInsets ( Component c ) {
        return new Insets ( this.radius + 1 , this.radius + 1 , this.radius + 2 , this.radius );
    }


    public boolean isBorderOpaque ( ) {
        return true;
    }


    public void paintBorder ( Component c , Graphics g , int x , int y , int width , int height ) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        g.drawRoundRect ( x , y , width - 1 , height - 1 , radius , radius );
    }


}
