package br.com.rdevs.ecommerce.produto.model.dto;

import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class TipoProdutoDTO {
    private BigInteger idTipoProduto;

    private String dsTipoProduto;

    private List<TbProduto> produtos;
}
