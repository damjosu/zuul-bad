import java.util.Stack;
/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael KÃ¶lling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Player player;
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        parser = new Parser();
        player = new Player();
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcion, salaDeReuniones, servicios, recursosHumanos, despachoDelDirector, salaDeProyecciones;
        
        entrada = new Room("en la entrada del edificio");
        entrada.addItem(new Item("Jarrón", 10.5F));
        
        recepcion = new Room("en recepción");
        recepcion.addItem(new Item("telefono", 3.2F));
        recepcion.addItem(new Item("escritorio", 30F));
        
        salaDeReuniones = new Room("en la sala de reuniones");
        salaDeReuniones.addItem(new Item("Silla", 4.3F));
        salaDeReuniones.addItem(new Item("Cuadro", 1.3F));
        
        servicios = new Room("en los servicios");
        servicios.addItem(new Item("Ventana", 10.2F));
        servicios.addItem(new Item("Inhodoro", 15.6F));
        
        recursosHumanos = new Room("en recursos humanos");
        recursosHumanos.addItem(new Item("PC", 3.5F));
        recursosHumanos.addItem(new Item("Reclamaciones", 1.3F));

        despachoDelDirector = new Room("en el despacho del director");
        despachoDelDirector.addItem(new Item("Plasma", 4.3F));
        despachoDelDirector.addItem(new Item("Puros", 0.2F));
        
        salaDeProyecciones = new Room ("en la sala de proyecciones");
        salaDeProyecciones.addItem(new Item("proyector", 4.1F));
        salaDeProyecciones.addItem(new Item("Pantalla", 3.2F));
        
        entrada.setExit("north", recepcion);
        
        recepcion.setExit("east", salaDeReuniones);
        recepcion.setExit("south", entrada);
        recepcion.setExit("west", recursosHumanos);
        
        salaDeReuniones.setExit("north", servicios);
        salaDeReuniones.setExit("west", recepcion);
        salaDeReuniones.setExit("northWest", salaDeProyecciones);
        
        servicios.setExit("south", salaDeReuniones);
        
        recursosHumanos.setExit("north", despachoDelDirector);
        recursosHumanos.setExit("east", recepcion);
        
        despachoDelDirector.setExit("east", salaDeProyecciones);
        despachoDelDirector.setExit("south", recursosHumanos);
        
        salaDeProyecciones.setExit("southEast", salaDeReuniones);
        salaDeProyecciones.setExit("west", despachoDelDirector);
        
        player.setCurrentRoom(entrada);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Gracias por jugar. Hasta la vista.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Bienvenido a The Office!");
        System.out.println("Escribe 'help' si necesitas ayuda.");
        System.out.println();        
        printLocationInfo();
    }
    
    /**
     * Checks the available exits.
     */
    private void printLocationInfo()
    {
        System.out.println(player.getCurrentRoom().getLongDescription());
    }    
    
    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        if(command.isUnknown()) {
            System.out.println("No se a que te refieres...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);           
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        }
        else if (commandWord.equals("look")) {
            player.look();
        }
        else if (commandWord.equals("eat")) {
            player.eat();
        }
        else if (commandWord.equals("back"))
        {
            player.back();
            printLocationInfo();
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("Estás perdido, solo");
        System.out.println("en la oficina");
        System.out.println();
        parser.showAllCommands();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
       if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Ir a donde?");
            return;
       }
       
       player.goRoom(command);
       printLocationInfo();
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Salir que?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
