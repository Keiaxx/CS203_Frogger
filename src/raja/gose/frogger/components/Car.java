package raja.gose.frogger.components;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Car {

    private double speedFactor = 50;
    private int carX = -200;
    private int carY;
    private BufferedImage carRight = null;
    private BufferedImage carLeft = null;
    private boolean moveRight = true;
    
    private CollisionBox border;

    public Car(int height) {
        
        System.out.println("NEWCAR");
        
        carY = height;
        
        Random rand = new Random();
        moveRight = rand.nextBoolean();
        
        speedFactor = rand.nextInt(4)+1;
        
        try {
            carRight = ImageIO.read(new File("car.png"));
            carLeft = ImageIO.read(new File("carLeft.png"));
        } catch (IOException e) {
        }
        
        int padding = 25;
        border = new CollisionBox(carX+padding, carY+padding, getWidth()-padding, getHeight()-padding);

    }

    public void draw(Graphics2D g) {

        if (moveRight) {
            g.drawImage(carRight, carX, carY, null);
            border.setCorner(carX, carY);
        } else {
            g.drawImage(carLeft, carX, carY, null);
            border.setCorner(carX, carY);
        }
    }

    public void move() {

        if (moveRight) {

            carX += speedFactor;

            if (carX >= 1000) {
                carX = 0 - getWidth();
            }

        } else {

            carX -= speedFactor;

            if (carX <= 0 - this.getWidth()) {
                carX = 1000;
            }

        }

    }

    public void changeDirection() {
        if (moveRight) {
            moveRight = false;
        } else {
            moveRight = true;
        }
    }
    
    public void randomDirection() {
        this.moveRight = new Random().nextBoolean();
    }

    public void increaseSpeed() {
        speedFactor++;
    }

    public int getWidth() {
        return carRight.getWidth();
    }

    public int getHeight() {
        return carRight.getHeight();
    }
    
    public CollisionBox getBorder(){
        return this.border;
    }
}
