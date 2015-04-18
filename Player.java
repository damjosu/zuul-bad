import java.util.Stack;
import java.util.ArrayList;
/**
 * Clase Player
 * @author (Josu) 
 * @version (18/04/205)
 */
public class Player
{
    // Nombre del jugador.
    private String name;
    // ID del jugador.
    private String ID;
    // Capacidad total en Kg que puede llevar el jugador.
    private float capacity;
    // Peso que lleva el jugador.
    private float carryWeight;
    // Inventario con los objetos que tiene el jugador.
    private ArrayList<Item> inventory;
    // Habitaci�n en la que se encuentra el jugador.
    private Room currentRoom;
    // Mapa relativo a la habitaci�n principal.
    private Stack<Room> map;
    /**
     * Constructor de la clase Player.
     */
    public Player(String name, float capacity)
    {
        map = new Stack<>();
        inventory = new ArrayList<>();
        currentRoom = null;
        carryWeight = 0;
        this.name = name;        
        this.capacity = capacity;
    }
    
    /**
     * Comprueba que el objeto a coger tenga un peso que pueda soportar, en
     * caso de poder cogerlo lo a�ade al inventario y desaparece de la habitaci�n.
     * @param item el objeto a coger.
     */
    public void take(Item item)
    {
        if ((carryWeight + item.getWeight())<= capacity)
        {
            inventory.add(currentRoom.removeItem(item.getID()));
        }        
    }
    
    /**
     * Suelta un objeto que le pasas por parametro, lo elimina
     * del inventario y se deposita en la habitaci�n.
     * @ID el objeto a soltar.
     * 
     */
    public Item drop(int ID)
    {
        int i = 0;
        Item removedItem = null;
        boolean match = false;
        while (i < inventory.size() && !match)
        {
            if (inventory.get(i).getID() == ID)
            {
                match = true;
                removedItem = inventory.get(i);
                inventory.remove(i);
            }
            i++;
        }
        return removedItem;
    }
    
    /**
     * Establece la habitaci�n actual en la que esta el jugador.
     * @param room la habitaci�n a establecer.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * @return la habitaci�n actual del jugador.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * A�ade al mapa una habitaci�n en la que ha estado el jugador.
     */
    public void roomYouWere(Room room)
    {
        map.push(room);
    }
    
    /**
     * @return true si no hay ninguna habitaci�n en el mapa o false en caso contrario.
     */
    public boolean mapEmpty()
    {
        return map.empty();
    }    

    //     public Room removeRoomFromMap()
    //     {
    //         return map.pop();
    //     }
    
    /**
     * Impreme un mensaje informando de que el jugador ha comido.
     */
    public void eat()
    {
        System.out.println("Acabas de comer y ya no tienes hambre");
    }
    
    /**
     * Imprime un mensaje con la informaci�n de la habitaci�n actual en
     * la que se encuentra el jugador.
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Vuelve a la habitaci�n previa a la actual. 
     * Si no es posible te informa de ello.
     */
    public void back()
    {
        if (!mapEmpty())
        {
            currentRoom = map.pop();
        }     
        else
        {
            System.out.println("No puedes volver a la nada");
        }
    }
    
    /**
     * Lleva al jugador a otra habitaci�n en la direcci�n especificada.
     * En caso de no haber salida en esa direcci�n te informa de ello.
     * @direction direccion a la que va el jugador.
     */
    public void goRoom(String direction)
    {
        Room nextRoom = currentRoom.getExit(direction);        
        if (nextRoom == null) {
            System.out.println("No hay puerta!");
        }
        else {
            map.push(currentRoom);
            currentRoom = nextRoom;
        }
    }
}
