package views.modal;

import estoque.Singleton;
import views.utils.Actions;
import java.util.ArrayList;
import reactive.Store;
import model.bean.Compra;
import model.bean.Fornecedor;
import model.bean.Usuario;
import model.dao.FornecedorDAO;
import model.dao.UsuarioDAO;
import static views.utils.Image.LOGO;
import static views.utils.Image.loadImage;
import views.utils.Alerts;

/**
 *
 * @author Weslei Ramos
 */
public class CompraEditor extends javax.swing.JDialog {

    private Store store;
    private Actions action;
    private Usuario usuario = null;
    private ArrayList<Fornecedor> fornecedores = new ArrayList<Fornecedor>();
    
    /**
     * 
     * @param store
     * @param tituloModal 
     */
    public CompraEditor(Store store, Actions action) {
        super((javax.swing.JFrame) null, ModalityType.APPLICATION_MODAL);
        initComponents();
        setIconImage(loadImage(LOGO, 32, 32));
        this.store = store;
        this.action = action;
        this.setLocationRelativeTo(null);
        this.setTitle(getTitleFromAction());
        this.title.setText(getTitleFromAction());
        usuarioSelect.setEnabled(false);
        
        Compra currentCompra = getCurrentCompra();
        UsuarioDAO temp_dao = new UsuarioDAO();
        if (action == Actions.CREATE) {
            temp_dao.setValue("id_usuario", Singleton.getUsuario().getId());
        } else {
            temp_dao.setValue("id_usuario", currentCompra.getIdUsuario());
        }
        
        if (temp_dao.find()) {
            usuario = temp_dao.toBean();
        } else {
            Alerts.error(null, "Erro ao obter o usuário", "Não foi possível obter o usuário para a compra.");
            this.close();
        }
        
        usuarioSelect.addItem(usuario.getEmail());
        usuarioSelect.setSelectedIndex(0);
        
        for (FornecedorDAO fornecedor: new FornecedorDAO().get()) {
            Fornecedor bean = fornecedor.toBean();
            fornecedorSelect.addItem(bean.getRazaoSocial());
            if (action == Actions.EDIT) {
                if (bean.getId() == currentCompra.getIdFornecedor()) {
                    fornecedorSelect.setSelectedIndex(fornecedores.size());
                }
            }
            fornecedores.add(bean);
        }
    }
    
    /**
     * 
     * @param action
     * @return 
     */
    private String getTitleFromAction() {
        switch (action) {
            case CREATE: return "Adicionar compra";          
            case EDIT: return "Editar compra";
            default: return "";
        }
    }
    
    /**
     * Tenta obter o bean selecionado
     * @return 
     */
    private Compra getCurrentCompra() {
        if (this.action == Actions.CREATE)
            return new Compra();
        
        Compra bean = (Compra) store.get("selected");
        return bean == null ? new Compra() : bean;
    }
    
    /**
     * Obtém os dados inseridos e salva em um bean
     * @return 
     */
    private Compra getCompraDados() {
        Compra bean = getCurrentCompra();
        bean.setIdUsuario(usuario.getId());
        
        Fornecedor fornecedor = fornecedores.get(fornecedorSelect.getSelectedIndex());
        bean.setIdFornecedor(fornecedor.getId());

        return bean;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        title = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        fornecedorSelect = new javax.swing.JComboBox<>();
        usuarioSelect = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Compras");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Fornecedor");

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Usuário");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(title)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel1))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(fornecedorSelect, 0, 462, Short.MAX_VALUE)
                                    .addComponent(usuarioSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))))
                .addGap(14, 14, 14))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(fornecedorSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(usuarioSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void close() {
        this.setVisible(false);
        this.dispose();
    }
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        this.close();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if (fornecedorSelect.getSelectedIndex() >= 0 && usuarioSelect.getSelectedIndex() >= 0) {
            this.store.set("edited", this.getCompraDados());
            this.close();
        } else {
            Alerts.error(this.getParent(), "Erro ao salvar", "É necessário inserir fornecedor e usuário");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> fornecedorSelect;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel title;
    private javax.swing.JComboBox<String> usuarioSelect;
    // End of variables declaration//GEN-END:variables
}