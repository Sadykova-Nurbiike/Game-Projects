// Nurbiike Sadykova
//
// M1YVEQ
//
// Assignment-1
//
// 2018/10/15 17:26:28
//
// This solution was submitted and prepared by Nurbiike Sadykova, M1YVEQ for the
// Assignment-1 assignment of the Practical software engineering I. course.
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



package capitalgame;



import java.io.BufferedReader;

import java.io.FileNotFoundException;

import java.io.FileReader;

import java.util.ArrayList;

import java.util.Scanner;

import java.util.concurrent.CopyOnWriteArrayList;



/**

 *

 * @author M1YVEQ

 */

public class Game {

 

    //Attributes

    private int numFields;

    private int numPlayers;

    private final ArrayList<Field> fields ;

    private final CopyOnWriteArrayList<Player> players;

    private final ArrayList<Integer> steps;

    private final ArrayList<Player> losers;



    //Constructor

    public Game() {

        fields = new ArrayList<>();

        players = new CopyOnWriteArrayList<>();

        steps = new ArrayList<>();

        losers = new ArrayList<>();

        numFields = 0 ;

        numPlayers = 0;

    }



    /**

     * Reads the parameters of the game from a text file

     * @param filename 

     * @throws FileNotFoundException

     * @throws InvalidInputException 

     */

    public void read(String filename) throws FileNotFoundException, InvalidInputException {

        Scanner sc = new Scanner(new BufferedReader(new FileReader(filename)));

        numFields = sc.nextInt();

        for (int i = 0; i < numFields; i++) {

            Field field;

            switch (sc.next()) {

                case "L":

                    field = new LuckyField(sc.nextInt());

                    break;

                case "P":

                    field = new Property();

                    break;

                case "S":

                    field = new Service(sc.nextInt());

                    break;

                default:

                    throw new InvalidInputException();

            }

            fields.add(field);

        }

        numPlayers = sc.nextInt();

        for (int i = 0; i < numPlayers; i++) {

            Player player;

            switch (sc.next()) {

                case "G":

                    player = new GreedyPlayer(sc.next());

                    break;

                case "C":

                    player = new CarefulPlayer(sc.next());

                    break;

                case "T":

                    player = new TacticalPlayer(sc.next());

                    break;

                default:

                    throw new InvalidInputException();

            }

            players.add(player);

        }

        while (sc.hasNextInt()) {

            steps.add(sc.nextInt());

        }

    }

    

            

    /**

     * Prints out which player loses as a second loser

     */

    public void gameInProgress() {
        
    int stepCnt = 0;

    while( losers.size() < 2) {

        for(Player p : players){

            int indexTF = (steps.get(stepCnt) + p.getPosition()) % numFields; //Index of target field

            Field targetField = fields.get(indexTF);

            p.setPosition(indexTF);

            switch(targetField.getType()){

                case "Lucky_Field":

                    p.bePaid(targetField.getCost());

                    break;

                case "Service":

                    p.pay(targetField.getCost());

                    break;

                case "Property":                                        

                    if(targetField.isOwned()){                          //-if property is owned- START

                        if(!targetField.getOwner().equals(p)){                // -owner is not p- START

                            if(targetField.hasItHouse()){

                                p.pay(2000);

                                if(p.isInGame()){

                                    targetField.getOwner().bePaid(2000);

                                }

                            }

                            else {

                                p.pay(500);

                                if(p.isInGame()){

                                    targetField.getOwner().bePaid(500);

                                }

                            }

                        }                                                     // -owner is not p- END

                        if(targetField.getOwner().equals(p)){                 // -owner is p- START

                            if(!targetField.hasItHouse()){

                                p.buildHouse(targetField);

                            }

                        }                                                     // -owner is p- END

                    }                                                   //-if property is owned- END

                    else{                                               //-if property isn't owned- START

                        p.buyProperty(targetField);

                    }                                                   //-if property isn't owned- END

            }

            if (!p.isInGame()) {

                losers.add(p);

                players.remove(p);

            }       

            stepCnt++;

        }

    }
    System.out.println(losers.get(1) + " loses as a second loser.");

}

}

