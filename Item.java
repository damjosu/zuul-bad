
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

    /**
     * Constructor for objects of class Item
     */
    public Item(String name, float weight)
    {
        this.name = name;
        this.weight = weight;
    }
    
    public String getName()
    {
        return name;
    }
    
    public float getWeight()
    {
        return weight;
    }
    
    public String itemToString()
    { 
        return name + " pesa: " + weight + "Kg";
    }
    
//     public boolean carryAble()
//     {
//         return carryAble();
//     }
}
