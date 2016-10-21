package raja.gose.frogger.listeners;

/**
 *
 * Listens to keyboard input
 *
 * Copyright (c) 2016
 *
 * @author Adrian Gose & Saad Raja
 *
 */

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import raja.gose.frogger.GameStarter;
import raja.gose.frogger.components.Frog;

public class InputListener implements KeyListener {

    GameStarter gs;

    public InputListener(GameStarter gs) {
        this.gs = gs;
    }

    /**
     * Called when a keyboard key is pressed.
     * @param e 
     */
    @Override
    public void keyPressed(KeyEvent e) {

        Frog frog = gs.getField().getFrog();

        
        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                gs.togglePause();
                break;

        }

        if (gs.isPaused()) {
            return;
        }

        /**
         * Actions when WASD or ARROW keys are pressed.
         */
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
            case KeyEvent.VK_A:
                frog.moveLeft();
                break;
            case KeyEvent.VK_D:
                frog.moveRight();
                break;
            case KeyEvent.VK_W:
                frog.moveUp();
                break;
            case KeyEvent.VK_S:
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
