package childrenAbacus;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;

public class AbacusDriver {

    public static void main(String[] args) {
        JFrame frame = new JFrame("חשבוניה");
        frame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        
        JPanel abacusPanel = new Abacus(7, 10, 30, new Color(0x80ffff));

        JPanel controlsPanel = new JPanel();
        controlsPanel.setBorder(BorderFactory.createEtchedBorder());
        controlsPanel.setPreferredSize(new Dimension(abacusPanel.getWidth(), 50));

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        mainPanel.setBorder(BorderFactory.createEtchedBorder());
        mainPanel.add(abacusPanel);
        mainPanel.add(Box.createRigidArea(new Dimension(abacusPanel.getWidth(), 10)));
        mainPanel.add(controlsPanel);

        frame.getContentPane().add(mainPanel);

        frame.pack();
        frame.setVisible(true);
    }

}
