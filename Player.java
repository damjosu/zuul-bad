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
    // Habitaci�n en la que se encuentra el jugador.
    private Room currentRoom;
    // Mapa relativo a la habitaci�n principal.
    private Stack<Room> map;
    // Valor inicial de la autonumeraci�n de jugadores.
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
     * pueda transportar, en caso de poder cogerlo lo a�ade al inventario y desaparece de la habitaci�n.
     * @param id la id del objeto a coger.
     */
    public void take(int id)
    {
        if (carryWeight() == capacity) 
        {
            System.out.println("�No puedes llevar m�s!");
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
                        System.out.println("Has cogido " + currentItem.getDescription() + " de " + currentRoom.getDescription() + " puedes llevar " + String.format("%.2f", Math.abs(capacity - carryWeight())) + "Kg m�s\n");
                    }   
                    else
                    {
                        System.out.println(currentItem.getDescription() + " pesa demasiado, solo puedes llevar " + String.format("%.2f", Math.abs(capacity - carryWeight())) + "Kg m�s");
                    }
                }
                else
                {
                    System.out.println("�Animal! no te puedes llevar eso");
                } 
            }
            else
            {
                System.out.println("Ese objeto no est� en la habitaci�n");
            }
        }
    }

    /**
     * Suelta un objeto que le pasas por parametro, lo elimina
     * del inventario y se deposita en la habitaci�n.
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
                System.out.println("Has dejado caer " + inventory.get(i).getDescription() + " en " + currentRoom.getDescription() + " puedes llevar " + capacityLeft + "Kg m�s");                
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
            System.out.println("El inventario est� vacio");
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
     * Establece la habitaci�n actual en la que esta el jugador.
     * @param room la habitaci�n a establecer.
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
