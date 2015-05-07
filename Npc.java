import java.util.Random;
/**
 * Write a description of class NPC here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Npc
{
    private int hP;
    // Nombre del NPC.
    private String name;
    // Habilidad de 1 a 100;
    private int skill;
    private Gun gun;
    // id del NPC.    
    private int id;
    // Valor inicial de la autonumeración de jugadores.
    private static int n = 0;
    /**
     * Constructor for objects of class NPC
     */
    public Npc(String name)
    {
        Random rnd = new Random();
        this.name = name;
        hP = 100;
        gun = new Gun(rnd.nextInt(5) + 1);
        skill = (rnd.nextInt(100) + 1);
    }
    
    public String getName()
    {
        return name;
    }
    
    public int getHp()
    {
        return hP;
    }
    
    public int getAmmo()
    {
        return gun.getAmmo();
    }
    
    public int shoot()
    {
        int aim = 0;
        if (gun.getAmmo() != 0)
        {
            gun.shoot();
            aim = (gun.ACCURACY + skill) / 2;
        }
        return aim;
    }
    
    public void setHp(int damage)
    {
        hP -= damage;
    }
}
