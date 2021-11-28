package lab6;

import javax.swing.*;
import java.awt.*;

public class GradientPanel extends JPanel {


        @Override
        protected void paintComponent(Graphics grphcs) {
            super.paintComponent(grphcs);
            Graphics2D g2d = (Graphics2D) grphcs;
            g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);
            GradientPaint gp = new GradientPaint(0, 0,Color.decode ( "#2C19D4" ).darker (),
                     getWidth (), getHeight(),Color.decode("#2414AD").darker ().darker ());
            g2d.setPaint(gp);
            g2d.fillRect(0, 0, getWidth(), getHeight());

        }
}
