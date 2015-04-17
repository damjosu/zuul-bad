import java.util.Stack;
import java.util.ArrayList;
/**
 *
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> rooms;
    /**
     * Constructor for objects of class Player
     */
    public Player()
    {
        currentRoom = null;
        rooms = new Stack<>();
    }
    
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    public void roomYouWere(Room room)
    {
        rooms.push(room);
    }
    
    public boolean roomsLogEmpty()
    {
        return rooms.empty();
    }    

    public Room removeRoomLogEntry()
    {
        return rooms.pop();
    }
    
    public void eat()
    {
        System.out.println("Acabas de comer y ya no tienes hambre");
    }
    
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    public void back()
    {
        if (!roomsLogEmpty())
        {
            currentRoom = rooms.pop();
        }     
        else
        {
            System.out.println("No puedes volver a la nada");
        }
    }
    
    public void goRoom(String direction)
    {
        Room nextRoom = currentRoom.getExit(direction);        
        if (nextRoom == null) {
            System.out.println("No hay puerta!");
        }
        else {
            rooms.push(currentRoom);
            currentRoom = nextRoom;
        }
    }
}
