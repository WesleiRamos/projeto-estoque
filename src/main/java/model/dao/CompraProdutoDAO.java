package model.dao;

import java.math.BigDecimal;
import model.bean.CompraProduto;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.None;
import model.dao.querybuilder.TableProperties;

public class CompraProdutoDAO extends GenericDAO<CompraProdutoDAO> {
    
    public TableProperties tableProperties() {
        return new TableProperties(
            "compra_produto", new String[]{
                "id_compra",
                "id_produto",
                "quantidade",
                "valor_unitario_compra"
            },
            "", new String[] {
                "id_compra",
                "id_produto"
            }
        );
    }

    public CompraProdutoDAO() {
        super();
    }

    public CompraProdutoDAO(int id_compra, int id_produto, int quantidade, double valor_unitario_compra) {
        super();
        this.setValue("id_compra", id_compra);
        this.setValue("id_produto", id_produto);
        this.setValue("quantidade", quantidade);
        this.setValue("valor_unitario_compra", valor_unitario_compra);
    }

    public CompraProdutoDAO(CompraProduto c) {
        super();
        this.setValue("id_compra", c.getId());
        this.setValue("id_produto", c.getIdProduto());
        this.setValue("quantidade", c.getQuantidade());
        this.setValue("valor_unitario_compra", c.getValorUnitario());
    }

    public boolean save() throws Exception {
        if (this.getValue("id_compra") instanceof None) {
            throw new Exception("id_compra não pode ser nulo");
        }

        if (this.getValue("id_produto") instanceof None) {
            throw new Exception("id_produto não pode ser nulo");
        }

        return super.save();
    }
    
    public CompraProdutoDAO prepare() {
        this.selectRaw("compra_produto.*, produto.descricao as produto")
          .innerJoin("produto", (join) -> {
                join.on("compra_produto.id_produto", "=", "produto.id_produto");
          });

        return this;
    }


    public CompraProduto toBean() {
        CompraProduto bean = new CompraProduto();
        bean.setId(this.<Integer>getValue("id_compra", Integer.class));
        bean.setIdProduto(this.<Integer>getValue("id_produto", Integer.class));
        bean.setQuantidade(this.<Integer>getValue("quantidade", Integer.class));
        bean.setValorUnitario(this.<BigDecimal>getValue("valor_unitario_compra", BigDecimal.class));
        
        if (this.hasValue("produto"))
            bean.setProduto(this.<String>getValue("produto", String.class));
        
        return bean;
    }

    public static CompraProdutoDAO fromBean(CompraProduto c) {
        return new CompraProdutoDAO(c);
    }
}
