import java.util.LinkedHashMap;
/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2011.07.31
 */

public class CommandWords
{
    // all valid command words
    private LinkedHashMap<String, Option> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        commands = new LinkedHashMap();
        commands.put("go", Option.IR);
        commands.put("quit", Option.SALIR);
        commands.put("help", Option.AYUDA);
        commands.put("look", Option.MIRAR);
        commands.put("eat", Option.COMER);
        commands.put("back", Option.VOLVER);
        commands.put("take", Option.COGER);
        commands.put("drop", Option.SOLTAR);
        commands.put("inventory", Option.INVENTARIO);
        commands.put("unknown", Option.DESCONOCIDO);
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        for(int i = 0; i < commands.size(); i++) {
            if(commands.containsKey(aString))
                return true;
        }
        // if we get here, the string was not found in the commands
        return false;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String availableCommands = "";
        for (String command : commands.keySet())
        {
            availableCommands += command + " ";
        }
        System.out.println("Los comandos disponibles son: \n" + availableCommands);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord)
    {
        Option option = commands.get(commandWord);
        if (option == null)
        {
            option = Option.DESCONOCIDO;
        }
        return option;
    }

}
