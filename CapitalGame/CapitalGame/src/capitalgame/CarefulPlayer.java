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

 * @author M1YVEQ

 */

public class CarefulPlayer extends Player{

    

    //Constructor

    public CarefulPlayer(String name){

        super(name,"Careful_Player");

    }

    

    //Methods

    

    /**

     * Buys property,if half the amount of the money is enough to buy

     * @param property  property to be bought

     */

    @Override

    public void buyProperty(Field property){

        if(this.money >= 2000){

            property.beBought(this);

        }

    }

    

    /**

     * Builds house,if half the amount of the money is enough to buy

     * @param property  property to build house

     */

    @Override

    public void buildHouse(Field property){

        if(this.money >= 8000){

            property.buildHouse(this);

        }

    }

    

}

