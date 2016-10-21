package raja.gose.frogger.components;

/**
 *
 * CollisionBox stores any moving objects position which is based on a
 * rectangle. Only one corner is needed as well as the width and height of the
 * object. Two CollisionBoxes can be compared with each other to calculate
 * whether or not the two intersect, therefore a collision has occured.
 *
 * Copyright (c) 2016
 *
 * @author Adrian Gose & Saad Raja
 *
 */

public class CollisionBox {

    private int x1;
    private int y1;
    private int width;
    private int height;

    /**
     * Create a new collision box for a image/drawing/object.
     *
     * @param x1 Top left corner X value
     * @param y1 Top left corner Y value
     * @param width Width of the entire object
     * @param height Height of the entire object
     */
    public CollisionBox(int x1, int y1, int width, int height) {
        this.setCorner(x1, y1);
        this.width = width;
        this.height = height;
    }

    /**
     * Sets the new/current corner of the object, based on the top leftmost
     * point.
     *
     * @param x1 Top left corner X value
     * @param y1 Top left corner Y value
     */
    public void setCorner(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    /**
     * Returns integer of the top left X value.
     *
     * @return
     */
    public int getTopLeftX() {
        return x1;
    }

    /**
     * Returns integer of the bottom right X value.
     *
     * @return
     */
    public int getBottomRightX() {
        return x1 + width;
    }

    /**
     * Returns integer of the top left Y value.
     *
     * @return
     */
    public int getTopLeftY() {
        return y1;
    }

    /**
     * Returns integer of the bottom right Y value.
     *
     * @return
     */
    public int getBottomRightY() {
        return y1 + height;
    }

    /**
     * Checks if two CollisionBoxes have collided/intersected.
     * 
     * @param other Another CollisionBox object
     * @return Boolean of whether the two objects have collided or not.
     */
    public Boolean checkCollision(CollisionBox other) {

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
