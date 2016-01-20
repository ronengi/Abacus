package childrenAbacus;

import java.util.ArrayList;

import childrenAbacus.Bead.Shift;

import java.awt.Color;
import java.awt.Graphics;

public class Wire implements Drawable {

    private ArrayList<Bead> beads;
    private Abacus abacusParent;
    
    // constructor
    public Wire(int numBeads, int beadDiameter, Abacus abacusParent) {
        this.abacusParent = abacusParent;
        Color beadColor = colorByWireIndex(this.abacusParent.getNumWires());
        beads = new ArrayList<Bead>();
        for (int beadIndex = 0; beadIndex < numBeads; ++beadIndex) {
            Bead newBead = new Bead(beadDiameter, beadColor, this);
            beads.add(newBead);
        }
    }

    public ArrayList<Bead> getBeads() {
        return beads;
    }

    public Abacus getAbacusParent() {
        return abacusParent;
    }
    
    public int getWireIndex() {
        int wireIndex = getAbacusParent().getWires().indexOf(this);
        wireIndex = (wireIndex == -1) ? getAbacusParent().getWires().size() : wireIndex;
        return wireIndex;
    }

    public int getNumBeads() {
        return getBeads().size();
    }
    
    @Override
    public void draw(Graphics gr) {
        for (Bead nextBead : getBeads()) {
            nextBead.draw(gr);
        }
    }

    private Color colorByWireIndex(int wireIndex) {
        Color wireColor;
        int colorGroup = wireIndex % 10;
        switch (colorGroup) {
        case 0:
            wireColor = Color.red;
            break;
        case 1:
            wireColor = Color.blue;
            break;
        case 2:
            wireColor = Color.green;
            break;
        case 3:
            wireColor = Color.magenta;
            break;
        case 4:
            wireColor = Color.pink;
            break;
        case 5:
            wireColor = Color.yellow;
            break;
        case 6:
            wireColor = Color.orange;
            break;
        case 7:
            wireColor = Color.red;
            break;
        case 8:
            wireColor = Color.blue;
            break;
        case 9:
            wireColor = Color.green;
            break;
        default:
            wireColor = Color.black;
            break;
        }
        return wireColor;
    }


    @Override
    public boolean wasClicked(int X, int Y) {
        boolean found = false;
        int beadIndex = 0;
        while (!found && beadIndex < getBeads().size()) {
            found = getBeads().get(beadIndex).wasClicked(X, Y);
            ++beadIndex;
        }
        return found;
    }

    public void shift(Bead thisBead) {
        for (Bead nextBead : getBeads()) {
            if (    (nextBead.getBeadIndex() >= thisBead.getBeadIndex() && nextBead.getBeadShift() == Shift.left) ||
                    (nextBead.getBeadIndex() <= thisBead.getBeadIndex() && nextBead.getBeadShift() != Shift.left)   )
                nextBead.shift();
        }
    }
    
}
