import java.util.ArrayList;
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
    // Salidas de la habitación.
    private ArrayList<Door> doors;
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
        items = new ArrayList<>();
        doors = new ArrayList<>();
    }

    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor The room in the given direction.
     */
    public void addDoor(Door door)
    {
        doors.add(door);
    }
    
    /**
     * @param direction dirección de la salida.
     * @return La salida que está en la direcion pasada por parametro.
     */
    public Door getDoor(String direction)
    {
        int i = 0;
        boolean match = false;
        Door door = null;
        while (!match && i < doors.size())        
        {
            if (doors.get(i).getDirection().equals(direction))
            {
                door = doors.get(i);
                match = true;
            }
            i++;
        }
        return door;
    }
    
    /**
     * @param direction dirección de la habitacion contigüa.
     * @return La habitación que está en la direcion pasada por parametro.
     */
    public Room getRoom(String direction)
    {
        int i = 0;
        boolean match = false;
        Room room = null;
        while (!match && i < doors.size())        
        {
            if (doors.get(i).getDirection().equals(direction))
            {
                room = doors.get(i).to();
                match = true;
            }
            i++;
        }
        return room;
    }
    
    public boolean doorsLocked()
    {
        int i = 0;
        boolean locked  = true;
        while (locked && i < doors.size())
        {
            if (!doors.get(i).isLocked()) 
            {
                locked = false;
            }
            i++;
        }
        return locked;
    }
    
    /**
     * Return a description of the room's exits.
     * For example: "Doors: north east west"
     *
     * @ return A description of the available exits.
     */    
    public String getDoorString()
    {
        String theDoors = "";
        for (Door door : doors)
        {
            theDoors += door.getDirection() + " ";
        }        
        return theDoors;
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
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
     * Elimina un objeto de la habitación.
     * @id la id del objeto a eliminar.
     * @return el objeto eliminado.
     */
    public Item removeItem(int id)
    {
        int i = 0;
        Item removedItem = null;
        boolean match = false;
        while (i < items.size() && !match)
        {
            if (items.get(i).getId() == id)
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
     * @param id la id del objeto a buscar.
     * @return el objeto.
     */
    public Item itemSearch(int id)
    {
        boolean match = false;
        int i = 0;
        Item item  = null;
        while (i < items.size() && !match)
        {
            if (items.get(i).getId() == id)
            {
                item = items.get(i);
                match = true;
            }
            i++;
        }
        return item;
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
     *     Doors: north west southwest
     * @return A description of the room, including exits.
     */
    public String getLongDescription()
    {
        String longDescription;
        if (items.isEmpty())
        {
            longDescription = "Estás en " + getDescription() + ", no hay objetos\nSalidas: " + getDoorString();
        }
        else
        {
            longDescription = "Estás en " + getDescription() + ", se hallan los siguientes objetos:\n" + allItemsToString() + "Salidas: " + getDoorString();
        }        
        return longDescription;
    }
}