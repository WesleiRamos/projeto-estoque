package views;

import estoque.Singleton;
import java.util.ArrayList;
import model.bean.Usuario;
import model.dao.UsuarioDAO;
import views.utils.Alerts;
import static views.utils.Image.*;

/**
 *
 * @author Weslei Ramos
 */
public class LoginUI extends javax.swing.JFrame {

    /**
     * Creates new form Login
     */
    public LoginUI() {
        initComponents();
        setLocationRelativeTo(null); 
        setIconImage(loadImage(LOGO, 32, 32));
        loadResizeAndSet(logoEstoque, LOGO);
    }
    
    /**
     * 
     * @param v 
     */
    private void enableDisableFields(boolean v) {
        email.setEnabled(v);
        senha.setEnabled(v);
        login.setEnabled(v);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        logoEstoque = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        email = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        login = new javax.swing.JButton();
        senha = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("StockMaster · Fazer login");
        setMaximumSize(new java.awt.Dimension(770, 530));
        setMinimumSize(new java.awt.Dimension(770, 530));
        setResizable(false);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel2.setText("E-mail");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jLabel3.setText("Senha");

        login.setBackground(new java.awt.Color(20, 20, 20));
        login.setFont(new java.awt.Font("Segoe UI", 1, 20)); // NOI18N
        login.setText("LOGIN");
        login.setBorder(null);
        login.setName(""); // NOI18N
        login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(logoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(280, 280, 280))
            .addGroup(layout.createSequentialGroup()
                .addGap(200, 200, 200)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel3)
                    .addComponent(jLabel2)
                    .addComponent(email, javax.swing.GroupLayout.DEFAULT_SIZE, 380, Short.MAX_VALUE)
                    .addComponent(login, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(senha))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(logoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(41, 41, 41)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(email, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(senha, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addComponent(login, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(39, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginActionPerformed
        enableDisableFields(false);
        
        UsuarioDAO temp_dao = (UsuarioDAO) (new UsuarioDAO().where("email", email.getText().trim()));
        ArrayList<UsuarioDAO> results = temp_dao.get();
        if (results.size() == 0) {
            Alerts.error(null, "Dados incorretos", "E-mail ou senha inválidos");
            enableDisableFields(true);
            return;
        }
        
        Usuario usuario = results.get(0).toBean();
        if (!usuario.verifyPassword(senha.getText().trim())) {
            Alerts.error(null, "Dados incorretos", "E-mail ou senha inválidos");
            enableDisableFields(true);
            return;
        }
        
        Singleton.setUsuario(usuario);
        this.close();
    }//GEN-LAST:event_loginActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JButton login;
    private javax.swing.JLabel logoEstoque;
    private javax.swing.JPasswordField senha;
    // End of variables declaration//GEN-END:variables
}
