package views.utils;


import java.awt.Component;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Alerts {
    private static Object[] deleteOptions = {
        "Cancelar",
        "Deletar"
    };
    
    private static Object[] ok = {
        "OK"
    };
    
    /**
     * Mostra uma alerta perguntando se quer realmente executar a ação
     * @param parent
     * @param title
     * @param message
     * @return 
     */
    public static boolean delete(Component parent, String title, String message) {
        return 1 == JOptionPane.showOptionDialog(
            parent, 
            message,
            title,
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE,
            null,
            deleteOptions,
            deleteOptions[0]);
    }
    
    /**
     * 
     * @param parent
     * @param title
     * @param message
     * @return 
     */
    public static boolean error(Component parent, String title, String message) {
       return 1 == JOptionPane.showOptionDialog(
               parent,
               message,
               title,
               JOptionPane.OK_OPTION,
               JOptionPane.ERROR_MESSAGE,
               null,
               ok,
               ok[0]);
    }
    
    /**
     * 
     * @param title
     * @param ex
     * @return 
     */
    public static boolean errorLog(String title, Exception ex) {
        Logger.getLogger("Log").log(Level.SEVERE, null, ex);
        return error(null, title, String.format("%s", ex));
    }
}
