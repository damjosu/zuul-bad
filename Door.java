
/**
 * Write a description of class Exit here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Door
{
    // Dirección donde está la salida.
    private String direction;
    // Habitación a la que da la salida.
    private Room to;
    // Indica si esta abierta o cerrada.
    private boolean isOpen;
    // Indica si se ha activado el cierre de seguridad.
    private boolean isLocked;
    // Contraseña del panel de la puerta.
    private int pass;    

    /**
     * Constructor for objects of class Door
     */
    public Door(String direction, Room to)
    {
        this.to = to;
        this.direction = direction;
        pass = -1;
        isOpen = true;
        isLocked = false;
    }
    
    /**
     * Constructor for objects of class Door
     */
    public Door(String direction, Room to, int pass)
    {
        this.to = to;
        this.direction = direction;
        this.pass = pass;
        isOpen = false;
        isLocked = false;
    }
    
    public String getDirection()
    {
        return direction;
    }
    
    public Room to()
    {
        return to;
    }
    
    public void setPass(int pass)
    {
        this.pass = pass;
    }
    
    public int getPass()
    {
        return pass;
    }
    
    public void open()
    {
        isOpen = true;
    }
    
    public boolean isOpen()
    {
        return isOpen;
    }
    
    public void lock()
    {
        isLocked = true;
    }
    
    public boolean isLocked()
    {
        return isLocked;
    } 
}
