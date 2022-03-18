package views.utils;

public class Image {
    
    public static final String LOGO = "https://i.imgur.com/PoFuZ8j.png";
    public static final String USUARIO_ICON = "https://i.imgur.com/lkpMtcW.png";
    // https://imgur.com/a/JA5tgJd
    public static final String FORNECEDOR_ICON = "https://i.imgur.com/NWO7H1j.png";
    public static final String CLIENTE_ICON = "https://i.imgur.com/onzDbwH.png";
    // https://imgur.com/a/8JfdIPl
    public static final String PRODUTO_ICON = "https://i.imgur.com/3ZKl8WW.png";
    public static final String TIPO_PRODUTO_ICON = "https://i.imgur.com/MNMQiZk.png";
    // https://imgur.com/a/jnIvjci
    public static final String COMPRAS_ICON = "https://i.imgur.com/3HmR4h7.png";
    public static final String VENDAS_ICON = "https://i.imgur.com/5oK7c4s.png";

    /**
     * Carrega imagem, redimensiona e define ela no label informado
     * @param label
     * @param url 
     */
    public static void loadResizeAndSet(javax.swing.JLabel label, String url) {
        try {
            label.setIcon(new javax.swing.ImageIcon(loadImage(url, label.getWidth(), label.getHeight())));
        } catch(Exception ex) {
            java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Carrega uma imagem a partir de uma url
     * @param url
     * @return 
     */
    public static java.awt.Image loadImage(String url, int width, int height) {
        try {
            java.awt.image.BufferedImage image = javax.imageio.ImageIO.read(new java.net.URL(url));
            return image.getScaledInstance(width, height, java.awt.Image.SCALE_SMOOTH);
        }  catch(Exception ex) {
            java.util.logging.Logger.getLogger(Image.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        
        return null;
    }
}
