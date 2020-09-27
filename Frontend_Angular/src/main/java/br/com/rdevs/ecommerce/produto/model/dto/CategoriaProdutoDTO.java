package br.com.rdevs.ecommerce.produto.model.dto;

import br.com.rdevs.ecommerce.produto.model.entity.TbProduto;
import lombok.Data;

import java.math.BigInteger;
import java.util.List;

@Data
public class CategoriaProdutoDTO {

    private BigInteger idCategoriaProduto;

    private String dsCategoriaProduto;

//    private SubCategoriaProdutoDTO subCategoriaProduto;

//    private List<TbProduto> produtos;
}
