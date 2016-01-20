package childrenAbacus;

import java.awt.Color;
import javax.swing.JFrame;

public class AbacusDriver {

    public static void main(String[] args) {
        JFrame frame = new JFrame("חשבוניה");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        frame.getContentPane().add(new Abacus(7, 10, 50, new Color(0x80ffff)));     // snow

        frame.pack();
        frame.setVisible(true);
    }

}
