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



import java.util.ArrayList;



/**

 *

 * @author m1yveq

 */

public abstract class Player {

    

    //Attributes

    protected String name;

    protected String strategy;

    protected int money;

    protected int position;

    protected Boolean inGame;

    protected ArrayList<Property>  properties;

    

    //Constructor

    public Player(String name, String strategy) {

        this.name = name;

        this.strategy = strategy;

        this.money = 10000;  

        this.position=0;

        this.inGame = true;

        this.properties = new ArrayList<>();

    }

    

    // Getters and Setters

    public int getPosition(){

        return position;

    }

    

    public void setPosition(int position){

        this.position = position;

    }

    

    public Boolean isInGame(){

        return inGame;

    }

    

    public void addProperty(Property property){

        this.properties.add(property);

    }

    

    //Methods

    

    /**

     * Pays. Decreases the money of the player by the given amount,

     * if player has enough money.Otherwise, player loses and his

     * properties are lost, and become free to buy.

     * @param amount  amount of money to pay

     */

    public void pay(int amount) {

        if(money >= amount){

            money = money - amount;

        }

        else{

            for(int i=0; i<properties.size(); i++){

                properties.get(i).ownerLose();

            }

            this.inGame = false;

        }

    }

    

    /**

     * Gets money 

     * @param amount - amount of money to get 

     */

    public void bePaid(int amount){

         this.money = this.money + amount;

    }

    

    /**

     * Buys property

     * @param property  - property to be bought

     */

    public void buyProperty(Field property){ 

        property.beBought(this);

    }

    

    /**

     * Builds house

     * @param property - property to built house  

     */

    public void buildHouse(Field property){ 

        property.buildHouse(this);

    }

    

    /**

     * Overrides toString() methods

     * @return String

     */

    @Override

    public String toString() {

        return "Player{" + "name=" + name + ", strategy=" + strategy + '}';

    }

    



}

