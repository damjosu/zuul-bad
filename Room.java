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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */
public class Room 
{
    private String description;
    private Room northExit;
    private Room southExit;
    private Room eastExit;
    private Room westExit;
    private Room southEastExit;
    private Room northWestExit;
    private HashMap<String, Room> rooms;

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
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west, Room southEast, Room northWest) 
    {
        if(north != null)
        {
            northExit = north;  
            rooms.put("north", north);
        }
                      
        if(east != null)            
        {
            eastExit = east;
            rooms.put("east", east);
        }
        
        if(south != null)
        {
             southExit = south;
             rooms.put("south", south);
        }
           
        if(west != null)
        {
             westExit = west;
             rooms.put("west", west);
        }
           
        if(southEast != null)
        {
            southEastExit = southEast;
            rooms.put("southEast", southEast);
        }
            
        if(northWestExit != null)
        {
            northWestExit = northWest;
            rooms.put("northWest", northWest);
        }
            
    }
    
    public Room getExit(String theExit)
    {
        Room exit = null;
        if (rooms.containsKey(theExit))
        {
            exit = rooms.get(theExit);
        }
        return exit;        
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
            theExits += exit;
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
    
}
