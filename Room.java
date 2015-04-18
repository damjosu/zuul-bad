import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    // Descripción de la habitación.
    private String description;
    // Salidas de la habitación y la habitación a la que dan.
    private HashMap<String, Room> rooms;
    // Objetos que hay en la habitación.
    private ArrayList<Item> items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        rooms = new HashMap<>();
        items = new ArrayList<>();
    }
    
    /**
     * Añade un objeto a la habitación.
     * @param item el objeto a añadir.
     */
    public void addItem(Item item)
    {
        items.add(item);
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        rooms.put(direction, neighbor);
    }
    
    /**
     * @param theExit dirección de la salida.
     * @return la habitación que esta en la salida especificada por parametro.
     */
    public Room getExit(String theExit)
    {
        return rooms.get(theExit);
    }
    
    /**
     * Return a description of the room's exits.
     * For example: "Exits: north east west"
     *
     * @ return A description of the available exits.
     */
    public String getExitString()
    {
        String theExits = "";
        Set<String> exits = rooms.keySet();
        for (String exit : exits)
        {
            theExits += exit + " ";
        }        
        return theExits;
    }
      
    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Elimina un objeto de la habitación.
     * @ID la ID del objeto a eliminar.
     * @return el objeto eliminado.
     */
    public Item removeItem(int ID)
    {
        int i = 0;
        Item removedItem = null;
        boolean match = false;
        while (i < items.size() && !match)
        {
            if (items.get(i).getID() == ID)
            {
                match = true;
                removedItem = items.get(i);
                items.remove(i);
            }
            i++;
        }
        return removedItem;
    }
    
    /**
     * @return una cadena con cada objeto con su descripción y peso en Kg.
     */
    private String allItemsToString()
    {
        String allItems = "";
        for (Item item : items)
        {
            allItems += item.itemToString() + "\n";
        }
        return allItems;
    }
    
    /**
     * Return a long description of this room, of the form:
     *     You are in the 'name of room'
     *     Exits: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        return "Estás en " + getDescription() + "\n Hay un/os: " + allItemsToString() + "\n Salidas: " + getExitString();
    }
}
