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
    private int ID;
    // Capacidad total en Kg que puede llevar el jugador.
    private float capacity;
    // Peso que lleva el jugador.
    private float carryWeight;
    // Inventario con los objetos que tiene el jugador.
    private ArrayList<Item> inventory;
    // Habitación en la que se encuentra el jugador.
    private Room currentRoom;
    // Mapa relativo a la habitación principal.
    private Stack<Room> map;
    // Valor inicial de la autonumeración de jugadores.
    private static int p = 1;
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
        ID = p;
        p++;
    }
    
    /**
     * @param slot el hueco del inventario.
     * @return el objeto en el hueco especificado.
     */
    public Item getItem(int slot)
    {
        return inventory.get(slot);
    }
    
    /**
     * @return el numero de objetos en el inventario.
     */
    public int getNumberOfInventoryItems()
    {
        return inventory.size();
    }
    
    /**
     * @return el peso actual de todos los objetos en el inventario.
     */
    public void showCarryWeight()
    {
        if (carryWeight == 0)
        {
            System.out.println("Te sientes ligero");
        }
        else
        {
            System.out.println("Llevas " + carryWeight + " Kg");
        }   
    }
    
    /**
     * Comprueba que el objeto a coger tenga un peso que pueda soportar, y sea un objeto que se 
     * pueda transportar, en caso de poder cogerlo lo añade al inventario y desaparece de la habitación.
     * @param item el objeto a coger.
     * @return true si ha cogido el objeto, false en caso contrario.
     */
    public void take(Item item)
    {
        if (carryWeight == capacity)
        {
            System.out.println("¡No puedes llevar más!");
        }
        else
        {
            if (item.carryAble())
            {
                if ((carryWeight + item.getWeight()) <= capacity)
                {
                    inventory.add(currentRoom.removeItem(item.getID()));
                    carryWeight += item.getWeight();
                }   
                else
                {
                    System.out.println("El " + item.getDescription() + " pesa demasiado, librate de algunos objetos primero.");
                }
            }
            else
            {
                System.out.println("¡Animal! no te puedes llevar eso");
            } 
        }
    }
    
    /**
     * Suelta un objeto que le pasas por parametro, lo elimina
     * del inventario y se deposita en la habitación.
     * @ID el objeto a soltar.
     * 
     */
    public Item drop(Item item)
    {
        int i = 0;
        Item removedItem = null;
        boolean match = false;
        while (i < inventory.size() && !match)
        {
            if (inventory.get(i) == item)
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
     * Muestra el inventario actual del jugador.
     */
    public void showCurrentInventory()
    {
        if (inventory.isEmpty())
        {
            System.out.println("El inventario está vacio");
        }
        else 
        {
            System.out.println("Inventario:");
            for (int i = 0; i < inventory.size(); i++)
            {
                System.out.println(inventory.get(i).itemToString());
            }
        }
    }
    
    /**
     * Establece la habitación actual en la que esta el jugador.
     * @param room la habitación a establecer.
     */
    public void setCurrentRoom(Room room)
    {
        currentRoom = room;
    }
    
    /**
     * @return la habitación actual del jugador.
     */
    public Room getCurrentRoom()
    {
        return currentRoom;
    }
    
    /**
     * Añade al mapa una habitación en la que ha estado el jugador.
     */
    public void roomYouWere(Room room)
    {
        map.push(room);
    }
    
    /**
     * @return true si no hay ninguna habitación en el mapa o false en caso contrario.
     */
    public boolean mapEmpty()
    {
        return map.empty();
    }    

    /**
     * Impreme un mensaje informando de que el jugador ha comido.
     */
    public void eat()
    {
        System.out.println("Acabas de comer y ya no tienes hambre");
    }
    
    /**
     * Imprime un mensaje con la información de la habitación actual en
     * la que se encuentra el jugador.
     */
    public void look()
    {
        System.out.println(currentRoom.getLongDescription());
    }
    
    /**
     * Vuelve a la habitación previa a la actual. 
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
            System.out.println();
            System.out.println("No puedes retroceder");
        }
    }
    
    /**
     * Lleva al jugador a otra habitación en la dirección especificada.
     * En caso de no haber salida en esa dirección te informa de ello.
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
