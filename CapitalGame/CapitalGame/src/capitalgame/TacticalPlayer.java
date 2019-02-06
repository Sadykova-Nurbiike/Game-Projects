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

public class TacticalPlayer extends Player{

     

    //Attributes

    private int chance;

    

    //Constructor

    public TacticalPlayer(String name){

        super(name,"Tactical_Player");

        this.chance = 1;

    }

    

    //methods

    

    /**

     * Buys property.He skips each second chance when he could buy.

     * @param property  property to be bought

     */

    @Override

    public void buyProperty(Field property){

        if(chance % 2 == 1){

            property.beBought(this);

        }

        chance += 1;

    }

    

    /**

     * Builds house.He skips each second chance when he could build.

     * @param property property to build house

     */

    @Override

    public void buildHouse(Field property){

        if(chance % 2 == 1){

            property.buildHouse(this);

        }

        chance += 1;

    }

}

