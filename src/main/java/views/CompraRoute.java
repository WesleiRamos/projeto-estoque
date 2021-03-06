package views;

import model.bean.Compra;
import model.bean.CompraProduto;
import model.dao.CompraDAO;
import model.dao.CompraProdutoDAO;
import views.utils.Actions;
import views.modal.CompraEditor;

/**
 *
 * @author Weslei Ramos
 */
public class CompraRoute extends GenericRoute<CompraDAO, Compra> {

    /**
     * Creates new form CompraVendaRoute
     */
    public CompraRoute() {
        super(CompraDAO.class, Compra.class);
        initComponents();
        
        this.createSubscribersForTable(tableCompras, (bool) -> {
            verButton.setEnabled(bool);
            editarButton.setEnabled(bool);
            deletarButton.setEnabled(bool);
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        adicionarButton = new javax.swing.JButton();
        editarButton = new javax.swing.JButton();
        deletarButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableCompras = new javax.swing.JTable();
        verButton = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Gerenciar compras");

        adicionarButton.setText("Adicionar compra");
        adicionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarButtonActionPerformed(evt);
            }
        });

        editarButton.setText("Editar compra");
        editarButton.setEnabled(false);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        deletarButton.setText("Deletar compra");
        deletarButton.setEnabled(false);
        deletarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarButtonActionPerformed(evt);
            }
        });

        tableCompras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Data", "Fornecedor", "Usu??rio"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableCompras.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableCompras);

        verButton.setText("Ver detalhes da compra");
        verButton.setEnabled(false);
        verButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                verButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 725, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(adicionarButton)
                        .addGap(18, 18, 18)
                        .addComponent(verButton)
                        .addGap(18, 18, 18)
                        .addComponent(editarButton)
                        .addGap(18, 18, 18)
                        .addComponent(deletarButton)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(adicionarButton)
                    .addComponent(editarButton)
                    .addComponent(deletarButton)
                    .addComponent(verButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("Titulo");
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarButtonActionPerformed
        this.<CompraEditor>openModal(Actions.CREATE, CompraEditor.class);
    }//GEN-LAST:event_adicionarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        this.<CompraEditor>openModal(Actions.EDIT, CompraEditor.class);
    }//GEN-LAST:event_editarButtonActionPerformed

    private void deletarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarButtonActionPerformed
        this.deleteSelected("Deletar venda", "a venda", "getId");
    }//GEN-LAST:event_deletarButtonActionPerformed

    private void verButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_verButtonActionPerformed
        this.<CompraProdutoDAO, CompraProduto>openCompraVendaModal(CompraProdutoDAO.class, CompraProduto.class);
    }//GEN-LAST:event_verButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarButton;
    private javax.swing.JButton deletarButton;
    private javax.swing.JButton editarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableCompras;
    private javax.swing.JButton verButton;
    // End of variables declaration//GEN-END:variables
}
