package model.dao;

import java.math.BigDecimal;
import model.bean.Produto;
import model.dao.querybuilder.GenericDAO;
import java.util.ArrayList;
import model.dao.querybuilder.TableProperties;

public class ProdutoDAO extends GenericDAO<ProdutoDAO> {
    
    public TableProperties tableProperties() {
        return new TableProperties(
            "produto", new String[]{
              "id_produto",
              "descricao",
              "valor_unitario",
              "quantidade",
              "id_tipo_produto"
            },
            "id_produto", new String[]{
                "id_tipo_produto"
            }
        );
    }

    public ProdutoDAO() {
        super();
    }

    public ProdutoDAO(int id) {
        super(id);
    }

    public ProdutoDAO(int id, String descricao, double valorUnitario, int quantidade, int idTipoProduto) {
        super(id);
        this.setValue("descricao", descricao);
        this.setValue("valor_unitario", valorUnitario);
        this.setValue("quantidade", quantidade);
        this.setValue("id_tipo_produto", idTipoProduto);
    }

    public ProdutoDAO(Produto p) {
        super(p.getId());
        this.setValue("descricao", p.getDescricao());
        this.setValue("valor_unitario", p.getValorUnitario());
        this.setValue("quantidade", p.getQuantidade());
        this.setValue("id_tipo_produto", p.getTipoProduto());
    }

    public ProdutoDAO prepare() {
        this.selectRaw("produto.*, tipo_produto.descricao as tipo_produto_descricao")
          .innerJoin("tipo_produto", (join) -> {
                join.on("produto.id_tipo_produto", "=", "tipo_produto.id_tipo_produto");
          });

        return this;
    }

    public Produto toBean() {
        Produto bean = new Produto();
        bean.setId(this.<Integer>getValue("id_produto", Integer.class));
        bean.setDescricao(this.<String>getValue("descricao", String.class));
        bean.setValorUnitario(this.<BigDecimal>getValue("valor_unitario", BigDecimal.class));
        bean.setQuantidade(this.<Integer>getValue("quantidade", Integer.class));
        bean.setTipoProduto(this.<Integer>getValue("id_tipo_produto", Integer.class));
        
        if (this.hasValue("tipo_produto_descricao"))
            bean.setTipoProdutoDescricao(this.<String>getValue("tipo_produto_descricao", String.class));
            
        return bean;
    }

    public static ProdutoDAO fromBean(Produto p) {
        return new ProdutoDAO(p);
    }
}
