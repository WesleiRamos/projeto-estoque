package model.bean;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class GenericBean {
   
    public static SimpleDateFormat formatDate = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    public static NumberFormat formatNumber = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
    
    /**
     * Retorna como linha de uma tabela
     * @return 
     */
    public Object[] getAsRow() {
        return new Object[] {};
    }
    
    /**
     * 
     * @return 
     */
    public String toString() {
        return this.getClass().getSimpleName();
    }
}
