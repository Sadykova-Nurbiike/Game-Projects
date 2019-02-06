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

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class PersonTableModel extends AbstractTableModel {

    private ArrayList<Player> players;

    private String[] colNames = {"Place", "Name", "Score"};

    public void setData(ArrayList<Player> players) {
        this.players = players;
    }

    @Override
    public int getRowCount() {
        return players.size();
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int row, int column) {
        Player player = players.get(row);

        switch (column) {
            case 0:
                return row + 1;
            case 1:
                return player.getName();
            case 2:
                return player.getScore();
        }
        return null;
    }

    @Override
    public String getColumnName(int column) {
        return colNames[column];
    }
}
