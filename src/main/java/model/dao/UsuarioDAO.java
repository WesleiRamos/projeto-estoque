package model.dao;

import model.bean.Usuario;
import model.dao.querybuilder.GenericDAO;
import model.dao.querybuilder.TableProperties;

public class UsuarioDAO extends GenericDAO<UsuarioDAO> {

    public TableProperties tableProperties() {
        return new TableProperties(
            "usuario", new String[]{
                "id_usuario",
                "email",
                "senha"
            },
            "id_usuario"
        );
    }

    public UsuarioDAO() {
        super();
    }

    public UsuarioDAO(int id) {
        super(id);
    }

    public UsuarioDAO(Usuario u) {
        super(u.getId());
        this.setValue("email", u.getEmail());
        this.setValue("senha", u.getSenha());
    }

    public Usuario toBean() {
        Usuario bean = new Usuario();
        bean.setId(this.<Integer>getValue("id_usuario", Integer.class));
        bean.setEmail(this.<String>getValue("email", String.class));
        bean.setSenha(this.<String>getValue("senha", String.class));
        return bean;
    }

    public static UsuarioDAO fromBean(Usuario u) {
        return new UsuarioDAO(u);
    }
}
