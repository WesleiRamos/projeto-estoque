package model.dao;

import java.sql.Timestamp;
import model.bean.Compra;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.TableProperties;

public class CompraDAO extends GenericDAO<CompraDAO> {
    
    public TableProperties tableProperties() {
        return new TableProperties(
            "compra", new String[]{
                "id_compra",
                "data",
                "id_fornecedor",
                "id_usuario"
            },
            "id_compra", new String[] {
                "id_fornecedor",
                "id_usuario"
            }
        );
    }

    public CompraDAO() {
        super();
    }

    public CompraDAO(int id) {
        super(id);
    }

    public CompraDAO(int id, Timestamp data, int id_fornecedor, int id_usuario) {
        super(id);
        this.setValue("data", data);
        this.setValue("id_fornecedor", id_fornecedor);
        this.setValue("id_usuario", id_usuario);
    }

    public CompraDAO(Compra v) {
        super(v.getId());
        this.setValue("data", v.getData() == null ? raw("now()") : v.getData());
        this.setValue("id_fornecedor", v.getIdFornecedor());
        this.setValue("id_usuario", v.getIdUsuario());
    }

    public CompraDAO prepare() {
        this.selectRaw("compra.*, fornecedor.razao_social as fornecedor, usuario.email as usuario")
          .innerJoin("fornecedor", (join) -> {
                join.on("compra.id_fornecedor", "=", "fornecedor.id_fornecedor");
          })
          .innerJoin("usuario", (join) -> {
                join.on("compra.id_usuario", "=", "usuario.id_usuario");
          });

        return this;
    }

    public Compra toBean() {
        Compra bean = new Compra();
        bean.setId(this.<Integer>getValue("id_compra", Integer.class));
        bean.setData(this.<Timestamp>getValue("data", Timestamp.class));
        bean.setIdFornecedor(this.<Integer>getValue("id_fornecedor", Integer.class));
        bean.setIdUsuario(this.<Integer>getValue("id_usuario", Integer.class));
        
        if (this.hasValue("fornecedor"))
            bean.setFornecedorRazaoSocial(this.<String>getValue("fornecedor", String.class));
        
        if (this.hasValue("usuario"))
            bean.setUsuarioEmail(this.<String>getValue("usuario", String.class));
        
        return bean;
    }

    public static CompraDAO fromBean(Compra v) {
        return new CompraDAO(v);
    }
}
