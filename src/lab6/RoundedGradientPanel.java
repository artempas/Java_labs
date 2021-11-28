package lab6;

import java.awt.*;
import java.awt.geom.*;

import javax.swing.JPanel;


public class RoundedGradientPanel extends JPanel {

    private Color color1;
    private Color color2;
    private int radius;
    private boolean buttons;
    private boolean verticalIncrease;

    public RoundedGradientPanel ( Color color1 , Color color2 , int radius ) {
        super ( );
        this.setOpaque ( false );
        this.radius = radius;
        this.color1 = color1;
        this.color2 = color2;
        this.buttons = false;
        this.verticalIncrease = false;

    }

    public RoundedGradientPanel ( Color color1 , Color color2 , int radius , boolean buttons ) {
        super ( );
        this.setOpaque ( false );
        this.radius = radius;
        this.color1 = color1;
        this.color2 = color2;
        this.buttons = buttons;
        this.verticalIncrease = false;

    }

    public RoundedGradientPanel ( Color color1 , Color color2 , int radius , char horizontalIncrease ) {
        super ( );
        this.setOpaque ( false );
        this.radius = radius;
        this.color1 = color1;
        this.color2 = color2;
        this.buttons = false;
        this.verticalIncrease = true;

    }

    @Override
    protected void paintComponent ( Graphics g ) {
        super.paintComponent ( g );
        Graphics2D g2d = (Graphics2D) g;
        Shape clip = g2d.getClip ( );
        Area rect = new Area ( clip );

        g2d.setRenderingHint ( RenderingHints.KEY_RENDERING , RenderingHints.VALUE_RENDER_QUALITY );
        int w = getWidth ( );
        int h = getHeight ( );
        if ( buttons )
            h += 20;
        if ( verticalIncrease ) w += 20;
        GradientPaint gp = new GradientPaint ( 0 , 0 , color1 , w , h , color2 );
        RoundRectangle2D r = new RoundRectangle2D.Float ( 0 , 0 , w , h , radius , radius );
        g2d.setPaint ( gp );
        g2d.fill ( r );


    }
}