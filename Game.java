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
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcion, salaDeReuniones, servicios, recursosHumanos, despachoDelDirector, salaDeProyecciones;
        entrada = new Room("en la entrada del edificio");
        recepcion = new Room("en recepci�n");
        salaDeReuniones = new Room("en la sala de reuniones");
        servicios = new Room("en los servicios");
        recursosHumanos = new Room("en recursos humanos");
        despachoDelDirector = new Room("en el despacho del director");
        salaDeProyecciones = new Room ("en la sala de proyecciones");
        
        entrada.setExits(recepcion, null, null, null, null);
        recepcion.setExits(null, salaDeReuniones, null, recursosHumanos, null);
        salaDeReuniones.setExits(servicios, null, null, recepcion, null);
        servicios.setExits(null, null, salaDeReuniones, null, null);
        recursosHumanos.setExits(despachoDelDirector, recepcion, null, null, null);
        despachoDelDirector.setExits(null, salaDeProyecciones, recursosHumanos, null, null);
        salaDeProyecciones.setExits(null, null, null, despachoDelDirector, salaDeReuniones);
        
        currentRoom = entrada;      
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
        System.out.println("Thank you for playing.  Good bye.");
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
        System.out.println("Est�s " + currentRoom.getDescription());
        System.out.print("Exits: ");
        System.out.println(currentRoom.getExitString());
        System.out.println();
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
            System.out.println("I don't know what you mean...");
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
        System.out.println("Est�s perdido, solo");
        System.out.println("en la oficina");
        System.out.println();
        System.out.println("Los comandos son:");
        System.out.println("   go quit help");
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();        
        Room nextRoom = currentRoom.getExit(direction);        
        if (nextRoom == null) {
            System.out.println("No hay puerta!");
        }
        else {
            currentRoom = nextRoom;
            printLocationInfo();            
        }
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
