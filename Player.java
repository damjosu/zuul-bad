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
    // id del jugador.
    private int id;
    // Capacidad total en Kg que puede llevar el jugador.
    private float capacity;
    // Peso que lleva el jugador.
    private ArrayList<Item> inventory;
    // Habitación en la que se encuentra el jugador.
    private Room currentRoom;
    // Mapa relativo a la habitación principal.
    private Stack<Room> map;
    // Valor inicial de la autonumeración de jugadores.
    private static int p = 0;
    /**
     * Constructor de la clase Player.
     */
    public Player(String name, float capacity)
    {
        map = new Stack<>();
        inventory = new ArrayList<>();
        currentRoom = null;
        this.name = name;        
        this.capacity = capacity;
        id = p;
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
     * @return el peso actual que lleva el jugador en Kg.
     */
    public float carryWeight()
    {
        float carryWeight = 0;
        for (Item item : inventory)
        {
            carryWeight += item.getWeight();
        }
        return carryWeight;
    }

    /**
     * Comprueba que el objeto a coger tenga un peso que pueda soportar, y sea un objeto que se 
     * pueda transportar, en caso de poder cogerlo lo añade al inventario y desaparece de la habitación.
     * @param id la id del objeto a coger.
     */
    public void take(int id)
    {
        if (carryWeight() == capacity) 
        {
            System.out.println("¡No puedes llevar más!");
        }
        else
        {
            Item currentItem = currentRoom.itemSearch(id);
            if (currentItem != null)
            {
                if (currentItem.carryAble()) 
                {
                    if ((carryWeight() + currentItem.getWeight()) <= capacity)
                    {
                        inventory.add(currentRoom.removeItem(id));
                        System.out.println("Has cogido " + currentItem.getDescription() + " de " + currentRoom.getDescription() + " puedes llevar " + String.format("%.2f", Math.abs(capacity - carryWeight())) + "Kg más\n");
                    }   
                    else
                    {
                        System.out.println(currentItem.getDescription() + " pesa demasiado, solo puedes llevar " + String.format("%.2f", Math.abs(capacity - carryWeight())) + "Kg más");
                    }
                }
                else
                {
                    System.out.println("¡Animal! no te puedes llevar eso");
                } 
            }
            else
            {
                System.out.println("Ese objeto no está en la habitación");
            }
        }
    }

    /**
     * Suelta un objeto que le pasas por parametro, lo elimina
     * del inventario y se deposita en la habitación.
     * @id la id del objeto a soltar.
     * @return el objeto a soltar.
     */
    public void drop(int id)
    {
        int i = 0;
        boolean match = false;     
        while (i < inventory.size() && !match)
        {
            if (inventory.get(i).getId() == id)
            {
                String capacityLeft = String.format("%.2f", (capacity - carryWeight()));
                System.out.println("Has dejado caer " + inventory.get(i).getDescription() + " en " + currentRoom.getDescription() + " puedes llevar " + capacityLeft + "Kg más");                
                currentRoom.addItem(inventory.get(i));
                inventory.remove(inventory.get(i));
                match = true;                
            }
            i++;
        }
        if (!match)
        {
            System.out.println("No tienes ese objeto");
        }
    }

    /**
     * Muestra el inventario actual del jugador.
     */
    public void showInventory()
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
    public boolean back()
    {
        boolean back = false;
        if (!map.isEmpty())
        {
            currentRoom = map.pop();
            back = true;
        }     
        else
        {
            System.out.println("No puedes retroceder");
        }
        return back;
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
