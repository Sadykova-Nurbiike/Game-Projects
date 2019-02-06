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
import java.awt.Rectangle;

public class Entity {

    /**
     * The coordinates of the top left corner of the entity
     */
    protected int x, y;
    protected final static int SIZE = 10;
    protected Color color;

    public Entity(int x, int y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    public void draw(Graphics g) {
        g.setColor(color);
        g.drawRect(x, y, SIZE, SIZE);
        g.fillRect(x + 1, y + 1, SIZE - 2, SIZE - 2);
    }

    public Rectangle getBound() {
        return new Rectangle(x, y, SIZE, SIZE);
    }

    /**
     * Returns true if this entity collides with the other entity
     *
     * @param other
     * @return
     */
    public boolean collides(Entity other) {
        if (other == this) {
            return false;
        }
        return getBound().intersects(other.getBound());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    /**
     * sets the coordinates of the top left corner of the entity
     *
     * @param x
     * @param y
     */
    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * moves the entity with the given parameters accordingly
     *
     * @param dx
     * @param dy
     */
    public void move(int dx, int dy) {
        x += dx;
        y += dy;
    }

}
