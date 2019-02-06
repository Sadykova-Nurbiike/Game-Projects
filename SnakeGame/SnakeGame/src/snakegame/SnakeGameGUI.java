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

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SnakeGameGUI {

    private final JFrame frame; // main frame
    private JFrame tableFrame; // table frame, which shows 10 best players
    private GameEngine gameArea;
    private final TablePanel tablePanel;

    private BestPlayers bestPlayers;

    public SnakeGameGUI() throws SQLException {
        /*Database part*/
        try {
            bestPlayers = new BestPlayers(10);
        } catch (SQLException ex) {
            Logger.getLogger(SnakeGameGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        /*Database part*/

        
        /*Table Frame*/
        tableFrame = new JFrame("10 High Scores!");

        tablePanel = new TablePanel();
        tablePanel.setData(bestPlayers.getHighScores());

        tableFrame.getContentPane().add(tablePanel, BorderLayout.CENTER);

        tableFrame.setPreferredSize(new Dimension(400, 400));
        tableFrame.setResizable(false);

        tableFrame.pack();
        tableFrame.setLocationRelativeTo(null);
        tableFrame.setVisible(false);
        /*Table Frame*/
        

        frame = new JFrame("Snake game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        gameArea = new GameEngine();
        gameArea.setDatabase(bestPlayers);

        frame.getContentPane().add(gameArea, BorderLayout.CENTER);
        frame.getContentPane().add(gameArea.getLabel(), BorderLayout.SOUTH);

        frame.setPreferredSize(new Dimension(508, 570));
        frame.setResizable(false);

        /*Menu Bar*/
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);
        JMenu gameMenu = new JMenu("Menu");
        menuBar.add(gameMenu);

        JMenuItem bestScores = new JMenuItem("10 best scores");
        gameMenu.add(bestScores);
        bestScores.addActionListener((ActionEvent ae) -> {
            tableFrame.setVisible(true);
        });
        JMenuItem exitMenuItem = new JMenuItem("restart");
        gameMenu.add(exitMenuItem);
        exitMenuItem.addActionListener((ActionEvent ae) -> {
            gameArea.restart();
        });
        /*Menu Bar*/

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

    }
}
