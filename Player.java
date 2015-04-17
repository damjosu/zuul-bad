import java.util.Stack;
import java.util.ArrayList;
/**
 *
 */
public class Player
{
    private Room currentRoom;
    private Stack<Room> rooms;
    private float carryWeight;
    private float capacity;
    private String name;
    private ArrayList<Item> items;
    /**
     * Constructor for objects of class Player
     */
    public Player(String name, float capacity)
    {
        currentRoom = null;
        rooms = new Stack<>();
        items = new ArrayList<>();
        this.name = name;
        carryWeight = 0;
        this.capacity = capacity;
    }
    
    public void take(String item)
    {
        if ((carryWeight + item.getWeight())<= capacity)
        {
            items.add(currentRoom.removeItem(item));
        }        
    }
    
    public void drop()
    {
        if (!items.empty())
        {
            
        }
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
