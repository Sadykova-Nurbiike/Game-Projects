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
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class TablePanel extends JPanel {

    private final JTable table;
    private final PersonTableModel tableModel;

    public TablePanel() {

        tableModel = new PersonTableModel();
        table = new JTable(tableModel);

        setLayout(new BorderLayout());

        add(new JScrollPane(table), BorderLayout.CENTER);
    }

    public void setData(ArrayList<Player> players) {
        tableModel.setData(players);
    }

    
}
