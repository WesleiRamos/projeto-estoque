package views.utils;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.NumberFormatter;
import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class Masks {
    
    public static void numberMask(JFormattedTextField field) {
        createFormatter(DecimalFormat.getNumberInstance(), field);
    }
    
    public static void moneyMask(JFormattedTextField field) {
        createFormatter(new DecimalFormat("#,###.00"), field);
    }
    
    public static void cpfMask(JFormattedTextField field) {
        createFormatter("###.###.###-##", field);
    }
    
    public static void cnpjMask(JFormattedTextField field) {
        createFormatter("##.###.###/####-##", field);
    }
    
    public static void createFormatter(NumberFormat format, JFormattedTextField field) {
        try {
            NumberFormatter formatter = new NumberFormatter(format);
            formatter.setAllowsInvalid(false);
            formatter.install(field);
        } catch (Exception ex) {
            Logger.getLogger("Masks").log(Level.SEVERE, null, ex);
        }
    }
    
    public static void createFormatter(String mask, JFormattedTextField field) {
        try {
            new MaskFormatter(mask).install(field);
        } catch (Exception ex) {
            Logger.getLogger("Masks").log(Level.SEVERE, null, ex);
        }
    }
}
