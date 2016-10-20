/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raja.gose.frogger.components;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author Administrator
 */
public class Frog {

    private int frogX = 450;
    private int frogY;
    
    private BufferedImage dead = null;
    private BufferedImage down = null;
    private BufferedImage left = null;
    private BufferedImage right = null;
    private BufferedImage up = null;
    
    private BufferedImage currentImage;
    
    private CollisionBox border;
    
    private int fieldHeight = 0;
    
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

    public void draw(Graphics2D g) {
        g.drawImage(currentImage, frogX, frogY, null);
        border.setCorner(frogX, frogY);
    }

    public Boolean hasCrossed() {
        if (frogY >= this.getHeight()) {
            return true;
        } else {
            return false;
        }
    }

    public void moveRight() {
        currentImage = right;
        if(frogX >= 900)
            return;
        frogX += 50;
    }

    public void moveLeft() {
        currentImage = left;
        if(frogX <= 0)
            return;
        frogX -= 50;
    }

    public void moveUp() {
        currentImage = up;
        if(frogY <= 0)
            return;
        frogY -= 100;
    }

    public void moveDown() {
        currentImage = down;
        if(frogY >= fieldHeight)
            return;
        frogY += 100;
    }

    public int getX() {
        return this.frogX;
    }

    public int getY() {
        return this.frogY;
    }

    public void setY(int ypos) {
        this.frogY = ypos;
        this.fieldHeight = ypos;
    }
    
    public void death(){
        currentImage = dead;
    }

    public int getWidth() {
        return up.getWidth();
    }

    public int getHeight() {
        return up.getHeight();
    }
    
    public CollisionBox getBorder(){
        return this.border;
    }
}
