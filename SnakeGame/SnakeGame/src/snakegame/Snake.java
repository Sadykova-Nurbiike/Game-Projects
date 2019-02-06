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
import java.awt.Graphics;
import java.util.ArrayList;

public class Snake {

    ArrayList<Entity> snake;
    private final Entity head;
    //Game Stuff

    public Snake(int WIDTH_PANEL, int HEIGHT_PANEL) {
        //snake
        snake = new ArrayList<>();
        head = new Entity(WIDTH_PANEL / 2, HEIGHT_PANEL / 2, Color.green);
        //The snake starts off from the center of the lavel
        snake.add(head);
        for (int i = 1; i < 2; i++) {
            Entity e = new Entity(head.getX() + (i * Entity.SIZE), head.getY(), Color.green);
            snake.add(e);
        }

    }

    //Getters and setters
    public Entity getHead() {
        return this.head;
    }

    public ArrayList<Entity> getSnake() {
        return this.snake;
    }

    public int getSize() {
        return snake.size();
    }

    public Entity getE(int i) {
        return snake.get(i);
    }

    public void addE(Entity e) {
        snake.add(e);
    }

    //Draw
    public void draw(Graphics g) {
        snake.forEach((e) -> {
            e.draw(g);
        });
    }

}
