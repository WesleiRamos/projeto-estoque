package views.modal;

import views.utils.Actions;
import java.math.BigDecimal;
import java.util.ArrayList;
import reactive.Store;
import model.bean.Produto;
import model.bean.TipoProduto;
import model.dao.TipoProdutoDAO;
import static views.utils.Image.LOGO;
import static views.utils.Image.loadImage;
import views.utils.Alerts;
import views.utils.Masks;

/**
 *
 * @author Weslei Ramos
 */
public class ProdutoEditor extends javax.swing.JDialog {
    
    private Store store;
    private Actions action;
    
    private ArrayList<TipoProduto> tipoProdutos = new ArrayList<>();

    /**
     * 
     * @param store
     * @param tituloModal 
     */
    public ProdutoEditor(Store store, Actions action) {
        super((javax.swing.JFrame) null, ModalityType.APPLICATION_MODAL);
         
        initComponents();
        setIconImage(loadImage(LOGO, 32, 32));
        
        this.store = store;
        this.action = action;
        this.setLocationRelativeTo(null);
        this.setTitle(getTitleFromAction());
        this.title.setText(getTitleFromAction());
        
        Produto bean = getCurrentProduto();
        for (TipoProdutoDAO tipo: new TipoProdutoDAO().get()) {
            TipoProduto tipoBean = tipo.toBean();
            tipoProdutoSelect.addItem(String.format("%d - %s", tipoBean.getId(), tipoBean.getDescricao()));
            
            if (action == Actions.EDIT) {
                if (tipoBean.getId() == bean.getTipoProduto()) {
                    tipoProdutoSelect.setSelectedIndex(tipoProdutos.size());
                }
            }
            
            tipoProdutos.add(tipoBean);
        }
        
        if (action == Actions.EDIT) {
            this.descricao.setText(bean.getDescricao());
            this.quantidade.setValue(bean.getQuantidade());
            this.valor_unitario.setValue(bean.getValorUnitario());
        } else {
            this.quantidade.setValue(0);
            this.valor_unitario.setValue(0.00);
        }
        
        
        Masks.numberMask(this.quantidade);
        Masks.moneyMask(this.valor_unitario);
    }
    
    /**
     * 
     * @param action
     * @return 
     */
    private String getTitleFromAction() {
        switch (action) {
            case CREATE:
                return "Adicionar produto";
                
            case EDIT:
                return "Editar produto";
                
            default:
                return "";
        }
    }
    
    /**
     * Tenta obter o bean selecionado
     * @return 
     */
    private Produto getCurrentProduto() {
        if (action == Actions.CREATE)
            return new Produto();
        
        Produto bean = (Produto) this.store.get("selected");
        return bean == null ? new Produto() : bean;
    }
    
    /**
     * Obtém os dados inseridos e salva em um bean
     * @return 
     */
    private Produto getProdutoDados() {
        Produto bean = getCurrentProduto();
        bean.setDescricao(this.descricao.getText());
        bean.setQuantidade(Integer.parseInt(this.quantidade.getText()));
        bean.setValorUnitario(new BigDecimal(this.valor_unitario.getValue().toString()));
        
        TipoProduto tipoProduto = tipoProdutos.get(tipoProdutoSelect.getSelectedIndex());
        bean.setTipoProduto(tipoProduto.getId());
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

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        descricao = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        quantidade = new javax.swing.JFormattedTextField();
        tipoProdutoSelect = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        valor_unitario = new javax.swing.JFormattedTextField();
        title = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setResizable(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Descrição");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Valor Unitário");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Quantidade");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Tipo Produto");

        jButton2.setBackground(javax.swing.UIManager.getDefaults().getColor("Actions.Red"));
        jButton2.setText("Cancelar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton1.setText("Salvar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoProdutoSelect, 0, 521, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(descricao))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton2)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(quantidade)
                            .addComponent(valor_unitario))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(descricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(quantidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(valor_unitario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(tipoProdutoSelect, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2)
                    .addComponent(jButton1))
                .addContainerGap(20, Short.MAX_VALUE))
        );

        title.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        title.setText("Tipo produtos");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(title)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(21, Short.MAX_VALUE)
                .addComponent(title)
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
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
        if (tipoProdutoSelect.getSelectedIndex() >= 0) {
            this.store.set("edited", this.getProdutoDados());
            this.close();
        } else {
            Alerts.error(this.getParent(), "Erro ao salvar", "É necessário informar um tipo de produto");
        }
    }//GEN-LAST:event_jButton1ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField descricao;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JFormattedTextField quantidade;
    private javax.swing.JComboBox<String> tipoProdutoSelect;
    private javax.swing.JLabel title;
    private javax.swing.JFormattedTextField valor_unitario;
    // End of variables declaration//GEN-END:variables
}
