package childrenAbacus;

import java.awt.Color;
import java.awt.Graphics;

public class Bead implements Drawable {

    public enum Shift { left, right }

    private Wire wireParent;
    private int beadDiameter;
    private int leftX;
    private int topY;
    private Color beadColor;
    private Shift beadShift;

    // constructor
    public Bead(int beadDiameter, Color beadColor, Wire wireParent) {
        this.wireParent = wireParent;
        this.beadDiameter = beadDiameter;

        setTopY(getWireIndex() * beadDiameter);
        setLeftX(getBeadIndex() * beadDiameter);

        this.beadColor = beadColor;
        setBeadShift(Shift.left);
    }
    

    // getters
    public Wire getWireParent() {
        return wireParent;
    }

    public int getBeadIndex() {
        int beadIndex = getWireParent().getBeads().indexOf(this); 
        beadIndex = (beadIndex == -1) ? getWireParent().getBeads().size() : beadIndex;
        return beadIndex;
    }

    public int getWireIndex() {
        return getWireParent().getWireIndex();
    }
    
    public int getBeadDiameter() {
        return beadDiameter;
    }

    public int getLeftX() {
        return leftX;
    }

    public int getTopY() {
        return topY;
    }

    public Color getBeadColor() {
        return beadColor;
    }

    public Shift getBeadShift() {
        return beadShift;
    }


    // setters
    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }
    
    public void setBeadColor(Color beadColor) {
        this.beadColor = beadColor;
    }

    public void setBeadShift(Shift beadShift) {
        this.beadShift = beadShift;
    }

    public void toggleBeadShift() {
        setBeadShift((getBeadShift() == Shift.left) ? Shift.right : Shift.left);
    }
    

    // draw
    public void draw(Graphics gr) {
        draw(gr, getBeadColor());
    }
    
    public void erase(Graphics gr) {
        // panel calls repaint : draws if there is another bead underneath
        draw(gr, getWireParent().getAbacusParent().getAbacusBgColor());
    }
    
    public void draw(Graphics gr, Color beadColor) {
        int diameter = getBeadDiameter();
        gr.setColor(beadColor);
        gr.fillOval(getLeftX(), getTopY() + 2, diameter, diameter-4);
    }

    public void shift() {
        Graphics gr = getWireParent().getAbacusParent().getGraphics();
        erase(gr);
        toggleBeadShift();
        if (getBeadShift() == Shift.right)
            setLeftX(getLeftX() + getWireParent().getAbacusParent().beadShiftDistance * getBeadDiameter());
        else
            setLeftX(getLeftX() - getWireParent().getAbacusParent().beadShiftDistance * getBeadDiameter());
        draw(gr, getBeadColor());
    }


    @Override
    public boolean wasClicked(int X, int Y) {
        boolean found = false;

        if (    (X > getLeftX()) &&
                (X < (getLeftX() + getBeadDiameter())) &&
                (Y > getTopY()) &&
                (Y < getTopY() +getBeadDiameter())  ) {

            found = true;
            getWireParent().shift(this);
        }

        return found;
    }

    
}
