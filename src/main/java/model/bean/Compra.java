package model.bean;

import java.sql.Timestamp;

public class Compra extends GenericBean {
    private int id;
    private Timestamp data;
    private int idFornecedor;
    private int idUsuario;
    private String fornecedorRazaoSocial;
    private String usuarioEmail;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Timestamp getData() {
        return data;
    }

    public void setData(Timestamp data) {
        this.data = data;
    }

    public int getIdFornecedor() {
        return idFornecedor;
    }

    public void setIdFornecedor(int idFornecedor) {
        this.idFornecedor = idFornecedor;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    /**
     * @return the fornecedorRazaoSocial
     */
    public String getFornecedorRazaoSocial() {
        return fornecedorRazaoSocial;
    }

    public void setFornecedorRazaoSocial(String fornecedorRazaoSocial) {
        this.fornecedorRazaoSocial = fornecedorRazaoSocial;
    }

    public String getUsuarioEmail() {
        return usuarioEmail;
    }

    public void setUsuarioEmail(String usuarioEmail) {
        this.usuarioEmail = usuarioEmail;
    }
    
    public Object[] getAsRow() {
        return new Object[]{
            getId(),
            formatDate.format(getData()),
            getFornecedorRazaoSocial(),
            getUsuarioEmail()
        };
    }
}
