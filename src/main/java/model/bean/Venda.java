package model.bean;

import java.sql.Timestamp;

public class Venda extends GenericBean {
    private int id;
    private Timestamp data;
    private int idCliente;
    private int idUsuario;
    private String clienteNome;
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

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
    
    public String getClienteNome() {
        return clienteNome;
    }

    public void setClienteNome(String clienteNome) {
        this.clienteNome = clienteNome;
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
            getClienteNome(),
            getUsuarioEmail()
        };
    }
}
