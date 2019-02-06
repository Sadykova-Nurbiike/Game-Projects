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

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BestPlayers {

    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;

    public BestPlayers(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        String dbURL = "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=Europe/Budapest";
        connection = DriverManager.getConnection(dbURL, "root", "forever18");
        String insertQuery = "INSERT INTO HIGHSCORES (NAME, SCORE) VALUES (?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
    }

    /**
     * @return ArrayList of the best players from database
     * @throws SQLException
     */
    public ArrayList<Player> getHighScores() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES";
        ArrayList<Player> bestPlayers = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            bestPlayers.add(new Player(name, score));
        }
        sortHighScores(bestPlayers);
        return bestPlayers;
    }

    /**
     * Insert the player into database if his score is one of the top scores
     *
     * @param name
     * @param score
     * @throws SQLException
     */
    public void putHighScore(String name, int score) throws SQLException {
        ArrayList<Player> bestPlayers = getHighScores();
        if (bestPlayers.size() < maxScores) {
            insertScore(name, score);
        } else {
            int leastScore = bestPlayers.get(bestPlayers.size() - 1).getScore();
            if (leastScore < score) {
                deleteScores(leastScore);
                insertScore(name, score);
            }
        }
    }

    /**
     * Sort the high scores in descending order.
     *
     * @param highScores
     */
    private void sortHighScores(ArrayList<Player> bestPlayers) {
        Collections.sort(bestPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player t, Player t1) {
                return t1.getScore() - t.getScore();
            }
        });
    }

    private void insertScore(String name, int score) throws SQLException {
        insertStatement.setString(1, name);
        insertStatement.setInt(2, score);
        insertStatement.executeUpdate();
    }

    /**
     * Deletes all the players with score.
     *
     * @param score
     */
    private void deleteScores(int score) throws SQLException {
        deleteStatement.setInt(1, score);
        deleteStatement.executeUpdate();
    }
}
