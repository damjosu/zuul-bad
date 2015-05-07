
/**
 * Write a description of class Weapon here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Gun extends Item
{
    private int ammo;
    public static final int ACCURACY = 20;
    public static final int DAMAGE = 50;
    public static final String DESCRIPTION = "Glock 25SDN";
    public static final float WEIGHT = 1.5F;
    /**
     * Constructor for objects of class Weapon
     */
    public Gun(int ammo)
    {
        super(DESCRIPTION, WEIGHT, true);
        this.ammo = ammo;
    }    
        
    public void shoot()
    {
        if (ammo != 0)
        {
            ammo--;
        }
    }
    
    public int getAmmo()
    {
        return ammo;
    }
}
