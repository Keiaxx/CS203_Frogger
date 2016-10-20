package raja.gose.frogger;

import java.awt.BorderLayout;
import raja.gose.frogger.components.Frog;
import raja.gose.frogger.components.ScoreLabel;
import raja.gose.frogger.listeners.InputListener;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import raja.gose.frogger.components.Car;
import raja.gose.frogger.components.GameField;
import raja.gose.frogger.components.GameField.Difficulty;

public class GameStarter {

    private ScoreLabel scoreLabel;
    private GameField gameField;
    private JFrame frame;
    private JPanel buttonPanel;
    private JButton pauseButton;
    private JButton restartButton;

    private Difficulty diff = Difficulty.EASY;

    private boolean paused = false;

    private Timer loopTimer;

    public void initializeGame() {

        paused = false;

        String[] difficulties = {"Easy", "Medium", "Hard"};

        String choice = (String) JOptionPane.showInputDialog(null, "Choose a difficulty", "Difficulty", JOptionPane.QUESTION_MESSAGE, null, difficulties, difficulties[0]);

        if (choice.equalsIgnoreCase("easy")) {
            diff = Difficulty.EASY;
        } else if (choice.equalsIgnoreCase("medium")) {
            diff = Difficulty.MEDIUM;
        } else if (choice.equalsIgnoreCase("hard")) {
            diff = Difficulty.HARD;
        }

        frame = new JFrame();
        buttonPanel = new JPanel();

        restartButton = new JButton("Restart Game");
        restartButton.addActionListener(buttonPress());

        pauseButton = new JButton("Pause Game");

        pauseButton.addActionListener(buttonPress());

        buttonPanel.setVisible(true);
        buttonPanel.add(restartButton);
        buttonPanel.add(pauseButton);

        gameField = new GameField(diff);

        frame.add(gameField, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().setPreferredSize(new Dimension(1000, 50 + gameField.getFieldDimension().height));
        frame.pack();
        frame.setVisible(true);

        gameField.addKeyListener(new InputListener(this));
        gameField.setFocusable(true);
        gameField.requestFocus();

        loopTimer = new Timer(20, (e) -> {
            this.gameLoop();
        });
        loopTimer.start();
    }

    public void gameLoop() {

        if (paused) {
            return;
        }

        Frog frog = gameField.getFrog();

        //WIN EVENTS
        if (frog.getY() < 0) {

            gameField.winEvent();
            for (Car car : gameField.getCars()) {
                car.randomDirection();
            }

        }
        //============

        for (Car car : gameField.getCars()) {
            
            if (car.getBorder().isColliding(frog.getBorder())) {
                loopTimer.stop();
                frog.death();
                loseEvent();
                
                
            }

            car.move();

        }

        gameField.repaint();

    }

    public void loseEvent() {
        gameField.getScore().loss();
    }

    public void togglePause() {
        paused = paused ? false : true;
    }

    public Boolean isPaused() {
        return this.paused;
    }

    public GameField getField() {
        return gameField;
    }

    private ActionListener buttonPress() {
        ActionListener action = (ActionEvent e) -> {

            if (e.getSource() == restartButton) {
                frame.dispose();
                this.initializeGame();
            } else if (e.getSource() == pauseButton) {
                togglePause();
            }

        };
        return action;
    }

}
