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

import java.awt.BorderLayout;

import java.awt.event.ActionEvent;

import java.awt.event.ActionListener;

import javax.swing.JFrame;

import javax.swing.JMenu;

import javax.swing.JMenuBar;

import javax.swing.JMenuItem;

public class BlackHoleGUI {

    private JFrame frame;

    private ModelGUI boardGUI;

    private final int INITIAL_BOARD_SIZE = 5;

    /**
     *
     * Creates the frame, initializes 'ModelGUI', sets the layout, adds the
     *
     * 'menuBar' adds action listeners to the menu items.
     *
     */
    public BlackHoleGUI() {

        frame = new JFrame("Black hole");

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        boardGUI = new ModelGUI(INITIAL_BOARD_SIZE);

        frame.getContentPane().add(boardGUI.getModelPanel(), BorderLayout.CENTER);

        frame.getContentPane().add(boardGUI.getTurnLabel(), BorderLayout.SOUTH);

        JMenuBar menuBar = new JMenuBar();

        frame.setJMenuBar(menuBar);

        JMenu gameMenu = new JMenu("Game");

        menuBar.add(gameMenu);

        JMenu newMenu = new JMenu("New");

        gameMenu.add(newMenu);

        int[] boardSizes = new int[]{5, 7, 9};

        for (int boardSize : boardSizes) {

            JMenuItem sizeMenuItem = new JMenuItem(boardSize + "x" + boardSize);

            newMenu.add(sizeMenuItem);

            sizeMenuItem.addActionListener(new ActionListener() {

                @Override

                public void actionPerformed(ActionEvent e) {

                    startNewGame(boardSize);

                }

            });

        }

        JMenuItem exitMenuItem = new JMenuItem("Exit");

        gameMenu.add(exitMenuItem);

        exitMenuItem.addActionListener(new ActionListener() {

            @Override

            public void actionPerformed(ActionEvent ae) {

                System.exit(0);

            }

        });

        frame.pack();

        frame.setVisible(true);

    }

    /**
     *
     * Starts a new game, this method can be called on Menu->NewGame or when the
     *
     * game is over.
     *
     *
     *
     * @param n size of the new games board
     *
     */
    public void startNewGame(int n) {

        frame.getContentPane().remove(boardGUI.getModelPanel());

        frame.getContentPane().remove(boardGUI.getTurnLabel());

        boardGUI = new ModelGUI(n);

        frame.getContentPane().add(boardGUI.getModelPanel(), BorderLayout.CENTER);

        frame.getContentPane().add(boardGUI.getTurnLabel(), BorderLayout.SOUTH);

        frame.pack();

    }

    //Getters
    public int getINITIAL_BOARD_SIZE() {

        return INITIAL_BOARD_SIZE;

    }

}
