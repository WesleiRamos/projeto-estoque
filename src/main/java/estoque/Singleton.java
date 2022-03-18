package estoque;

import model.bean.Usuario;

public class Singleton {
    
    public static final int hashCost = 10;
    private static Usuario usuario = null;
    
    /**
     * 
     * @return 
     */
    public static boolean isLoggedIn() {
        return usuario != null;
    }
    
    /**
     * 
     */
    public static Usuario getUsuario() {
        return usuario;
    }
    
    /**
     * 
     */
    public static void setUsuario(Usuario u) {
        if (usuario == null) usuario = u;
    }
}
