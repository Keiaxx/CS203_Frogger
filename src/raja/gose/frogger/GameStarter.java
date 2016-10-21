package raja.gose.frogger;

/**
 *
 * Houses the games main features. - Game loop - Game panels
 *
 * Copyright (c) 2016
 *
 * @author Adrian Gose & Saad Raja
 *
 */

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import raja.gose.frogger.listeners.InputListener;
import raja.gose.frogger.components.Frog;
import raja.gose.frogger.components.Car;
import raja.gose.frogger.components.GameField;
import raja.gose.frogger.components.GameField.DIFFICULTY;

public class GameStarter {

    /** Instance variables **/
    private GameField gameField;

    private JFrame frame;
    private JPanel buttonPanel;
    private JLabel instructions;
    private JButton restartButton;
    
    private DIFFICULTY diff;

    private Timer loopTimer;
    private boolean debug = true;
    private boolean paused = false;

    
    /**
     * Starts a new frogger game. Sets up all frames and the game field.
     */
    public void initializeGame() {

        /* Prompt for difficulty */
        String[] difficulties = DIFFICULTY.valuesAsString();
        String choice = (String) JOptionPane.showInputDialog(null, "Choose a number of cars", "Difficulty", JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[0]);
        diff = DIFFICULTY.valueOf(choice);

        /* Create frames and button panels */
        frame = new JFrame();
        buttonPanel = new JPanel();

        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(buttonPress());

        instructions = new JLabel();
        instructions.setText("Press ENTER to toggle pausing the game. Use W,A,S,D or ARROW KEYS to move.");

        buttonPanel.setVisible(true);
        buttonPanel.add(restartButton);
        buttonPanel.add(instructions);

        gameField = new GameField(diff);

        frame.add(gameField, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(1000, 50 + gameField.getFieldDimension().height));
        frame.pack();
        frame.setVisible(true);

        /* Addes keyboard listener */
        gameField.addKeyListener(new InputListener(this));
        gameField.setFocusable(true);
        gameField.requestFocus();

        /* Starts the main game loop */
        loopTimer = new Timer(20, (e) -> {
            this.gameLoop();
        });
        loopTimer.start();
    }

    /**
     * Main game loop.
     * 
     * Processing of input, calculations, and display occurs here.
     */
    public void gameLoop() {

        if (debug) {
            this.printDebug();
        }

        /* Check if game has been paused */
        if (paused) {
            return;
        }

        Frog frog = gameField.getFrog();

        /* Checks to see if the frog has reached the other side of the road */
        if (frog.getY() < 0) {
            gameField.winEvent();
            
            for (Car car : this.getField().getCars()) {
                
                car.randomDirection();
                
            }
        }

        /* Checks to see if the frog has been hit or has run into a car */
        for (Car car : this.getField().getCars()) {
            
            if (car.getBorder().checkCollision(frog.getBorder())) {
                
                this.loopTimer.stop();
                frog.death();
                this.getField().getScore().loss();
                
            }else{
                
            car.move();
            
            }
        }

        /* Redraw all objects */
        this.getField().repaint();

    }

    /**
     * Reset the game to a new state 
     */
    public void resetGame() {
        this.paused = false;
        this.initializeGame();
    }

    /**
     * Toggles game pause mode 
     */
    public void togglePause() {
        if (this.paused) {
            this.paused = false;
        } else {
            this.paused = true;
        }
    }

    /** 
     * Checks to see if the game has
     * been paused
     * @return boolean
     */
    public Boolean isPaused() {
        return this.paused;
    }

    /**
     * Get the main game field.
     * @return 
     */
    public GameField getField() {
        return this.gameField;
    }

    /**
     * Handles JButton press events.
     * @return 
     */
    private ActionListener buttonPress() {
        ActionListener action = (ActionEvent e) -> {

            if (e.getSource() == restartButton) {
                this.frame.dispose();
                this.resetGame();
            }
        };
        return action;
    }

    /**
     * Debug.
     * Shows positions of objects
     */
    private void printDebug() {
        System.out.println(this.getField().getFrog().toString());
        for (Car car : this.getField().getCars()) {
            System.out.println(car.toString());
        }

    }
}
