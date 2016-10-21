package raja.gose.frogger.components;

/**
 *
 * The frog used for the Frogger game.
 *
 * Copyright (c) 2016
 *
 * @author Adrian Gose & Saad Raja
 *
 */
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Frog {

    /* Position coordinates */
    private int frogX = 450;
    private int frogY;

    /* All necessary images of each direction and state of the frog */
    private BufferedImage dead = null;
    private BufferedImage down = null;
    private BufferedImage left = null;
    private BufferedImage right = null;
    private BufferedImage up = null;
    private BufferedImage currentImage;

    private CollisionBox border;

    private int fieldHeight = 0;

    /**
     * Creates a new Frog. Loads all frog images to memory.
     */
    public Frog() {

        try {

            dead = ImageIO.read(new File("frog-dead.png"));
            down = ImageIO.read(new File("frog-down.png"));
            left = ImageIO.read(new File("frog-left.png"));
            right = ImageIO.read(new File("frog-right.png"));
            up = ImageIO.read(new File("frog-up.png"));

            currentImage = up;
        } catch (IOException e) {
        }

        border = new CollisionBox(frogX, frogY, this.getWidth(), this.getHeight());

    }

    /**
     * Redraws the frog based on its coordinates.
     * @param g Graphics2D from GameField
     */
    public void draw(Graphics2D g) {
        g.drawImage(currentImage, frogX, frogY, null);
        border.setCorner(frogX, frogY);
    }

    /**
     * Check to see if the frog has reached the other side of the road.
     * @return 
     */
    public Boolean hasCrossed() {
        if (frogY >= this.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Moves the frog to the right 50 pixels
     */
    public void moveRight() {
        currentImage = right;
        if (frogX >= 900) {
            return;
        }
        frogX += 50;
    }

    /**
     * Moves the frog left 50 pixels
     */
    public void moveLeft() {
        currentImage = left;
        if (frogX <= 0) {
            return;
        }
        frogX -= 50;
    }

    /**
     * Moves the frog up one lane
     */
    public void moveUp() {
        currentImage = up;
        frogY -= this.getHeight();
    }

    /**
     * Moves the frog down one lane
     */
    public void moveDown() {
        currentImage = down;
        if (frogY >= fieldHeight) {
            return;
        }
        frogY += this.getHeight();
    }

    /**
     * Return current X position of the frog
     * @return 
     */
    public int getX() {
        return this.frogX;
    }

    /**
     * Return current Y position of the frog
     * @return 
     */
    public int getY() {
        return this.frogY;
    }

    /**
     * Get the width of the frog image
     * @return 
     */
    public int getWidth() {
        return up.getWidth();
    }

    /**
     * Get the height of the frog image
     * @return 
     */
    public int getHeight() {
        return up.getHeight();
    }

    /**
     * Get the CollisionBox of the frog
     * @return 
     */
    public CollisionBox getBorder() {
        return this.border;
    }

    /**
     * Sets the Y value of the frog.
     * Used upon Frog creation to move
     * to the bottom of the playing field
     * @param ypos 
     */
    public void setY(int ypos) {
        this.frogY = ypos;
        this.fieldHeight = ypos;
    }

    /**
     * Called upon death event.
     * Changes the image of the frog to a
     * splat
     */
    public void death() {
        currentImage = dead;
    }

    @Override
    public String toString() {
        return this.getClass().getName() + "[ x= " + this.frogX + " y= " + this.frogY + " fieldHeight= " + this.fieldHeight + "]";
    }
}
