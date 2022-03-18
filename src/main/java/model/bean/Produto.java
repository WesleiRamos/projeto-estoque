package model.bean;

import java.math.BigDecimal;

public class Produto extends GenericBean {
    private int id;
    private String descricao;
    private BigDecimal valorUnitario;
    private int quantidade;
    private int tipoProduto;
    private String tipoProdutoDescricao;

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

    public BigDecimal getValorUnitario() {
        return valorUnitario;
    }

    public void setValorUnitario(BigDecimal valorUnitario) {
        this.valorUnitario = valorUnitario;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getTipoProduto() {
        return tipoProduto;
    }

    public void setTipoProduto(int tipoProduto) {
        this.tipoProduto = tipoProduto;
    }

    public String getTipoProdutoDescricao() {
        return tipoProdutoDescricao;
    }

    public void setTipoProdutoDescricao(String tipoProdutoDescricao) {
        this.tipoProdutoDescricao = tipoProdutoDescricao;
    }
    
    public Object[] getAsRow() {
        return new Object[]{
            getId(),
            getDescricao(),
            formatNumber.format(getValorUnitario()),
            getQuantidade(),
            getTipoProdutoDescricao()
        };
    }
}
