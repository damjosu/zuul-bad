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
        player = new Player("Josu", 5.3F);
        createRooms();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room entrada, recepcion, salaDeReuniones, servicios, recursosHumanos, despachoDelDirector, salaDeProyecciones;
        
        String passEntrada = "2533 Es la pass del ******** del director, la proxima "+
                            "vez se pensará dos veces en recortar en servicio técnico, " + 
                            "pasasela a todos los que te manden quejas";
        String passSalida = "9-10-1989";
        entrada = new Room("entrada del edificio");
        entrada.addItem(new Item("Jarrón", 2.5F, true));
        entrada.addItem(new Item("Jarrón", 2.5F, true));
        entrada.addItem(new Item("Sofá", 50.3F, false));

        recepcion = new Room("recepción");
        recepcion.addItem(new Item("Silla", 3.5F, true));
        recepcion.addItem(new Item("telefono", 1.2F, true));
        recepcion.addItem(new Item("PC", 6.3F, true, "2533"));
        recepcion.addItem(new Item("Impresora", 3.7F, true));
        recepcion.addItem(new Item("escritorio", 30F, false));

        salaDeReuniones = new Room("sala de reuniones");
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Silla", 3.5F, true));
        salaDeReuniones.addItem(new Item("Mesa", 30.3F, false));
        salaDeReuniones.addItem(new Item("Microfono", 0.2F, true));

        servicios = new Room("servicios");
        servicios.addItem(new Item("Escobilla", 0.7F, true));
        servicios.addItem(new Item("Papel higienico", 0.2F, true));
        servicios.addItem(new Item("Ventana", 10.2F, false));
        servicios.addItem(new Item("Inhodoro", 25.6F, false));
        servicios.addItem(new Item("Lavabo", 10.6F, false));
        servicios.addItem(new Item("Espejo", 7.6F, true));

        recursosHumanos = new Room("recursos humanos");
        recursosHumanos.addItem(new Item("Silla", 3.5F, true));
        recursosHumanos.addItem(new Item("PC", 3.5F, true, passEntrada));
        recursosHumanos.addItem(new Item("Impresora", 3.7F, true));
        recursosHumanos.addItem(new Item("Reclamaciones", 1.3F, true));
        recursosHumanos.addItem(new Item("Ficheros", 35.4F, false));

        despachoDelDirector = new Room("despacho del director");
        despachoDelDirector.addItem(new Item("telefono", 1.2F, true));
        despachoDelDirector.addItem(new Item("Plasma", 4.3F, true));
        despachoDelDirector.addItem(new Item("Puros", 0.2F, true));
        despachoDelDirector.addItem(new Item("Diploma", 2.5F, true));
        despachoDelDirector.addItem(new Item("Silla", 3.5F, true));
        despachoDelDirector.addItem(new Item("Foto familiar", 0.5F, true, passSalida));
        despachoDelDirector.addItem(new Item("Portatil", 2.5F, true));
        despachoDelDirector.addItem(new Item("Sofá", 50.3F, false));

        salaDeProyecciones = new Room ("sala de proyecciones");
        salaDeProyecciones.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("Silla", 3.5F, true));
        salaDeProyecciones.addItem(new Item("proyector", 4.1F, true));
        salaDeProyecciones.addItem(new Item("Pantalla para proyector", 8.2F, false));

        entrada.addDoor(new Door("norte", recepcion));

        recepcion.addDoor(new Door("este", salaDeReuniones));
        recepcion.addDoor(new Door("sur", entrada));
        recepcion.addDoor(new Door("oeste", recursosHumanos));

        salaDeReuniones.addDoor(new Door("norte", servicios));
        salaDeReuniones.addDoor(new Door("oeste", recepcion));
        salaDeReuniones.addDoor(new Door("noroeste", salaDeProyecciones));

        servicios.addDoor(new Door("sur", salaDeReuniones));

        recursosHumanos.addDoor(new Door("norte", despachoDelDirector, 2533));
        recursosHumanos.addDoor(new Door("este", recepcion));

        despachoDelDirector.addDoor(new Door("este", salaDeProyecciones, 9101989));
        despachoDelDirector.addDoor(new Door("sur", recursosHumanos, 9101989));

        salaDeProyecciones.addDoor(new Door("sureste", salaDeReuniones));
        salaDeProyecciones.addDoor(new Door("oeste", despachoDelDirector, 2533));

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
        System.out.println("Bienvenido a The Office!");
        System.out.println("Escribe '" + Option.AYUDA.getCommand() + "' si necesitas ayuda.");      
        player.look();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        Option commandWord = command.getCommandWord();
        switch(commandWord)
        {
            case AYUDA: 
            printHelp();
            break;
            case IR:
            System.out.println();
            goRoom(command);   
            break;
            case SALIR:
            wantToQuit = quit(command);
            break;
            case MIRAR:
            System.out.println();
            player.look();
            break;
            case COMER:
            player.eat();
            break;
            case VOLVER:
            if (player.back())
            {
                System.out.println();
                player.look();
            }           
            break;
            case COGER:
            if(command.hasSecondWord()) 
            {
                try
                { 
                    player.take(Integer.parseInt(command.getSecondWord())); 
                }
                catch(NumberFormatException e)
                { 
                    System.out.println("ID introducida incorrecta");
                }  
            }
            else
            {
                System.out.println("¿Coger el qué?");
            }
            break;
            case SOLTAR:
            if(command.hasSecondWord()) 
            {
                try
                { 
                    player.drop(Integer.parseInt(command.getSecondWord())); 
                }
                catch(NumberFormatException e)
                { 
                    System.out.println("ID introducida incorrecta");
                }  
            }
            else
            {
                System.out.println("¿Soltar el qué?");
            }
            break;
            case INVENTARIO:
            player.showInventory();
            break;
            case INSPECCIONAR:
            if(command.hasSecondWord()) 
            {
                try
                { 
                    player.inspect(Integer.parseInt(command.getSecondWord())); 
                }
                catch(NumberFormatException e)
                { 
                    System.out.println("ID introducida incorrecta");
                }  
            }
            else
            {
                System.out.println("¿Inspeccionar el qué?");
            }
            break;
            case DESCONOCIDO:
            System.out.println("No se a que te refieres...");
            break;
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
        
        if (player.goRoom(command.getSecondWord()))
        {
            player.look();
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
            System.out.println("¿Salir qué?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
