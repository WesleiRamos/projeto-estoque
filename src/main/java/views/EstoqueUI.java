package views;

import estoque.Singleton;
import java.util.HashMap;
import static views.utils.Image.*;

/**
 *
 * @author Weslei Ramos
 */
public class EstoqueUI extends javax.swing.JFrame {
    
    /**
     * Creates new form EstoqueUI
     */
    public EstoqueUI() {
        initComponents(); 
        setLocationRelativeTo(null); 
        
        HashMap<String, javax.swing.JButton> routes = new HashMap<>() {{
            put("/usuario", goToUsuarios);
            put("/fornecedor", goToFornecedor);
            put("/cliente", goToCliente);
            put("/produto", goToProdutos);
            put("/produto/tipo", goToTipoProdutos);
            put("/compras", goToCompras);
            put("/vendas", goToVendas);
        }};
        
        routes.forEach((route, button) -> {
            button.addActionListener(createChangeRouteAction(route));
        });
                
        setIconImage(loadImage(LOGO, 32, 32));
        loadResizeAndSet(logoEstoque, LOGO);
        loadResizeAndSet(usuariosIcon, USUARIO_ICON);
        loadResizeAndSet(clienteIcon, CLIENTE_ICON);
        loadResizeAndSet(fornecedorIcon, FORNECEDOR_ICON);
        loadResizeAndSet(produtoIcon, PRODUTO_ICON);
        loadResizeAndSet(tipoProdutoIcon, TIPO_PRODUTO_ICON);
        loadResizeAndSet(vendasIcon, VENDAS_ICON);
        loadResizeAndSet(comprasIcon, COMPRAS_ICON);
    }
    
    public void setVisible(boolean b) {
        super.setVisible(b);
        setTitle(String.format("StockMaster · %s", Singleton.getUsuario().getEmail()));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        menu = new javax.swing.JPanel();
        logoEstoque = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        goToFornecedor = new javax.swing.JButton();
        fornecedorIcon = new javax.swing.JLabel();
        goToCliente = new javax.swing.JButton();
        clienteIcon = new javax.swing.JLabel();
        goToCompras = new javax.swing.JButton();
        comprasIcon = new javax.swing.JLabel();
        goToVendas = new javax.swing.JButton();
        vendasIcon = new javax.swing.JLabel();
        goToTipoProdutos = new javax.swing.JButton();
        tipoProdutoIcon = new javax.swing.JLabel();
        goToProdutos = new javax.swing.JButton();
        produtoIcon = new javax.swing.JLabel();
        goToUsuarios = new javax.swing.JButton();
        usuariosIcon = new javax.swing.JLabel();
        wrapper = new javax.swing.JPanel();
        fornecedorRoute = new views.FornecedorRoute();
        clienteRoute = new views.ClienteRoute();
        compraRoute = new views.CompraRoute();
        vendaRoute = new views.VendaRoute();
        tipoProdutoRoute = new views.TipoProdutoRoute();
        produtoRoute1 = new views.ProdutoRoute();
        usuarioRoute1 = new views.UsuarioRoute();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("StockMaster");
        setBackground(new java.awt.Color(0, 0, 0));
        setMinimumSize(new java.awt.Dimension(1024, 800));
        setPreferredSize(new java.awt.Dimension(1200, 800));

        menu.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        menu.setMaximumSize(new java.awt.Dimension(300, 32767));
        menu.setMinimumSize(new java.awt.Dimension(300, 0));
        menu.setName(""); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jLabel1.setText("StockMaster");

        goToFornecedor.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToFornecedor.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToFornecedor.setText("Fornecedores");
        goToFornecedor.setBorder(null);
        goToFornecedor.setBorderPainted(false);
        goToFornecedor.setContentAreaFilled(false);
        goToFornecedor.setFocusPainted(false);
        goToFornecedor.setFocusable(false);
        goToFornecedor.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToFornecedor.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        fornecedorIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        goToCliente.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToCliente.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToCliente.setText("Clientes");
        goToCliente.setBorder(null);
        goToCliente.setBorderPainted(false);
        goToCliente.setContentAreaFilled(false);
        goToCliente.setFocusPainted(false);
        goToCliente.setFocusable(false);
        goToCliente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToCliente.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        clienteIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        goToCompras.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToCompras.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToCompras.setText("Compras");
        goToCompras.setBorder(null);
        goToCompras.setBorderPainted(false);
        goToCompras.setContentAreaFilled(false);
        goToCompras.setFocusPainted(false);
        goToCompras.setFocusable(false);
        goToCompras.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToCompras.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        comprasIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        goToVendas.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToVendas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToVendas.setText("Vendas");
        goToVendas.setBorder(null);
        goToVendas.setBorderPainted(false);
        goToVendas.setContentAreaFilled(false);
        goToVendas.setFocusPainted(false);
        goToVendas.setFocusable(false);
        goToVendas.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToVendas.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        vendasIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        goToTipoProdutos.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToTipoProdutos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToTipoProdutos.setText("Tipos de produtos");
        goToTipoProdutos.setBorder(null);
        goToTipoProdutos.setBorderPainted(false);
        goToTipoProdutos.setContentAreaFilled(false);
        goToTipoProdutos.setFocusPainted(false);
        goToTipoProdutos.setFocusable(false);
        goToTipoProdutos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToTipoProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        tipoProdutoIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        goToProdutos.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToProdutos.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToProdutos.setText("Produtos");
        goToProdutos.setBorder(null);
        goToProdutos.setBorderPainted(false);
        goToProdutos.setContentAreaFilled(false);
        goToProdutos.setFocusPainted(false);
        goToProdutos.setFocusable(false);
        goToProdutos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToProdutos.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        produtoIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        goToUsuarios.setBackground(javax.swing.UIManager.getDefaults().getColor("Button.highlight"));
        goToUsuarios.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        goToUsuarios.setText("Usuários");
        goToUsuarios.setBorder(null);
        goToUsuarios.setBorderPainted(false);
        goToUsuarios.setContentAreaFilled(false);
        goToUsuarios.setFocusPainted(false);
        goToUsuarios.setFocusable(false);
        goToUsuarios.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        goToUsuarios.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        usuariosIcon.setPreferredSize(new java.awt.Dimension(50, 50));

        javax.swing.GroupLayout menuLayout = new javax.swing.GroupLayout(menu);
        menu.setLayout(menuLayout);
        menuLayout.setHorizontalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addContainerGap(32, Short.MAX_VALUE)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(fornecedorIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel1)
                                .addComponent(logoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(goToFornecedor, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(menuLayout.createSequentialGroup()
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(menuLayout.createSequentialGroup()
                                .addComponent(clienteIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(goToCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menuLayout.createSequentialGroup()
                                .addComponent(tipoProdutoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(goToTipoProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(menuLayout.createSequentialGroup()
                                .addComponent(produtoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(goToProdutos, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, menuLayout.createSequentialGroup()
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(comprasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vendasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(usuariosIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(goToVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goToCompras, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(goToUsuarios, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(23, 23, 23))))
        );
        menuLayout.setVerticalGroup(
            menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(menuLayout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(menuLayout.createSequentialGroup()
                        .addComponent(logoEstoque, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addGap(56, 56, 56)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(fornecedorIcon, javax.swing.GroupLayout.DEFAULT_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(goToFornecedor, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goToCliente)
                            .addComponent(clienteIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goToTipoProdutos)
                            .addComponent(tipoProdutoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(produtoIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(goToProdutos))
                        .addGap(32, 32, 32)
                        .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(goToCompras)
                            .addComponent(comprasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addComponent(goToVendas))
                    .addComponent(vendasIcon, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addGroup(menuLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(goToUsuarios, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(usuariosIcon, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(153, Short.MAX_VALUE))
        );

        wrapper.setLayout(new java.awt.CardLayout());
        cardLayout = (java.awt.CardLayout) wrapper.getLayout();
        wrapper.add(fornecedorRoute, "/fornecedor");
        wrapper.add(clienteRoute, "/cliente");
        wrapper.add(compraRoute, "/compras");
        wrapper.add(vendaRoute, "/vendas");
        wrapper.add(tipoProdutoRoute, "/produto/tipo");
        wrapper.add(produtoRoute1, "/produto");
        wrapper.add(usuarioRoute1, "/usuario");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(menu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(wrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 603, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(wrapper, javax.swing.GroupLayout.DEFAULT_SIZE, 800, Short.MAX_VALUE)
            .addComponent(menu, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Cria os eventos para a troca de rota
     */
    private java.awt.event.ActionListener createChangeRouteAction(String route) {
        return new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cardLayout.show(wrapper, route);
            }
        };
    }
        
    /**
     * @param args the command line arguments
     
    public static void main(String args[]) {        
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
            
        } catch(Exception ex) {
            System.err.println("Failed to initialize LaF");
        }

        /* Create and display the form * /
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EstoqueUI().setVisible(true);
            }
        });
    }*/

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel clienteIcon;
    private views.ClienteRoute clienteRoute;
    private views.CompraRoute compraRoute;
    private javax.swing.JLabel comprasIcon;
    private javax.swing.JLabel fornecedorIcon;
    private views.FornecedorRoute fornecedorRoute;
    private javax.swing.JButton goToCliente;
    private javax.swing.JButton goToCompras;
    private javax.swing.JButton goToFornecedor;
    private javax.swing.JButton goToProdutos;
    private javax.swing.JButton goToTipoProdutos;
    private javax.swing.JButton goToUsuarios;
    private javax.swing.JButton goToVendas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel logoEstoque;
    private javax.swing.JPanel menu;
    private javax.swing.JLabel produtoIcon;
    private views.ProdutoRoute produtoRoute1;
    private javax.swing.JLabel tipoProdutoIcon;
    private views.TipoProdutoRoute tipoProdutoRoute;
    private views.UsuarioRoute usuarioRoute1;
    private javax.swing.JLabel usuariosIcon;
    private views.VendaRoute vendaRoute;
    private javax.swing.JLabel vendasIcon;
    private java.awt.CardLayout cardLayout;
    private javax.swing.JPanel wrapper;
    // End of variables declaration//GEN-END:variables
}
