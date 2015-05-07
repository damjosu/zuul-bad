
/**
 * Enumeration class Option - write a description of the enum class here
 * 
 * @author (Josu)
 * @version (28/04/2015)
 */
public enum Option
{
    IR("ir"), SALIR("salir"), AYUDA ("ayuda"), 
    MIRAR ("mirar"), COMER ("comer"), VOLVER("volver"), 
    COGER("coger"), SOLTAR("soltar"), INVENTARIO("inventario"),
    INSPECCIONAR("inspeccionar"), DESCONOCIDO("desconocido");
    private String command;
    private Option(String command)
    {
        this.command = command;
    }
    
    public String getCommand()
    {
        return command;
    }
 }
