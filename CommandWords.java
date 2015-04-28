import java.util.ArrayList;
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
    private ArrayList<Option> commands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        commands = new ArrayList();
        for(Option option : Option.values())
        {
            commands.add(option);
        }
    }

    /**
     * Check whether a given String is a valid command word. 
     * @return true if a given string is a valid command,
     * false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        boolean match = false;
        int i = 0;
        while(i < commands.size() && !match)
        {
            if(commands.get(i).getCommand().equals(aString))
            {
                match = true;
            }
            i++;
        }
        return match;
    }

    /**
     * Print all valid commands to System.out
     */
    public void showAll()
    {
        String availableCommands = "Los comandos disponibles son: \n";
        for (Option command : commands)
        {
            availableCommands += command.getCommand() + " ";
        }
        System.out.println(availableCommands);
    }

    /**
     * Return the object Option associated with a word.
     * @param commandWord The word to look up (as a string).
     * @return the object Option correspondng to the paramater commandWord, or the object Option.UNKNOWN
     *         if it is not a valid command word
     */
    public Option getCommandWord(String commandWord)
    {
        Option option = Option.DESCONOCIDO;
        boolean match = false;
        int i = 0;
        while(i < commands.size() && !match)
        {
            option = Option.DESCONOCIDO;
            if(commands.get(i).getCommand().equals(commandWord))
            {
                option = commands.get(i);
                match = true;
            }
            i++;
         }
        return option;
    }
}
