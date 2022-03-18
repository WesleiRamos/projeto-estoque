package views;

import model.bean.CompraVendaProduto;
import model.bean.GenericBean;
import model.dao.querybuilder.GenericDAO;
import static views.utils.Image.LOGO;
import static views.utils.Image.loadImage;

public class CompraVendaGenericWindow<DAO extends GenericDAO, BEAN extends GenericBean & CompraVendaProduto> extends javax.swing.JDialog {
    
    private int selectedId = 0;
    private Class<DAO> c_dao = null;
    private Class<BEAN> c_bean = null;
    
    public CompraVendaGenericWindow(int selectedId, Class<DAO> c_dao, Class<BEAN> c_bean) {
        super((javax.swing.JFrame) null, ModalityType.APPLICATION_MODAL);
        
        this.c_dao = c_dao;
        this.c_bean = c_bean;
        this.selectedId = selectedId;
        
        initComponents();
        setIconImage(loadImage(LOGO, 32, 32));
    }
    
    private String getTitleFromBean() {
        String[] splits = c_bean
            .getSimpleName()
            .replaceAll("([A-Z])", " $1")
            .trim()
            .split(" ");
        
        String str = String.format("%ss da %s", splits[1], splits[0]);
        return str.substring(0, 1).toUpperCase() + str.substring(1).toLowerCase();
    }
    
    /**
     * Design buga com tipos genéricos na classe
     */
    private void initComponents() {
        CompraVendaGeneric compraVendaGeneric1 = new CompraVendaGeneric<DAO, BEAN>(c_dao, c_bean, getTitleFromBean(), selectedId);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(756, 600));
        setPreferredSize(new java.awt.Dimension(756, 600));
        setTitle(getTitleFromBean());
        setLocationRelativeTo(null);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compraVendaGeneric1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(compraVendaGeneric1, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );

        pack();
    }

    private void close() {
        this.setVisible(false);
        this.dispose();
    }
}
