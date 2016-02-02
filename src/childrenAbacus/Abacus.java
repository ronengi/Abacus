package childrenAbacus;

import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;


public class Abacus extends JPanel implements Drawable {

    private ArrayList<Wire> wires;
    public final int beadShiftDistance = 8;
    private Color abacusBgColor; 
    private JLabel label1;


    // constructor
    public Abacus(int numWires, int numBeads, int beadDiameter, Color bgColor) {
        setBorder(BorderFactory.createEtchedBorder());
        setAbacusBgColor(bgColor);
        wires = new ArrayList<Wire>();
        for (int wireIndex = 0; wireIndex < numWires; ++wireIndex) {
            Wire newWire = new Wire(numBeads, beadDiameter, this);
            wires.add(newWire);
        }
        setAbacusSize(beadDiameter);

        addMouseListener(new AbacusMouseListener());

        label1 = new JLabel( "Hello" );
        add(label1);
    }

    
    // getters
    public ArrayList<Wire> getWires() {
        return wires;
    }

    public Color getAbacusBgColor() {
        return abacusBgColor;
    }

    public int getNumWires() {
        return getWires().size();
    }
    
    public int getNumBeads() {
        return (getNumWires() > 0) ? getWires().get(0).getBeads().size() : 0;
    }

    public int getBeadDiameter() {
        return (getNumWires() > 0 && getNumBeads() > 0) ? getWires().get(0).getBeads().get(0).getBeadDiameter() : 0;
    }
    

    public void paintComponent(Graphics gr) {
        super.paintComponent(gr);
        draw(gr);
    }


    // setters
    public void setAbacusBgColor(Color abacusBgColor) {
        this.abacusBgColor = abacusBgColor;
        setBackground(abacusBgColor);
    }


    
    @Override
    public void draw(Graphics gr) {
        for (Wire nextWire : getWires()) {
            nextWire.draw(gr);            
        }
    }

    private void setAbacusSize(int beadDiameter) {
        int abacusHeight = getWires().size() * beadDiameter;
        int abacusWidth = (getWires().get(0).getBeads().size() + beadShiftDistance) * beadDiameter;
        setPreferredSize(new Dimension(abacusWidth, abacusHeight));        
        Container frameParent = getTopLevelAncestor();  // resize also the frame parent
        if (frameParent != null)
            ((JFrame)frameParent).pack();
    }


    @Override
    public boolean wasClicked(int X, int Y) {
        boolean found = false;
        int wireIndex = 0;
        while (!found && wireIndex < getWires().size()) {
            found = getWires().get(wireIndex).wasClicked(X, Y);
            ++wireIndex;
        }
        
        if (found) {
            label1.setText( "( " + X + ", " + Y + " )" );
            repaint();
        }

        return found;
    }

    
    ////////////////////////////////////////////////////////////////////////////////////////////////////
    private class AbacusMouseListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                wasClicked(e.getX(), e.getY());
            }
        }

        @Override
        public void mouseEntered(MouseEvent arg0) {
            // label1.setText("Entered");
        }

        @Override
        public void mouseExited(MouseEvent arg0) {
            // label1.setText("Exited");
        }

        @Override
        public void mousePressed(MouseEvent arg0) {
            // label1.setText("Pressed");
        }

        @Override
        public void mouseReleased(MouseEvent arg0) {
            // label1.setText("Released");            
        }

    } // AbacusMouseListener



} // Abacus
