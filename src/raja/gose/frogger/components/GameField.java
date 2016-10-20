/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raja.gose.frogger.components;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

/**
 *
 * @author Administrator
 */
public class GameField extends JComponent {
    
    private ArrayList<Car> cars = new ArrayList<>();
    private Frog frog;
    private ScoreLabel score;
    
    private Dimension fieldDimension;
    private Difficulty diff;
    
    private int numCars = 0;
    
    public enum Difficulty {
        
        EASY, MEDIUM, HARD
    }
    
    public GameField(Difficulty diff) {    
        frog = new Frog();
        score = new ScoreLabel();
        
        if (diff.equals(Difficulty.EASY)) {
            
            fieldDimension = new Dimension(1000, frog.getHeight() * 3);
            numCars = 1;
            
        } else if (diff.equals(Difficulty.MEDIUM)) {
            
            fieldDimension = new Dimension(1000, frog.getHeight() * 4);
            numCars = 2;
        } else if (diff.equals(Difficulty.HARD)) {
            
            fieldDimension = new Dimension(1000, frog.getHeight() * 5);
            numCars = 3;
        }
        
        for (int i = 0; i < numCars; i++) {
            
            int relativeHeight = frog.getHeight() * (i + 1);
            Car car = new Car(relativeHeight);
            
            System.out.println("Makeing new car" + i + "HITE " + relativeHeight);
            
            cars.add(car);
        }
        
        frog.setY(frog.getHeight()*(numCars+1));
        
        
    }
    
    @Override
    public void paintComponent(Graphics g) {
        
        Graphics2D gfx = (Graphics2D) g;
        
        gfx.drawRect(0, 0, fieldDimension.width, fieldDimension.height);
        
        for (Car car : cars) {
            car.draw(gfx);
        }
        
        frog.draw(gfx);
        score.draw(gfx);
        
    }
    
    public void winEvent(){
        
        incrementSpeed();
        
        score.addWin();
        
        frog.setY(frog.getHeight()*(numCars+1));
        
    }
  
    public ArrayList<Car> getCars() {
        return cars;
    }
    
    public ScoreLabel getScore(){
        return this.score;
    }
    
    public void incrementSpeed() {
        for (Car c : getCars()) {
            c.increaseSpeed();
        }
    }
    
    public Frog getFrog() {
        return frog;
    }
    
    public Dimension getFieldDimension(){
        return this.fieldDimension;
    }
    
}
