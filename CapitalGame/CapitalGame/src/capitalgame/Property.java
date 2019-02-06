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

public class Property extends Field {

    

    //Attributes

    private Boolean owned ;

    private Boolean hasHouse;

    private Player owner;

    

    //Constructor

    Property(){

        super("Property");

        this.owned = false;

        this.hasHouse = false;

        this.owner = null;

    }

    

    //Getters and Setters

    @Override

    public Boolean isOwned() {

        return owned;

    }

    

    @Override

    public Boolean hasItHouse() {

        return hasHouse;

    }

    

    @Override

    public Player getOwner() {

        return owner;

    }    

 

    //Methods

    

    /**

     * Will be bought for 1000

     * @param p  player who buys

     */

    @Override

    public void beBought(Player p) {

        if(this.owned == false){

            p.pay(1000);

            if(p.inGame){

                this.owned = true;

                this.owner = p;

            }

        }

    }

    

    /**

     * Builds house on this property for 4000

     * @param p  owner of this property.And owner wants to build house

     */

    @Override

    public void buildHouse(Player p) {

        if(this.owner.equals(p) && this.hasHouse.equals(false)){

            p.pay(4000);

            if(p.inGame){

                this.hasHouse = true;

            }

        }

    }

    

    /**

     *Makes this property free to buy, if owner loses

     */

    @Override

    public void ownerLose(){

        this.owned = false;

        this.hasHouse = false;

        this.owner = null;

    }

    

}

