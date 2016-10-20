/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raja.gose.frogger.components;

/**
 *
 * @author Administrator
 */
public class CollisionBox {

    private int x1;
    private int y1;
    private int width;
    private int height;

    public CollisionBox(int x1, int y1, int width, int height) {
        this.setCorner(x1, y1);
        this.width = width;
        this.height = height;
    }

    public void setCorner(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    public int getTopLeftX() {
        return x1;
    }

    public int getBottomRightX() {
        return x1 + width;
    }

    public int getTopLeftY() {
        return y1;
    }

    public int getBottomRightY() {
        return y1 + height;
    }

    public Boolean isColliding(CollisionBox other) {

        //               x1r2, y1r2
        // x1, y1  A------D--A----------D
        //         |         |          |
        //         A------D--A----------D  x2r2, y2r2
        //                  x2, y2 
        boolean collision = false;

        if ((this.getTopLeftX() < other.getBottomRightX())
                && (this.getBottomRightX() > other.getTopLeftX())
                && (this.getTopLeftY() < other.getBottomRightY())
                && (this.getBottomRightY() > other.getTopLeftY())) {

            return true;

        }

        return collision;
    }

}
