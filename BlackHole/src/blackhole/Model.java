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

/*

 * To change this license header, choose License Headers in Project Properties.

 * To change this template file, choose Tools | Templates

 * and open the template in the editor.

 */
package blackhole;

public class Model {

    private final int size; //size of the board

    private Player actualPlayer;

    private final Player[][] table;

    /**
     *
     * Initializes the fields in matrix table, initializes the starting
     *
     * locations of the players spaceships according to their position.
     *
     *
     *
     * @param size size of the board
     *
     */
    public Model(int size) {

        this.size = size;

        actualPlayer = Player.Red;

        table = new Player[size][size];

        for (int i = 0; i < size; ++i) {

            for (int j = 0; j < size; ++j) {

                if (i == size / 2 && j == size / 2) {

                    table[i][j] = Player.Hole;

                } else {

                    table[i][j] = Player.Nobody;

                }

            }

        }

        for (int i = 0; i < size; ++i) {
            if (i != size / 2) {
                table[i][i] = Player.Red;
                table[i][size - i - 1] = Player.Yellow;
            }

        }
    }

    /**
     *
     * Makes the move for the actual player,and sets value of actual player next
     *
     * player
     *
     *
     *
     * @param row row index of actual player
     *
     * @param column column index of actual player
     *
     * @param direction direction of movement
     *
     * @return true if the move successfully done, false if not.
     *
     */
    public Boolean moveActualPlayer(int row, int column, Direction direction) {

        if (table[row][column] != actualPlayer) {

            return false;

        }

        if (movePlayer(row, column, direction)) {

            if (actualPlayer == Player.Red) {

                actualPlayer = Player.Yellow;

            } else {

                actualPlayer = Player.Red;

            }

            return true;

        }

        return false;

    }

    /**
     *
     * Makes the move for the player
     *
     *
     *
     * @param row row index of player
     *
     * @param column column index of player
     *
     * @param direction direction of movement
     *
     * @return true if the move successfully done, false if not.
     *
     */
    public Boolean movePlayer(int row, int column, Direction direction) {

        int i = row;

        int j = column;

        switch (direction) {

            case Left:

                while (j > 0 && table[row][j - 1] == Player.Nobody) {

                    j--;

                }

                if (j != column) {

                    if (j > 0 && table[row][j - 1] == Player.Hole) {

                        table[row][column] = Player.Nobody;

                        return true;

                    }

                    table[row][j] = table[row][column];

                    table[row][column] = Player.Nobody;

                    return true;

                }

                if (j > 0 && table[row][j - 1] == Player.Hole) {

                    table[row][column] = Player.Nobody;

                    return true;

                }

                return false;

            case Right:

                while (j < size - 1 && table[row][j + 1] == Player.Nobody) {

                    j++;

                }

                if (j != column) {

                    if (j < size - 1 && table[row][j + 1] == Player.Hole) {

                        table[row][column] = Player.Nobody;

                        return true;

                    }

                    table[row][j] = table[row][column];

                    table[row][column] = Player.Nobody;

                    return true;

                }

                if (j < size - 1 && table[row][j + 1] == Player.Hole) {

                    table[row][column] = Player.Nobody;

                    return true;

                }

                return false;

            case Up:

                while (i > 0 && table[i - 1][column] == Player.Nobody) {

                    i--;

                }

                if (i != row) {

                    if (i > 0 && table[i - 1][column] == Player.Hole) {

                        table[row][column] = Player.Nobody;

                        return true;

                    }

                    table[i][column] = table[row][column];

                    table[row][column] = Player.Nobody;

                    return true;

                }

                if (i > 0 && table[i - 1][column] == Player.Hole) {

                    table[row][column] = Player.Nobody;

                    return true;

                }

                return false;

            case Down:

                while (i < size - 1 && table[i + 1][column] == Player.Nobody) {

                    i++;

                }

                if (i != row) {

                    if (i < size - 1 && table[i + 1][column] == Player.Hole) {

                        table[row][column] = Player.Nobody;

                        return true;

                    }

                    table[i][column] = table[row][column];

                    table[row][column] = Player.Nobody;

                    return true;

                }

                if (i < size - 1 && table[i + 1][column] == Player.Hole) {

                    table[row][column] = Player.Nobody;

                    return true;

                }

                return false;

        }

        return false;

    }

    /**
     *
     * checks if the game is over. The game will end, if one of the players
     *
     * manages to move half of his/her spaceships into the black hole.
     *
     *
     *
     * @return the winner (Red or Yellow Player) if the game is over, otherwise
     *
     * return Player.Nobody
     *
     */
    public Player findWinner() {

        int cntY = 0;

        int cntR = 0;

        for (int i = 0; i < size; i++) {

            for (int j = 0; j < size; j++) {

                if (table[i][j] == Player.Yellow) {

                    cntY++;

                } else if (table[i][j] == Player.Red) {

                    cntR++;

                }

            }

        }

        if (cntY == size / 2 || cntR == size / 2) {

            if (cntY == size / 2) {

                return Player.Yellow;

            } else {

                return Player.Red;

            }

        }

        return Player.Nobody;

    }

    //Getters
    public Player getActualPlayer() {

        return actualPlayer;

    }

    public int getModelSize() {

        return this.size;

    }

    public Player getPlayer(int row, int column) {

        return table[row][column];

    }

}
