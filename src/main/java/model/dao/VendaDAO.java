package model.dao;

import java.sql.Timestamp;
import model.bean.Venda;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.TableProperties;

public class VendaDAO extends GenericDAO<VendaDAO> {
    
    public TableProperties tableProperties() {
        return new TableProperties(
            "venda", new String[]{
                "id_venda",
                "data",
                "id_cliente",
                "id_usuario"
            },
            "id_venda", new String[]{
                "id_cliente",
                "id_usuario"
            }
        );
    }

    public VendaDAO() {
        super();
    }

    public VendaDAO(int id) {
        super(id);
    }

    public VendaDAO(int id, Timestamp data, int id_cliente, int id_usuario) {
        super(id);
        this.setValue("data", data);
        this.setValue("id_cliente", id_cliente);
        this.setValue("id_usuario", id_usuario);
    }

    public VendaDAO(Venda v) {
        super(v.getId());
        
        this.setValue("data", v.getData() == null ? raw("now()") : v.getData());
        this.setValue("id_cliente", v.getIdCliente());
        this.setValue("id_usuario", v.getIdUsuario());
    }

    public VendaDAO prepare() {
        this.selectRaw("venda.*, cliente.nome as cliente, usuario.email as usuario")
          .innerJoin("cliente", (join) -> {
                join.on("venda.id_cliente", "=", "cliente.id_cliente");
          })
          .innerJoin("usuario", (join) -> {
                join.on("venda.id_usuario", "=", "usuario.id_usuario");
          });

        return this;
    }

    public Venda toBean() {
        Venda bean = new Venda();
        bean.setId(this.<Integer>getValue("id_venda", Integer.class));
        bean.setData(this.<Timestamp>getValue("data", Timestamp.class));
        bean.setIdCliente(this.<Integer>getValue("id_cliente", Integer.class));
        bean.setIdUsuario(this.<Integer>getValue("id_usuario", Integer.class));
        
        if (this.hasValue("cliente"))
            bean.setClienteNome(this.<String>getValue("cliente", String.class));
        
        if (this.hasValue("usuario"))
            bean.setUsuarioEmail(this.<String>getValue("usuario", String.class));
        
        return bean;
    }

    public static VendaDAO fromBean(Venda v) {
        return new VendaDAO(v);
    }
}
