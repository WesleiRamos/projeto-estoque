package model.dao;

import java.math.BigDecimal;
import model.bean.VendaProduto;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.None;
import model.dao.querybuilder.TableProperties;

public class VendaProdutoDAO extends GenericDAO<VendaProdutoDAO> {

    public TableProperties tableProperties() {
        return new TableProperties(
            "venda_produto", new String[]{
                "id_venda",
                "id_produto",
                "quantidade",
                "valor_unitario_venda"
            },
            "", new String[] {
                "id_venda",
                "id_produto"
            }
        );
    }

    public VendaProdutoDAO() {
        super();
    }

    public VendaProdutoDAO(int id_venda, int id_produto, int quantidade, double valor_unitario_venda) {
        super();
        this.setValue("id_venda", id_venda);
        this.setValue("id_produto", id_produto);
        this.setValue("quantidade", quantidade);
        this.setValue("valor_unitario_venda", valor_unitario_venda);
    }

    public VendaProdutoDAO(VendaProduto c) {
        super();
        this.setValue("id_venda", c.getId());
        this.setValue("id_produto", c.getIdProduto());
        this.setValue("quantidade", c.getQuantidade());
        this.setValue("valor_unitario_venda", c.getValorUnitario());
    }

    public boolean save() throws Exception {
        if (this.getValue("id_venda") instanceof None) {
            throw new Exception("id_venda não pode ser nulo");
        }

        if (this.getValue("id_produto") instanceof None) {
            throw new Exception("id_produto não pode ser nulo");
        }

        return super.save();
    }
    
    public VendaProdutoDAO prepare() {
        this.selectRaw("venda_produto.*, produto.descricao as produto")
          .innerJoin("produto", (join) -> {
                join.on("venda_produto.id_produto", "=", "produto.id_produto");
          });

        return this;
    }

    public VendaProduto toBean() {
        VendaProduto bean = new VendaProduto();
        bean.setId(this.<Integer>getValue("id_venda", Integer.class));
        bean.setIdProduto(this.<Integer>getValue("id_produto", Integer.class));
        bean.setQuantidade(this.<Integer>getValue("quantidade", Integer.class));
        bean.setValorUnitario(this.<BigDecimal>getValue("valor_unitario_venda", BigDecimal.class));
        
        if (this.hasValue("produto"))
            bean.setProduto(this.<String>getValue("produto", String.class));
        
        return bean;
    }

    public static VendaProdutoDAO fromBean(VendaProduto c) {
        return new VendaProdutoDAO(c);
    }
}
