package model.bean;

import at.favre.lib.crypto.bcrypt.BCrypt;
import estoque.Singleton;

public class Usuario extends GenericBean {
    private int id;
    private String email;
    private String senha;

    public Usuario() {}
    public Usuario(String email, String senha) {
        this.email = email;
        this.senha = BCrypt.withDefaults().hashToString(Singleton.hashCost, senha.toCharArray());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }
    
    public boolean verifyPassword(String senha) {
        BCrypt.Result result = BCrypt.verifyer().verify(senha.toCharArray(), this.senha);
        return result.verified;
    }
    
    public Object[] getAsRow() {
        return new Object[] {
            getId(),
            getEmail()
        };
    }
}
