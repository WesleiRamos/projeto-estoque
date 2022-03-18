package model.dao;

import model.bean.Cliente;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.TableProperties;

public class ClienteDAO extends GenericDAO<ClienteDAO> {

    public TableProperties tableProperties() {
        return new TableProperties(
            "cliente", new String[]{
                "id_cliente",
                "nome",
                "cpf",
                "email",
                "telefone"
            },
            "id_cliente"
        );
    }
    
    public ClienteDAO() {
        super();
    }

    public ClienteDAO(int id) {
        super(id);
    }

    public ClienteDAO(int id, String nome, String cpf, String email, String telefone) {
        super(id);
        this.setValue("nome", nome);
        this.setValue("cpf", cpf);
        this.setValue("email", email);
        this.setValue("telefone", telefone);
    }

    public ClienteDAO(Cliente c) {
        super(c.getId());
        this.setValue("nome", c.getNome());
        this.setValue("cpf", c.getCpf());
        this.setValue("email", c.getEmail());
        this.setValue("telefone", c.getTelefone());
    }

    public Cliente toBean() {
        Cliente bean = new Cliente();
        bean.setId(this.<Integer>getValue("id_cliente", Integer.class));
        bean.setNome(this.<String>getValue("nome", String.class));
        bean.setCpf(this.<String>getValue("cpf", String.class));
        bean.setEmail(this.<String>getValue("email", String.class));
        bean.setTelefone(this.<String>getValue("telefone", String.class));
        return bean;
    }

    public static ClienteDAO fromBean(Cliente c) {
        return new ClienteDAO(c);
    }
}
