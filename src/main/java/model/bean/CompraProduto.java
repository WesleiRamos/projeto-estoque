package model.bean;

import java.math.BigDecimal;

public class CompraProduto extends GenericBean implements CompraVendaProduto {
    private int idCompra;
    private int idProduto;
    private int quantidade;
    private BigDecimal valorUnitarioCompra;
    private String produto;

    public int getId() {
        return idCompra;
    }

    public void setId(int idCompra) {
        this.idCompra = idCompra;
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
        return valorUnitarioCompra;
    }

    public void setValorUnitario(BigDecimal valorUnitarioCompra) {
        this.valorUnitarioCompra = valorUnitarioCompra;
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
