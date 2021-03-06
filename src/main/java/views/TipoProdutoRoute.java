package views;

import model.bean.TipoProduto;
import model.dao.TipoProdutoDAO;
import views.utils.Actions;
import views.modal.TipoProdutoEditor;

/**
 *
 * @author Weslei Ramos
 */
public class TipoProdutoRoute extends GenericRoute<TipoProdutoDAO, TipoProduto> {

    /**
     * Creates new form TipoProdutoVendaRoute
     */
    public TipoProdutoRoute() {
        super(TipoProdutoDAO.class, TipoProduto.class);
        initComponents();
        
        this.createSubscribersForTable(tableTipos, (bool) -> {
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
        tableTipos = new javax.swing.JTable();

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        jLabel1.setText("Gerenciar tipos de produto");

        adicionarButton.setText("Adicionar tipo");
        adicionarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adicionarButtonActionPerformed(evt);
            }
        });

        editarButton.setText("Editar tipo");
        editarButton.setEnabled(false);
        editarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editarButtonActionPerformed(evt);
            }
        });

        deletarButton.setText("Deletar tipo");
        deletarButton.setEnabled(false);
        deletarButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletarButtonActionPerformed(evt);
            }
        });

        tableTipos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Descri????o"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableTipos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(tableTipos);
        if (tableTipos.getColumnModel().getColumnCount() > 0) {
            tableTipos.getColumnModel().getColumn(0).setResizable(false);
            tableTipos.getColumnModel().getColumn(0).setPreferredWidth(80);
            tableTipos.getColumnModel().getColumn(1).setResizable(false);
        }

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
                    .addComponent(deletarButton))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 468, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel1.getAccessibleContext().setAccessibleName("Titulo");
    }// </editor-fold>//GEN-END:initComponents

    private void adicionarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adicionarButtonActionPerformed
        this.<TipoProdutoEditor>openModal(Actions.CREATE, TipoProdutoEditor.class);
    }//GEN-LAST:event_adicionarButtonActionPerformed

    private void editarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editarButtonActionPerformed
        this.<TipoProdutoEditor>openModal(Actions.EDIT, TipoProdutoEditor.class);
    }//GEN-LAST:event_editarButtonActionPerformed

    private void deletarButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletarButtonActionPerformed
        this.deleteSelected("Deletar tipo", "o tipo", "getDescricao");
    }//GEN-LAST:event_deletarButtonActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton adicionarButton;
    private javax.swing.JButton deletarButton;
    private javax.swing.JButton editarButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tableTipos;
    // End of variables declaration//GEN-END:variables
}
