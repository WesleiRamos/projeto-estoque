package model.bean;

import java.math.BigDecimal;

public class VendaProduto extends GenericBean implements CompraVendaProduto {
    private int idVenda;
    private int idProduto;
    private int quantidade;
    private BigDecimal valorUnitarioVenda;
    private String produto;

    public int getId() {
        return idVenda;
    }

    public void setId(int idVenda) {
        this.idVenda = idVenda;
    }

    public int getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(int idProduto) {
        this.idProduto = idProduto;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public BigDecimal getValorUnitario() {
        return valorUnitarioVenda;
    }

    public void setValorUnitario(BigDecimal valorUnitarioVenda) {
        this.valorUnitarioVenda = valorUnitarioVenda;
    }
    
    public String getProduto() {
        return produto;
    }

    public void setProduto(String produto) {
        this.produto = produto;
    }
    
    public Object[] getAsRow() {
        return new Object[] {
            getIdProduto(),
            getProduto(),
            getQuantidade(),
            formatNumber.format(getValorUnitario()),
            formatNumber.format(getValorUnitario().multiply(new BigDecimal(getQuantidade())))
        };
    }
}
