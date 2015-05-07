import java.util.Stack;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
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
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
        System.out.println(currentRoom.getLongDescription());
        System.out.println("\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\");
    }
    
    public void inspect(int id)
    {
        int i = 0;
        boolean gotIt = false;
        Item item = currentRoom.itemSearch(id);
        while (item == null && gotIt && i < inventory.size())
        {
            if (inventory.get(i).getId() == id)
            {
                item = inventory.get(i);
                gotIt = true;
            }
            i++;
        }
        
        if (item != null)
        {
            if (item.getNote() == null)
            {
                System.out.println("Es un simple " + item.getDescription());
            }
            else
            {
                System.out.println("Parece que tiene algo escrito: " + item.getNote());
            }
        }
        else
        {
            System.out.println("Ese objeto no está en la habitación ni en tu inventario");
        }
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
    public boolean goRoom(String direction)
    {
        boolean gone = false;
        Room nextRoom = currentRoom.getRoom(direction);
        Door currentDoor = currentRoom.getDoor(direction);
        if (currentRoom.doorsLocked()) // Salidas de la habitación actual cerradas.
        {
            System.out.println("Estás atrapado");
        }
        else // Salidas de la habitación actual abiertas.
        {
            if (nextRoom == null) // No hay habitación en esa dirección.
            {
                System.out.println("¡No hay puerta!");
            }
            else 
            {
                if (currentDoor.isLocked()) // Salida actual bloqueada.
                {
                    System.out.println("La salida está bloqueada");
                }
                else // Salida actual no bloqueada.
                {
                    if (currentDoor.isOpen()) // Salida actual abierta.
                    {
                        map.push(nextRoom);
                        currentRoom = nextRoom;
                        gone = true;
                    }
                    else // Salida actual cerrada.
                    {
                        int pass = -1;
                        Scanner keyboard = new Scanner(System.in);                    
                        System.out.println("Hay un panel electronico, escribe 'si' para introducir la contraseña o 'no' para volver más tarde.");
                        String introducePass = keyboard.next();

                        if (introducePass.equals("si")) // Introducir contraseña
                        {
                            System.out.println("Introduce la contraseña");
                            try
                            { 
                                pass = keyboard.nextInt();
                            }
                            catch(InputMismatchException e)
                            { 
                                // Se queda la contraseña por defecto.
                            }  

                            if (pass == currentDoor.getPass()) // Contraseña introducida correcta.
                            {
                                currentDoor.open();
                                map.push(nextRoom);
                                currentRoom = nextRoom;
                                System.out.println("Correcto");
                                gone = true;
                            }
                            else // Contraseña introducida incorrecta.
                            {
                                currentDoor.lock();
                                System.out.println("Incorrecto");
                            }
                        }                    
                    }
                }    
            }
        }
        return gone;
    }
}
