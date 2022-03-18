package model.dao;

import model.bean.TipoProduto;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.TableProperties;

public class TipoProdutoDAO extends GenericDAO<TipoProdutoDAO> {

    public TableProperties tableProperties() {
        return new TableProperties(
            "tipo_produto", new String[]{
                "id_tipo_produto",
                "descricao"
            },
            "id_tipo_produto"
        );
    }

    public TipoProdutoDAO() {
        super();
    }

    public TipoProdutoDAO(int id) {
        super(id);
    }

    public TipoProdutoDAO(int id, String descricao) {
        super(id);
        this.setValue("descricao", descricao);
    }

    public TipoProdutoDAO(TipoProduto t) {
        super(t.getId());
        this.setValue("descricao", t.getDescricao());
    }

    public TipoProduto toBean() {
        TipoProduto bean = new TipoProduto();
        bean.setId(this.<Integer>getValue("id_tipo_produto", Integer.class));
        bean.setDescricao(this.<String>getValue("descricao", String.class));
        return bean;
    }

    public static TipoProdutoDAO fromBean(TipoProduto t) {
        return new TipoProdutoDAO(t);
    }
}
