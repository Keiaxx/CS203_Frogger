package raja.gose.frogger.components;

/**
 *
 * Houses all components to the game
 *
 * Copyright (c) 2016
 *
 * @author Adrian Gose & Saad Raja
 *
 */

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JComponent;

public class GameField extends JComponent {

    private ArrayList<Car> cars = new ArrayList<>();
    private Frog frog;
    private Score score;
    private Dimension fieldDimension;
    private DIFFICULTY diff;
    private int numCars = 0;

    /**
     * Enumeration values of DIFFICULTY (Number of lanes) of the game
     * Easy to add or remove difficulty levels as options
     * are dynamically generated using this enumeration.
     */
    public enum DIFFICULTY {

        ONE(1), TWO(2), THREE(3), FIVE(5), EIGHT(8);

        private int numCars;

        DIFFICULTY(int numCars) {
            this.numCars = numCars;
        }

        public int numCars() {
            return this.numCars;
        }

        /**
         * Returns a string array of
         * current values of the enum 
         * @return 
         */
        public static String[] valuesAsString() {
            DIFFICULTY[] diff = values();
            
            String[] names = new String[diff.length];

            for (int i = 0; i < diff.length; i++) {
                names[i] = diff[i].name();
            }

            return names;
        }

    }
    
    /**
     * Creates a new GameField with a specified Difficulty
     * 
     * Calculates heights, lanes, and positions based on the difficulty(Number of cars) 
     * 
     * (Theoretically, as many cars can be implemented but dynamic sizes of the cars have not been implemented yet.
     * 
     * @param diff 
     */
    public GameField(DIFFICULTY diff) {

        frog = new Frog();
        score = new Score();

        /** **/
        numCars = diff.numCars();
        fieldDimension = new Dimension(1000, frog.getHeight() * (numCars + 2));

        for (int i = 0; i < numCars; i++) {

            int relativeHeight = frog.getHeight() * (i + 1);
            Car car = new Car(relativeHeight);
            cars.add(car);

        }

        frog.setY(frog.getHeight() * (numCars + 1));

    }

    /**
     * Paints components of each object.
     * This will call the draw() method of each game object to draw
     * image to the JFrame
     * @param g 
     */
    @Override
    public void paintComponent(Graphics g) {

        Graphics2D gfx = (Graphics2D) g;

        /* Draws a nice border around the games field */
        gfx.drawRect(0, 0, fieldDimension.width, fieldDimension.height);

        for (Car car : cars) {
            car.draw(gfx);
        }

        frog.draw(gfx);
        score.draw(gfx);

    }

    /**
     * If the frog reaches the other side, call this.
     */
    public void winEvent() {

        incrementSpeed();

        score.addWin();

        frog.setY(frog.getHeight() * (numCars + 1));

    }

    /**
     * Returns all the cars in the game
     * @return 
     */
    public ArrayList<Car> getCars() {
        return cars;
    }

    /**
     * Returns the Score object
     * @return 
     */
    public Score getScore() {
        return this.score;
    }

    /**
     * Returns a frog object
     * @return 
     */
    public Frog getFrog() {
        return frog;
    }

    /**
     * Increments the speed of all the cars.
     */
    public void incrementSpeed() {
        for (Car c : getCars()) {
            c.increaseSpeed();
        }
    }

    /**
     * Get the dimensions of the playing field.
     * @return 
     */
    public Dimension getFieldDimension() {
        return this.fieldDimension;
    }

}
