package lab6;

import javax.swing.*;
import java.awt.*;

public class CalcButton extends JButton {
    public CalcButton ( String title ) {
        super ( );
        this.setText ( title );
        this.setBorder ( new RoundedBorder ( 30 ) );
        this.setOpaque ( false );
        this.setContentAreaFilled ( false );
        this.setBorderPainted ( false );
        this.setPreferredSize (new Dimension ( 70,70 ) );
    }
}