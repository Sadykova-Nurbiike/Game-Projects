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



/**

 *

 * @author m1yveq

 */

public class GreedyPlayer extends Player{

    

    //Constructor

    public GreedyPlayer(String name){

        super(name,"Greedy_Player");

    }

    

    //Methods

    

    /**

     * Buys property, if he has enough money for it.

     * @param property  property to be bought 

     */

    @Override

    public void buyProperty(Field property){

        if(this.money >= 1000){

            property.beBought(this);

        }

    }

  

    /**

     * Builds house, if he has enough money for it.

     * @param property  property to build house

     */

    @Override

    public void buildHouse(Field property){

        if(this.money >= 4000){

            property.buildHouse(this);

        }

    }

}

