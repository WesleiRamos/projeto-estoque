package estoque;

import com.formdev.flatlaf.FlatDarkLaf;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import views.EstoqueUI;
import views.LoginUI;

public class Estoque {

    public static void main(String[] args) {
        try {
            FlatDarkLaf theme = new FlatDarkLaf();

            javax.swing.UIManager.put("TitlePane.unifiedBackground", false);
            javax.swing.UIManager.put("TitlePane.background", new java.awt.Color(36, 36, 36));
            javax.swing.UIManager.put("TitlePane.centerTitle", true);
            javax.swing.UIManager.put("Table.showVerticalLines", true);
            javax.swing.UIManager.put("Table.showHorizontalLines", true);
            javax.swing.UIManager.put("Table.rowHeight", 25);
            javax.swing.UIManager.put("Table.cellMargins", new java.awt.Insets(0, 5, 0, 5));
            javax.swing.UIManager.setLookAndFeel(theme);
            
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    LoginUI loginUI = new LoginUI();
                    EstoqueUI estoqueUI = new EstoqueUI();
                    loginUI.addWindowListener(new WindowAdapter() {
                        @Override
                        public void windowClosed(WindowEvent event) {
                            super.windowClosed(event);
                            if (Singleton.isLoggedIn()) {
                                estoqueUI.setVisible(true);
                            } else {
                                estoqueUI.dispose();
                            }
                        }
                    });
                    loginUI.setVisible(true);
                }
            });

        } catch (Exception ex) {
            System.err.println("Failed to initialize LaF");
        }  
    }
}
