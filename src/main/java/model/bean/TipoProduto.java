package model.bean;

public class TipoProduto extends GenericBean {
    private int id;
    private String descricao;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
    
    public Object[] getAsRow() {
        return new Object[] {
            getId(),
            getDescricao()
        };
    }
}
