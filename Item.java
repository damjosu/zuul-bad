/**
 * Clase Item
 * @author (Josu) 
 * @version (18/04/205)
 */
public class Item
{
    // Numero identificador del objeto
    private int id;
    // Descripción del objeto
    private String description;
    // Algo inscrito en el objeto.
    private String note;
    // Peso del objeto
    private float weight;
    // Si el objeto se puede llevar o no
    private boolean carryAble;    
    // Valor inicial de la autonumeración de objetos
    private static int i = 0;

    /**
     * Constructor de la clase Item.
     */
    public Item(String description, float weight, boolean carryAble)
    {
        this.description = description;
        this.weight = weight;
        this.carryAble = carryAble;
        note = null;
        id = i;
        i++;
    }
         
    /**
     * Constructor de la clase Item.
     */
    public Item(String description, float weight, boolean carryAble, String note)
    {
        this.description = description;
        this.weight = weight;
        this.carryAble = carryAble;
        this.note = note;
        id = i;
        i++;
    }
    
    public String getNote()
    {
        return note;
    }
    
    /**
     * @return true si el objeto se puede coger, y false en caso contrario.
     */
    public boolean carryAble()
    {
        return carryAble;
    }

    /**
     * @return la id del objeto.
     */
    public int getId()
    {
        return id;
    }

    /**
     * @return la descripción del objeto.
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * @return el peso del objeto en Kg.
     */
    public float getWeight()
    {
        return weight;
    }

    /**
     * @return una cadena con la descripción y peso del objeto en Kg.
     */
    public String itemToString()
    { 
        return description + "(" + id + ") " + weight + "Kg";
    }
}
