/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package raja.gose.frogger.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import raja.gose.frogger.GameStarter;
import raja.gose.frogger.components.Frog;

/**
 *
 * @author Administrator
 */
public class InputListener implements KeyListener {

    GameStarter gs;

    public InputListener(GameStarter gs) {
        this.gs = gs;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        Frog frog = gs.getField().getFrog();

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                gs.togglePause();
                break;
            case KeyEvent.VK_SHIFT:
                gs.getField().incrementSpeed();

        }

        if (gs.isPaused()) {
            return;
        }

        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                frog.moveLeft();
                break;
            case KeyEvent.VK_RIGHT:
                frog.moveRight();
                break;
            case KeyEvent.VK_UP:
                frog.moveUp();
                break;
            case KeyEvent.VK_DOWN:
                frog.moveDown();
                break;

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
