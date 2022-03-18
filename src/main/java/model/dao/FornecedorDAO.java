package model.dao;

import model.bean.Fornecedor;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.TableProperties;

public class FornecedorDAO extends GenericDAO<FornecedorDAO> {
    
    public TableProperties tableProperties() {
        return new TableProperties(
            "fornecedor", new String[]{
                "id_fornecedor",
                "cnpj",
                "razao_social",
                "email",
                "telefone"
            },
            "id_fornecedor"
        );
    }

    public FornecedorDAO() {
        super();
    }

    public FornecedorDAO(int id) {
        super(id);
    }
    
    

    public FornecedorDAO(int id, String cnpj, String razoSocial, String email, String telefone) {
        super(id);
        this.setValue("cnpj", cnpj);
        this.setValue("razao_social", razoSocial);
        this.setValue("email", email);
        this.setValue("telefone", telefone);
    }

    public FornecedorDAO(Fornecedor f) {
        super(f.getId());
        this.setValue("cnpj", f.getCnpj());
        this.setValue("razao_social", f.getRazaoSocial());
        this.setValue("email", f.getEmail());
        this.setValue("telefone", f.getTelefone());
    }

    public Fornecedor toBean() {
        Fornecedor bean = new Fornecedor();
        bean.setId(this.<Integer>getValue("id_fornecedor", Integer.class));
        bean.setCnpj(this.<String>getValue("cnpj", String.class));
        bean.setRazaoSocial(this.<String>getValue("razao_social", String.class));
        bean.setEmail(this.<String>getValue("email", String.class));
        bean.setTelefone(this.<String>getValue("telefone", String.class));
        return bean;
    }

    public static FornecedorDAO fromBean(Fornecedor f) {
        return new FornecedorDAO(f);
    }
}

