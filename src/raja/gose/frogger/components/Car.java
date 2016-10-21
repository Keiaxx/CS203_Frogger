package raja.gose.frogger.components;

/**
 *
 * A car used for the Frogger game. Each car has its own unique speed, position,
 * and direction.
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
import java.util.Random;
import javax.imageio.ImageIO;

public class Car {

    /* Instance Variables */
    private int carX = -200;
    private int carY;
    
    private BufferedImage carRight = null;
    private BufferedImage carLeft = null;
    
    private boolean moveRight = new Random().nextBoolean();
    private double speedFactor = new Random().nextInt(3) + 1;

    private CollisionBox border;

    /**
     * Creates a new car object at a specified height/lane
     * 
     * The car will spawn with a random direction, and speed.
     * 
     * @param height 
     */
    public Car(int height) {

        carY = height;

        /* Spawn the car with a random speed */
        speedFactor = new Random().nextInt(5) + 1;

        try {
            carRight = ImageIO.read(new File("car.png"));
            carLeft = ImageIO.read(new File("carLeft.png"));
        } catch (IOException e) {
        }

        /* Create a new CollisionBox for this Car */
        int padding = 25;
        border = new CollisionBox(carX - padding, carY - padding, getWidth() - padding, getHeight() - padding);

    }

    /**
     * Draws the car on the current JFrame.
     * @param g Graphics2D from JComponent object
     */
    public void draw(Graphics2D g) {

        if (moveRight) {
            g.drawImage(carRight, carX, carY, null);
            border.setCorner(carX, carY);
        } else {
            g.drawImage(carLeft, carX, carY, null);
            border.setCorner(carX, carY);
        }
        
    }

    /**
     * Upon every timer call this is invoked in order
     * to advance the car on the playing field.
     */
    public void move() {

        if (moveRight) {

            carX += speedFactor;

            /* If car is off of right screen wrap around */
            if (carX >= 1000) {
                carX = 0 - getWidth();
            }

        } else {

            carX -= speedFactor;
            
            /* If car is off of the left screen wrap around */
            if (carX <= 0 - this.getWidth()) {
                carX = 1000;
            }

        }

    }

    /**
     * Changes the direction of the car
     * based on its previous moving direction.
     */
    public void changeDirection() {
        if (moveRight) {
            moveRight = false;
        } else {
            moveRight = true;
        }
    }

    /**
     * Chooses a random direction.
     * Only called when a new car is created
     */
    public void randomDirection() {
        this.moveRight = new Random().nextBoolean();
    }

    /**
     * Increase the speed of the moving car.
     */
    public void increaseSpeed() {
        speedFactor++;
    }

    /**
     * Get the width of the image of the car.
     * @return 
     */
    public int getWidth() {
        return carRight.getWidth();
    }

    /**
     * Get the height of the image of the car.
     * @return 
     */
    public int getHeight() {
        return carRight.getHeight();
    }

    /**
     * Returns a CollisionBox object
     * of the current instance of the car
     * @return 
     */
    public CollisionBox getBorder() {
        return this.border;
    }

    /**
     * Prints a human readable format of the cars position
     * @return 
     */
    @Override
    public String toString() {
        return this.getClass().getName() + "[ x= " + this.carX + " y= " + this.carY + " movingRight= " + this.moveRight + "]";
    }
}
