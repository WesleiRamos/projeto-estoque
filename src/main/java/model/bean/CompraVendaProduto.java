package model.bean;

import java.math.BigDecimal;

public interface CompraVendaProduto {
    void setId(int id);
    void setIdProduto(int id);
    void setQuantidade(int quantidade);
    void setValorUnitario(BigDecimal valor);
    int getId();
    int getIdProduto();
    int getQuantidade();
    BigDecimal getValorUnitario();
}
