// Sadykova Nurbiike
//
// M1YVEQ
//
// Second Assignment
//
// 2018/11/20 22:58:23
//
// This solution was submitted and prepared by Sadykova Nurbiike, M1YVEQ for the
// Second Assignment assignment of the Practical software engineering I. course.
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
package blackhole;

import java.awt.Color;

import java.awt.Dimension;

import java.awt.GridLayout;

import java.awt.event.KeyListener;

import java.awt.event.KeyEvent;

import javax.swing.JButton;

import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JPanel;

public class ModelGUI {

    private JButton[][] buttons;

    private Model model;

    private JPanel modelPanel;

    private JLabel label;

    /**
     *
     * Initializes the fields, initializes the starting locations of the
     *
     * spaceships according to their position. Also, adds a ButtonListener(which
     *
     * implements KeyListener) to each button. Whenever a button is clicked, the
     *
     * keyPressed method will be called that belongs to ButtonListener class
     *
     * which mainly decides how to refresh the screen with the updated
     *
     * information.
     *
     *
     *
     * @param modelSize the size of the board
     *
     */
    public ModelGUI(int modelSize) {

        model = new Model(modelSize);

        modelPanel = new JPanel();

        modelPanel.setLayout(new GridLayout(model.getModelSize(), model.getModelSize()));

        buttons = new JButton[model.getModelSize()][model.getModelSize()];

        for (int i = 0; i < model.getModelSize(); ++i) {

            for (int j = 0; j < model.getModelSize(); ++j) {

                JButton button = new JButton();

                button.addKeyListener(new ButtonListener(i, j));

                button.setPreferredSize(new Dimension(80, 40));

                switch (model.getPlayer(i, j)) {

                    case Red:

                        button.setBackground(Color.red);

                        break;

                    case Yellow:

                        button.setBackground(Color.yellow);

                        break;

                    case Hole:

                        button.setBackground(Color.black);

                }

                buttons[i][j] = button;

                modelPanel.add(button);

            }

        }

        label = new JLabel();

        updateLabelText();

    }

    /**
     *
     * refresh the screen with the updated information.
     *
     */
    public void refresh() {

        for (int i = 0; i < model.getModelSize(); ++i) {

            for (int j = 0; j < model.getModelSize(); ++j) {

                JButton button = buttons[i][j];

                switch (model.getPlayer(i, j)) {

                    case Red:

                        button.setBackground(Color.red);

                        break;

                    case Yellow:

                        button.setBackground(Color.yellow);

                        break;

                    case Hole:

                        button.setBackground(Color.black);

                        break;

                    case Nobody:

                        button.setBackground(null);

                }

            }

        }

        if (model.findWinner() != Player.Nobody) {

            JOptionPane.showMessageDialog(modelPanel, model.findWinner() == Player.Red ? "RED player has won!" : "Yellow player has won!");

            System.exit(0);

        }

        updateLabelText();

    }

    /**
     *
     * The ButtonListener class that each button has as its action listener.
     *
     */
    class ButtonListener implements KeyListener {

        private int x, y;

        public ButtonListener(int x, int y) {

            this.x = x;

            this.y = y;

        }

        @Override

        public void keyPressed(KeyEvent e) {

            Direction direction = null;

            Boolean isProperKey = false;

            switch (e.getKeyCode()) {

                case KeyEvent.VK_UP:

                    direction = Direction.Up;

                    isProperKey = true;

                    break;

                case KeyEvent.VK_DOWN:

                    direction = Direction.Down;

                    isProperKey = true;

                    break;

                case KeyEvent.VK_LEFT:

                    direction = Direction.Left;

                    isProperKey = true;

                    break;

                case KeyEvent.VK_RIGHT:

                    direction = Direction.Right;

                    isProperKey = true;

                    break;

            }

            if (isProperKey) {

                if (model.moveActualPlayer(x, y, direction)) {

                    refresh();

                }

            }

        }

        @Override

        public void keyTyped(KeyEvent e) {

        }

        @Override

        public void keyReleased(KeyEvent e) {

        }

    }

    /**
     *
     * Shows the actual player on the label.
     *
     */
    private void updateLabelText() {

        label.setText("Current player: "
                + model.getActualPlayer().name());

    }

    //Getters
    public JPanel getModelPanel() {

        return modelPanel;

    }

    public JLabel getTurnLabel() {

        return label;

    }

}
