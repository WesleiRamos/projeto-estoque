package views;

import views.utils.Alerts;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JTable;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import model.bean.CompraVendaProduto;
import model.bean.GenericBean;
import model.dao.querybuilder.GenericDAO;
import reactive.Store;
import views.utils.Actions;

interface SelectedCallback {
    public void callback(boolean value);
}

public class GenericRoute<DAO extends GenericDAO, BEAN extends GenericBean> extends JPanel {
    
    protected Class<DAO> c_dao;
    protected Class<BEAN> c_bean;
    protected Store store;
    
    public GenericRoute(Class<DAO> c_dao, Class<BEAN> c_bean) {
        this.c_dao = c_dao;
        this.c_bean = c_bean;
        this.store = new Store(new HashMap<String, Object>() {
            {
                put("selected", null);
                put("edited", null);
                put("data", new ArrayList<BEAN>() { });
            }
        });
        
        this.addAncestorListener(new AncestorListener() {
            public void ancestorAdded (AncestorEvent event) {
                load();
            }

            public void ancestorRemoved(AncestorEvent event) {
                if (store.has("selected")) {
                    store.set("selected", null);
                }
            }
            
            public void ancestorMoved(AncestorEvent event) {}
        });
    }
    
    /**
     * Converte o dao para bean
     * @param bean
     * @return
     * @throws Exception 
     */
    private DAO daoFromBean(BEAN bean) throws Exception {
        Method fromBean = c_dao.getMethod("fromBean", c_bean);
        return (DAO) fromBean.invoke(c_bean, bean);
    }
    
    /**
     * Cria uma nova instancia do dao e retorna
     * @return 
     */
    protected DAO getDaoInstance() {
        DAO dao = null;
        try {
            dao = this.c_dao.getDeclaredConstructor().newInstance();
        } catch (Exception ex) {
            Alerts.error(null, "Erro ao obter instância DAO", String.format("%s", ex));
        }
        return dao;
    }
    
    /**
     * Carrega dados do banco com base no DAO e no BEAN
     * @param <DAO>
     * @param <BEAN>
     * @param daoClass
     * @return 
     */
    protected void load(){
        if (!isShowing() || java.beans.Beans.isDesignTime())
            return;
        
        ArrayList<BEAN> dados = new ArrayList<>();
        try {
            DAO temp_dao = getDaoInstance();
            if (temp_dao == null)
                throw new Exception("DAO can't be null");
            
            temp_dao.prepare();
            for (Object row : temp_dao.get()) {
                DAO dao = (DAO) row;
                dados.add((BEAN) dao.toBean());
            }
        } catch (Exception ex) {
            Alerts.error(null, "Erro ao carregar dados", String.format("%s", ex));
        }
       
        store.set("data", dados);
    }
    
    /**
     * Cria os subscribers para os valores passados
     * @param <DAO>
     * @param <BEAN>
     * @param S
     * @param store
     * @param daoClass 
     */
    protected void createSubscribersForTable(JTable table, SelectedCallback S) {
        
        for (int i = 0; i < table.getColumnCount(); i++) {
            TableColumn column = table.getColumnModel().getColumn(i);
            DefaultTableCellRenderer renderer = new DefaultTableCellRenderer();
            if (column.getHeaderValue().toString().toLowerCase().equals("id")) {
                renderer.setHorizontalAlignment(SwingConstants.CENTER);
                column.setMaxWidth(80); 
            } else {
                renderer.setHorizontalAlignment(SwingConstants.LEFT);
            }

            column.setCellRenderer(renderer);
        }

        store.subscribe("selected", (value) -> {
            S.callback(value != null);
        });
        
        store.subscribe("edited", (value) -> {
            if (value == null)
                return;
            
            try {
                DAO newRecord = (DAO) this.daoFromBean((BEAN) value);
                newRecord.save();
                this.load();
            } catch (Exception ex) {
                Alerts.error(null, "Erro ao salvar", String.format("%s", ex));
            }
        });
        
        store.subscribe("data", (value) -> {
           ArrayList<BEAN> data = (ArrayList<BEAN>) value;
           DefaultTableModel model = (DefaultTableModel) table.getModel();
           model.setNumRows(0);
           
           data.forEach((row) -> {
               model.addRow(row.getAsRow());
           });
        });
        
        table.getSelectionModel().addListSelectionListener((event) -> {
            if (event.getValueIsAdjusting()) { 
                if (table.getSelectedRow() == -1) {
                    store.set("seleted", null);
                } else {
                    ArrayList<BEAN> dados = (ArrayList<BEAN>) store.get("data");
                    store.set("selected", dados.get(table.getSelectedRow()));
                }
            }
        });
        
        this.load();
    }
    
    /**
     * Abre um novo modal
     * @param <Modal>
     * @param store
     * @param title
     * @param modal 
     */
    protected <Modal extends JDialog> void openModal(Actions action, Class<Modal> modal) {
        try {
            Modal window = modal.getDeclaredConstructor(Store.class, Actions.class).newInstance(store, action);
            window.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent event) {
                    super.windowClosed(event);
                    store.set("edited", null);
                }
            });
            window.setVisible(true);
        } catch (Exception ex) {
            Alerts.error(null, "Erro ao executar a ação", String.format("%s", ex));
        }
    }
    
    /**
     * 
     * @param <TDAO>
     * @param <TBEAN>
     * @param primaryKey
     * @param t_dao
     * @param t_bean 
     */
    protected <TDAO extends GenericDAO, TBEAN extends GenericBean & CompraVendaProduto> void openCompraVendaModal(Class<TDAO> t_dao, Class<TBEAN> t_bean) {
        BEAN bean = (BEAN) store.get("selected");
        try {
            Method method = c_bean.getMethod("getId");
            int result = (int) method.invoke(bean);
            
            CompraVendaGenericWindow<TDAO, TBEAN> window = new CompraVendaGenericWindow<>(result, t_dao, t_bean);
            window.setVisible(true);
        } catch (Exception ex) {
            Alerts.error(null, "Erro ao executar a ação", String.format("%s", ex));
        }
    }
    
    /**
     * Caso haja uma confirmação para deletar, deleta o registro selecionado
     * @param parent
     * @param store
     * @param title
     * @param message
     * @param method 
     */
    protected void deleteSelected(String title, String prefix, String methodName) {
        BEAN bean = (BEAN) store.get("selected");
        
        try {
            Method method = c_bean.getMethod(methodName);
            String result = String.valueOf(method.invoke(bean));
            
            if (Alerts.delete(getParent(), title, String.format("Deseja mesmo deletar %s \"%s\"?", prefix, result))) {
                store.set("selected", null);
                this.daoFromBean(bean).delete();
                this.load();
            }
        } catch (Exception ex) {
            Alerts.error(null, "Erro ao executar a ação", String.format("%s", ex));
        } 
    }
}