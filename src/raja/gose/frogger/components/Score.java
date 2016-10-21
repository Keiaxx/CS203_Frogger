package raja.gose.frogger.components;

/**
 *
 * Handles score tracking and showing strings on the JFrame
 *
 * Copyright (c) 2016
 *
 * @author Adrian Gose & Saad Raja
 *
 */

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JComponent;


public class Score extends JComponent {

    private final String title = "Current Score: ";
    int score = 0;
    boolean loss = false;

    /**
     * Draws the score title and or game over sign
     * @param gfx 
     */
    public void draw(Graphics2D gfx) {

        String display = title + score;

        if (loss) {
            gfx.drawString(display, 450, 20);
            gfx.setFont(new Font("TimesRoman", Font.BOLD, 150));
            gfx.setColor(Color.RED);
            gfx.drawString("GAME OVER", 30, 200);

        } else {

            gfx.drawString(display, 450, 20);

        }

    }

    /**
     * Sets the text to show game over
     */
    public void loss() {
        this.loss = true;
    }

    /**
     * Add to the score
     */
    public void addWin() {
        score++;
    }
    
    @Override
    public String toString(){
        return this.getClass().getName()+"[text="+this.title+ "score= "+this.score+"]";
    }

}
