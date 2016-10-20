/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raja.gose.frogger.components;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.JComponent;

/**
 *
 * @author Administrator
 */
public class ScoreLabel extends JComponent {

    private final String title = "Current Score: ";
    int score = 0;

    boolean loss = false;

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

    public void loss() {
        this.loss = true;
    }

    public void addWin() {

        score++;

    }

}
