
/**
 * Write a description of class Item here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Item
{
    private String name;
    private float weight;
    private boolean carryAble;

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, float weight, boolean carryAble)
    {
        this.name = name;
        this.weight = weight;
        this.carryAble = carryAble;
    }
    
    public String getName()
    {
        return name;
    }
    
    public float getWeight()
    {
        return weight;
    }
    
    public boolean carryAble()
    {
        return carryAble();
    }
        
    public String itemToString()
    { 
        return name + " pesa: " + weight + "Kg";
    }
}
