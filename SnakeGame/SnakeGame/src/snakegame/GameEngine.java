// Sadykova Nurbiike
//
// M1YVEQ
//
// Third assignment
//
// 2019/01/11 20:57:34
//
// This solution was submitted and prepared by Sadykova Nurbiike, M1YVEQ for the
// Third assignment assignment of the Practical software engineering I. course.
//
// I declare that this solution is my own work.
//
// I have not copied or used third party solutions.
//
// I have not passed my solution to my classmates, neither  made it public.
//
// Students’ regulation of Eötvös Loránd University (ELTE Regulations
// Vol. II. 74/C. § ) states that as long as a student presents another
// student’s work - or at least the significant part of it - as his/her own
// performance, it will count as a disciplinary fault. The most serious
// consequence of a disciplinary fault can be dismissal of the student from
// the University.

package snakegame;



import java.awt.Color;

import java.awt.Dimension;

import java.awt.Graphics;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;

import java.sql.SQLException;

import java.util.ArrayList;

import java.util.logging.Level;

import java.util.logging.Logger;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

import javax.swing.Timer;



public class GameEngine extends JPanel implements KeyListener {



    private final Color background = Color.WHITE;



    //label

    private final JLabel label;

    private final Timer newFrameTimer;

    private long startTime;



    public static final int WIDTHM = 500;

    public static final int HEIGHTM = 500;



    //Game Stuff

    private Entity apple;

    private Snake snake;

    private ArrayList<Entity> rocks;

    private int score;

    private int level;

    private boolean gameOver;

    private String playersName;



    //movement

    private int dx, dy;



    //key input

    private boolean up, down, right, left;



    //Database

    private BestPlayers bestPlayers;



    public GameEngine() {

        super();



        setPreferredSize(new Dimension(WIDTHM, HEIGHTM));

        setFocusable(true);

        requestFocus();

        addKeyListener(this);



        //Label

        label = new JLabel(" ");

        label.setHorizontalAlignment(JLabel.RIGHT);



        setBackground(background);

        restart();



        newFrameTimer = new Timer(1000 / 15, new NewFrameListener());

        newFrameTimer.start();

    }



    /**

     * @return elapsed time from the start of the game level

     */

    public long elapsedTime() {

        return System.currentTimeMillis() - startTime;

    }



    /**

     * restarts the game, game will start from level 1

     */

    public void restart() {

        apple = new Entity(0, 0, Color.red);

        snake = new Snake(WIDTHM, HEIGHTM);



        //rocks

        rocks = new ArrayList<>();



        //apple

        setApple();



        //logic

        score = 0;

        gameOver = false;

        level = 1;

        //The snake starts off in a random direction

        int randomDirection = 1 + (int) (Math.random() * ((4 - 1) + 1));

        switch (randomDirection) {

            case 1:

                dy = (-Entity.SIZE);

                dx = 0;

                break;

            case 2:

                dy = (Entity.SIZE);

                dx = 0;

                break;

            case 3:

                dy = 0;

                dx = (-Entity.SIZE);

                break;

            case 4:

                dy = 0;

                dx = Entity.SIZE;

                break;

        }

        for (int i = snake.getSize() - 1; i > 0; i--) {

            snake.getE(i).setPosition(snake.getE(i - 1).getX(),

                    snake.getE(i - 1).getY());

        }

        snake.getHead().move(dx, dy);



        startTime = System.currentTimeMillis();

    }



    /**

     * make reappear apple in a random position

     */

    public void setApple() {

        int x, y;

        Boolean isGoodPosition = true;

        //checks if position of the apple isn't same as rocks or snake's

        do {

            x = (int) ((Math.random() * (WIDTHM - 10 - Entity.SIZE)) + 10);

            y = (int) ((Math.random() * (HEIGHTM - Entity.SIZE)) + 10);

            x = x - (x % Entity.SIZE);

            y = y - (y % Entity.SIZE);

            for (Entity e : rocks) {

                if (e.getX() == x && e.getY() == y) {

                    isGoodPosition = false;

                }

            }

            for (Entity e : snake.getSnake()) {

                if (e.getX() == x && e.getY() == y) {

                    isGoodPosition = false;

                }

            }

        } while (!isGoodPosition);

        if (isGoodPosition) {

            apple.setPosition(x, y);

        }

    }



    //Algorithm for levels

    /**

     * removes all elements of the ArrayList rocks, then according to the level

     * adds to the rocks new elements and set their position

     *

     * @param option

     */

    public void setRocks(int option) {

        rocks.clear();

        switch (option) {

            case 4:

                for (int i = 0; i < 50; i++) {

                    Entity e1 = new Entity(i * 10, 0, Color.ORANGE);

                    Entity e2 = new Entity(i * 10, 490, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                break;

            case 5:

                for (int i = 0; i < 50; i++) {

                    Entity e1 = new Entity(i * 10, 0, Color.ORANGE);

                    Entity e2 = new Entity(i * 10, 490, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                for (int i = 1; i < 49; i++) {



                    Entity e1 = new Entity(0, i * 10, Color.ORANGE);

                    Entity e2 = new Entity(490, i * 10, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                break;

            case 3:

                for (int i = 1; i < 36; i++) {



                    Entity e1 = new Entity(75 + i * 10, 100, Color.ORANGE);

                    Entity e2 = new Entity(75 + i * 10, 400, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                break;

            case 2:

                for (int i = 1; i < 16; i++) {

                    Entity e1 = new Entity(0, i * 10, Color.ORANGE);

                    Entity e2 = new Entity(0, 500 - i * 10, Color.ORANGE);

                    Entity e3 = new Entity(490, i * 10, Color.ORANGE);

                    Entity e4 = new Entity(490, 500 - i * 10, Color.ORANGE);

                    Entity e5 = new Entity(i * 10 - 10, 0, Color.ORANGE);

                    Entity e6 = new Entity(500 - i * 10, 0, Color.ORANGE);

                    Entity e7 = new Entity(i * 10 - 10, 490, Color.ORANGE);

                    Entity e8 = new Entity(500 - i * 10, 490, Color.ORANGE);



                    rocks.add(e1);

                    rocks.add(e2);

                    rocks.add(e3);

                    rocks.add(e4);

                    rocks.add(e5);

                    rocks.add(e6);

                    rocks.add(e7);

                    rocks.add(e8);

                }

                break;

            case 6:

                for (int i = 1; i < 16; i++) {

                    Entity e1 = new Entity(0, i * 10, Color.ORANGE);

                    Entity e2 = new Entity(0, 500 - i * 10, Color.ORANGE);

                    Entity e3 = new Entity(490, i * 10, Color.ORANGE);

                    Entity e4 = new Entity(490, 500 - i * 10, Color.ORANGE);

                    Entity e5 = new Entity(i * 10 - 10, 0, Color.ORANGE);

                    Entity e6 = new Entity(500 - i * 10, 0, Color.ORANGE);

                    Entity e7 = new Entity(i * 10 - 10, 490, Color.ORANGE);

                    Entity e8 = new Entity(500 - i * 10, 490, Color.ORANGE);



                    rocks.add(e1);

                    rocks.add(e2);

                    rocks.add(e3);

                    rocks.add(e4);

                    rocks.add(e5);

                    rocks.add(e6);

                    rocks.add(e7);

                    rocks.add(e8);

                }

                for (int i = 1; i < 16; i++) {



                    Entity e1 = new Entity(180 + i * 10, 100, Color.ORANGE);

                    Entity e2 = new Entity(180 + i * 10, 400, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                break;

            case 7:

                for (int i = 0; i < 50; i++) {

                    Entity e1 = new Entity(i * 10, 0, Color.ORANGE);

                    Entity e2 = new Entity(i * 10, 490, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                for (int i = 1; i < 16; i++) {

                    Entity e1 = new Entity(i * 10 - 10, 160, Color.ORANGE);

                    Entity e2 = new Entity(i * 10 - 10, 340, Color.ORANGE);

                    Entity e3 = new Entity(500 - i * 10, 160, Color.ORANGE);

                    Entity e4 = new Entity(500 - i * 10, 340, Color.ORANGE);



                    rocks.add(e1);

                    rocks.add(e2);

                    rocks.add(e3);

                    rocks.add(e4);

                }

                break;

            case 8:

                for (int i = 0; i < 50; i++) {

                    Entity e1 = new Entity(i * 10, 0, Color.ORANGE);

                    Entity e2 = new Entity(i * 10, 490, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                for (int i = 1; i < 16; i++) {

                    Entity e1 = new Entity(160, i * 10, Color.ORANGE);

                    Entity e2 = new Entity(340, i * 10, Color.ORANGE);

                    Entity e3 = new Entity(160, 500 - i * 10, Color.ORANGE);

                    Entity e4 = new Entity(340, 500 - i * 10, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                    rocks.add(e3);

                    rocks.add(e4);

                }

                break;

            case 9:

                for (int i = 0; i < 50; i++) {

                    Entity e1 = new Entity(i * 10, 0, Color.ORANGE);

                    Entity e2 = new Entity(i * 10, 490, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                for (int i = 1; i < 16; i++) {

                    Entity e1 = new Entity(i * 10 - 10, 160, Color.ORANGE);

                    Entity e2 = new Entity(i * 10 - 10, 340, Color.ORANGE);

                    Entity e3 = new Entity(500 - i * 10, 160, Color.ORANGE);

                    Entity e4 = new Entity(500 - i * 10, 340, Color.ORANGE);

                    Entity e5 = new Entity(240, i * 10, Color.ORANGE);

                    Entity e6 = new Entity(240, 500 - i * 10, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                    rocks.add(e3);

                    rocks.add(e4);

                    rocks.add(e5);

                    rocks.add(e6);

                }

                break;

            case 10:

                for (int i = 0; i < 50; i++) {

                    Entity e1 = new Entity(i * 10, 0, Color.ORANGE);

                    Entity e2 = new Entity(i * 10, 490, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                for (int i = 1; i < 49; i++) {



                    Entity e1 = new Entity(0, i * 10, Color.ORANGE);

                    Entity e2 = new Entity(490, i * 10, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                }

                for (int i = 1; i < 16; i++) {

                    Entity e1 = new Entity(120, 80 + i * 10, Color.ORANGE);

                    Entity e2 = new Entity(120, 300 + i * 10, Color.ORANGE);

                    Entity e3 = new Entity(370, 300 + i * 10, Color.ORANGE);

                    Entity e4 = new Entity(370, 80 + i * 10, Color.ORANGE);

                    rocks.add(e1);

                    rocks.add(e2);

                    rocks.add(e3);

                    rocks.add(e4);

                }

                break;

            default:

                break;

        }



    }



    /**

     * updates the game panel

     *

     * @throws SQLException

     */

    private void update() throws SQLException {

        if (gameOver) {

            //Asking player for his name

            Boolean typed = false;

            do {

                playersName = JOptionPane.showInputDialog(null, "Your score: " + score + "\nPlease, enter your name: ", "Game over!", JOptionPane.PLAIN_MESSAGE);

                if (playersName != null) {

                    if (playersName.length() > 0) {

                        typed = true;

                    }

                }

            } while (!typed);



            //Inserting players data into database

            bestPlayers.putHighScore(playersName, score);



            //Stopping the game

            newFrameTimer.stop();

            System.exit(0);
            
            return;

        }

        //Making levels,there are 10 levels in this game

        if (level != 1) {

            setRocks(level);

        }



        //Movement of snake

        if (up && dy == 0) {

            dy = (-Entity.SIZE);

            dx = 0;

        }

        if (down && dy == 0) {

            dy = (Entity.SIZE);

            dx = 0;

        }

        if (left && dx == 0) {

            dy = 0;

            dx = (-Entity.SIZE);

        }

        if (right && dx == 0 && dy != 0) {

            dy = 0;

            dx = (Entity.SIZE);

        }



        if (dx != 0 || dy != 0) {

            for (int i = snake.getSize() - 1; i > 0; i--) {

                snake.getE(i).setPosition(snake.getE(i - 1).getX(),

                        snake.getE(i - 1).getY());

            }

            snake.getHead().move(dx, dy);

        }



        //Checking collision of head with snake,rocks and apple

        for (Entity e : snake.getSnake()) {

            if (e.collides(snake.getHead())) {

                gameOver = true;

                break;

            }

        }



        for (Entity e : rocks) {

            if (e.collides(snake.getHead())) {

                gameOver = true;

                break;

            }

        }



        if (apple.collides(snake.getHead())) {

            score++;

            setApple();



            Entity e = new Entity(-100, -100, Color.green);

            snake.addE(e);

            //new level starts

            if (score % 10 == 0) {

                level++;

                startTime = System.currentTimeMillis();



                /*new born snake*/

                snake.getSnake().clear();

                snake.getHead().setPosition(WIDTHM / 2, HEIGHTM / 2);

                snake.addE(snake.getHead());

                for (int i = 1; i < 2; i++) {

                    Entity rattler = new Entity(snake.getHead().getX() + (i * Entity.SIZE), snake.getHead().getY(), Color.green);

                    snake.addE(rattler);

                }

                /*new born snake*/



                if (level > 10) {

                    level = 10;

                }



            }

        }



        //movement of snake when it goes out of GamePanel

        if (snake.getHead().getX() < 0) {

            snake.getHead().setX(WIDTHM);

        }

        if (snake.getHead().getY() < 0) {

            snake.getHead().setY(HEIGHTM);

        }

        if (snake.getHead().getX() > WIDTHM) {

            snake.getHead().setX(0);

        }

        if (snake.getHead().getY() > HEIGHTM) {

            snake.getHead().setY(0);

        }



    }



    //Label

    public JLabel getLabel() {

        return label;

    }



    @Override

    public void keyTyped(KeyEvent e) {



    }



    /**

     * if one of the WASD keys is pressed, then boolean variables of direction

     * will be true

     *

     * @param e

     */

    @Override

    public void keyPressed(KeyEvent e) {

        int k = e.getKeyCode();



        if (k == KeyEvent.VK_UP) {

            up = true;

        }

        if (k == KeyEvent.VK_DOWN) {

            down = true;

        }

        if (k == KeyEvent.VK_LEFT) {

            left = true;

        }

        if (k == KeyEvent.VK_RIGHT) {

            right = true;

        }


    }



    /**

     * if pressed one of the WASD keys is released, then boolean variables of

     * direction will be false

     *

     * @param e

     */

    @Override

    public void keyReleased(KeyEvent e) {

        int k = e.getKeyCode();



        if (k == KeyEvent.VK_UP) {

            up = false;

        }

        if (k == KeyEvent.VK_DOWN) {

            down = false;

        }

        if (k == KeyEvent.VK_LEFT) {

            left = false;

        }

        if (k == KeyEvent.VK_RIGHT) {

            right = false;

        }

    }



    /**

     * Paints components

     *

     * @param grphcs

     */

    @Override

    protected void paintComponent(Graphics grphcs) {

        super.paintComponent(grphcs);

        setBackground(background);

        apple.draw(grphcs);

        snake.draw(grphcs);

        rocks.forEach((e) -> {

            e.draw(grphcs);

        });

    }



    /**

     * updates the content of the game panel

     */

    class NewFrameListener implements ActionListener {



        @Override

        public void actionPerformed(ActionEvent ae) {

            label.setText("Score : " + score + "    Level : " + level + "   Time: " + elapsedTime() / 1000 + " sec");

            try {

                update();

            } catch (SQLException ex) {

                Logger.getLogger(GameEngine.class.getName()).log(Level.SEVERE, null, ex);

            }

            repaint();

        }



    }



    //Database

    public void setDatabase(BestPlayers bp) {

        this.bestPlayers = bp;

    }



}

